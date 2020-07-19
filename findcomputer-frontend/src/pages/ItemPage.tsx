import React from 'react'
import { RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { AlertDialog, CustomizedSnackbars, OutlinedCard } from '../components/organism';
import { IItem, IIndexItemRequest, HTTPCallStatus} from '../data/interfaces';
import { serviceIndexItem } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Box, TextField} from '@material-ui/core';
import { serviceAddItem, serviceEditItem } from '../data/services/ItemService';
import { ItemForm } from '../components/organism/form';
import ItemDetailPage from './ItemDetailPage';
import { FormGroup, Label, Input } from 'reactstrap';

interface Props extends RouteComponentProps{};

interface IItemPage{
	rawContent:IItem[],
  viewConstraint:IIndexItemRequest,
	searchKey:string,
	category:string,
	snackbar:{
		isShown:boolean,
		severity:string,
		msg:[]
	},
	editDialog:{
		isShown:boolean
	}
}

const getInitViewConstraint = () => ({
  owner:"",
  category:""
})


export class ItemPage extends React.Component<Props,any> {
	
	state:IItemPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
      viewConstraint:getInitViewConstraint(),
			searchKey:"",
			category:"",
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			editDialog:{
				isShown:true
			}
		}
	}

	closeSnackbar = () => {
		this.setState({
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			}
		});
	}

	editItem = async (data:IUpsertItemRequest) => {
		await serviceEditItem(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					this.loadAllItems()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("edit item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		)
		this.setState({
			editDialog:{isShown:false}
		})
	}

	loadAllItems = async () => {
		console.log("posting index request")
		await serviceIndexItem(this.state.viewConstraint).subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["items"]);
				this.setState({
					rawContent: res.data["items"]
				});
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		);
	}

  setSuccessSnackbar = (res,key) => {
    console.log("deleting")
      if(res.data['status'] == HTTPCallStatus.Success){
        var array = [...this.state.rawContent]
        var index = array.map((e) => {
          return e.id
        }).indexOf(key);
        array.splice(index,1);

        this.setState({rawContent:array});
      }
      this.setState({
        snackbar:{
          isShown:true,
          severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
          msg:res.data['message']
        }
      })
  }

  setErrorSnackbar = (err) => {
    console.log("delete item err:"+err);
      this.setState({
        snackbar:{
          isShown:true,
          severity:"error",
          msg:err.message.split()
        }
      })
  }

  search = (e) => {
    this.setState({
      searchKey:e.target.value
    },() => console.log(this.state.searchKey))
	}
	
	searchByCategory = (e) => {
		this.setState({
      category:e.target.value
		})
		console.log(this.state.category)
	}

	async componentDidMount(){
		this.loadAllItems();
	}

	render(){
		let searchKeyword: string = this.state.searchKey
		const filteredItems = this.state.rawContent.filter(
			item => {
				if(searchKeyword === null) return 1;
				return (item.name != null && item.name.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1
				|| (item.owner != null && item.owner.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1)
				|| (item.description != null && item.description.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1))
			}
		)
		return (
			<Dashboard 
			titlePage = {"Items"}			
			content={
				<div>
					<div>
						{
							this.state.snackbar.isShown &&
							(<CustomizedSnackbars
								severity={this.state.snackbar.severity}
								msg={this.state.snackbar.msg}
								parentCallback={this.closeSnackbar}
							/>)
						}
					</div>
          <Box display="flex" flexWrap="wrap">
            <Box p={1}>
            <TextField
              id="outlined-full-width"
              label="search"
              style={{ margin: 8 }}
              placeholder="search something"
              helperText="Enter item's name / description / owner"
              fullWidth
              margin="normal"
              InputLabelProps={{
                shrink: true,
              }}
              variant="outlined"
              onChange={this.search}
            />
            </Box>
            <Box p ={1}>
							<FormGroup>
								<Label for="category">Category</Label>
								<Input type="select" name="select" id="category" onChange={this.searchByCategory}>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</Input>
							</FormGroup>
            </Box>
          </Box>
          <Box display="flex" flexWrap="wrap">
          {
							filteredItems.map(
							(c:IItem) => {
									return(
										<React.Fragment>
											<Box p={1}>
												<OutlinedCard
														category={c.category}
														owner = {c.owner}
														name = {c.name}
														price = {c.price}
														actions={
															<AlertDialog
															color="primary"
															param={c.id}
															parentAllowance = {this.state.editDialog.isShown}
															buttonTitle="show more"
															parentCallbackOpen={()=>this.setState({editDialog:{isShown:true}})}
															dialogTitle="Item Details"
															usingAction={false}
															dialogContent={
																<ItemDetailPage
																	id={c.id}
																	category={c.category}
																	owner = {c.owner}
																	name = {c.name}
																	price = {c.price}
																	description = {c.description}
																	parrentCallbackSuccess = {this.setSuccessSnackbar}
																	parrentCallbackError = {this.setErrorSnackbar}
																/>
															}
														/>
														}
													/>
												</Box>
										</React.Fragment>
									);
								}
              )
            }
            </Box>
          </div>
        }/>
      )
	}
};

// export default ItemPage;