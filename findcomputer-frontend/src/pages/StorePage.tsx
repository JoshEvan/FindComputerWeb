import React from 'react'
import { RouteComponentProps, Redirect } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { AlertDialog, CustomizedSnackbars, OutlinedCard } from '../components/organism';
import { IItem, IIndexItemRequest, HTTPCallStatus, IInsertItemRequest, IUpsertItemResponse, ICategory, IUpdateItemRequest} from '../data/interfaces';
import { serviceIndexItem } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Box, TextField } from '@material-ui/core';
import { serviceAddItem, serviceEditItem } from '../data/services/ItemService';
import { ItemForm } from '../components/organism/form';
import ItemDetailPage from './ItemDetailPage';
import jwt_decode from 'jwt-decode';
import { serviceIndexCategory } from '../data/services/CategoryService';
import { FormGroup, Label, Input } from 'reactstrap';

interface Props extends RouteComponentProps{};

interface IStorePage{
	rawContent:IItem[],
  viewConstraint:IIndexItemRequest,
  searchKey:string,
  category:string,
	snackbar:{
		isShown:boolean,
		severity:string,
		msg:[]
  },
  addDialog:{
    isShown:boolean,
    title:string,
    dialogNo:string,
    dialogYes:string,
    usingAction:boolean
  },
  categories:ICategory[]
	editDialog:{
		isShown:boolean
	}
}

const initItem={
	id:'',
	name:'',
	description:'',
  price:'',
  category:'',
	owner:'',
	priceDec:0.0,
}

const getInitViewConstraint = () => ({
  owner:jwt_decode(localStorage.getItem("JWT")).sub,
  category:""
})


export class StorePage extends React.Component<Props,any> {
	_isMounted:boolean=false;
	state:IStorePage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
      viewConstraint:getInitViewConstraint(),
      searchKey:"",
      category:"",
      addDialog:{
        isShown:false,
        title:"Sell new item",
				dialogNo:"cancel",
        dialogYes:"yes",
        usingAction:true
      },
      categories:[],
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

	addItem = async (data:IInsertItemRequest) => {
    data  = data as IInsertItemRequest
    data.owner = jwt_decode(localStorage.getItem("JWT")).sub
    // console.log(data)
		await serviceAddItem(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					this.loadAllItems()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['message']
          },
          addDialog:{
            isShown:false
          }
				})
			},
			(err)=>{
				console.log("add item err:"+err);
				this.setErrorSnackbar(err)
			}
    )
    
		this.setState({
			addDialog:{
				isShown:false,
				content:(
					<ItemForm
						submitData = {this.addItem}
						item={initItem}
					/>
				)
			}
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
  
  loadAllCategories = async () => {
		console.log("posting index cate request")
		await serviceIndexCategory().subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["categories"]);
				this.setState({
					categories: res.data["categories"]
				});
			},
			(err)=>{
				console.log("axios err:"+err);
				this.setErrorSnackbar(err)
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
    }, () => console.log(this.state.searchKey))
  }

  searchByCategory = (e) => {
		this.setState({
      category:e.target.value
		})
		console.log(this.state.category)
  }
  
  setSuccessSnackbarEdit = (res) => {
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
  }
  
  setSuccessSnackbarDelete = (res,key) => {
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

	async componentDidMount(){
    this._isMounted = true;			
    if(this._isMounted){
      this.loadAllCategories()
    }
		this.loadAllItems();
	}
	
  componentWillUnmount(){
    this._isMounted=false;
  }
  
	render(){

		if(!localStorage.getItem("JWT")){
			alert("Hello! I am an alert box!!");
			return(
				<Redirect to="/"/>
			)
		}

    let searchKeyword: string = this.state.searchKey
		let selectedCategory : string = this.state.category
		const filteredItems = this.state.rawContent.filter(
			item => {
				if(searchKeyword === null) return 1;
				if(selectedCategory.length > 0 && item.category !== null && item.category !== selectedCategory) return 0;
				return ((item.name != null && item.name.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1
				|| (item.owner != null && item.owner.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1)
				|| (item.description != null && item.description.toLowerCase().indexOf(searchKeyword.toLowerCase()) !== -1))
				)
			}
		)
		return (
			<Dashboard 
			titlePage = {jwt_decode(localStorage.getItem("JWT")).sub+"\'s store"}			
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
              helperText="Enter item's name / description"
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
									<option></option>
									{
										this.state.categories.map(
											(c:ICategory) => {
												return (<option>{c.name}</option>)
											}
										)
									}
								</Input>
							</FormGroup>
            </Box>
          </Box>
          <AlertDialog
              variant="contained"
              buttonSize = "large"
              color="primary"
              size="large"
              parentAllowance = {this.state.addDialog.isShown}
              buttonTitle="Sell New Item"
              parentCallbackOpen={()=>this.setState({addDialog:{isShown:true}})}
              dialogTitle="Sell New Item"
              usingAction={false}
              dialogContent={
                <ItemForm
                  submitData = {this.addItem}
                  item={
                    {}
                  }
                  categories={
                    this.state.categories
                  }
                />
              }
          />
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
																	priceAmount = {c.priceAmount}
                                  description = {c.description}
                                  categories = {this.state.categories}
																	parrentCallbackSuccessDelete = {this.setSuccessSnackbarDelete}
																	parrentCallbackSuccessEdit = {this.setSuccessSnackbarEdit}
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