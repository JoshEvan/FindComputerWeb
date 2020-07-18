import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars, OutlinedCard } from '../components/organism';
import { IItem, IIndexItemRequest, IDeleteItemResponse, HTTPCallStatus, IUpsertItemRequest, IUpsertItemResponse} from '../data/interfaces';
import { serviceIndexItem, getCurrentDate } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Button, Paper, Card, CardContent, Typography, Box, TextField } from '@material-ui/core';
import { async } from 'rxjs/internal/scheduler/async';
import { serviceDeleteItem, serviceAddItem, serviceEditItem } from '../data/services/ItemService';
import { ItemForm } from '../components/organism/form';
import ItemDetailPage from './ItemDetailPage';
import jwt_decode from 'jwt-decode';

interface Props extends RouteComponentProps{};

interface IStorePage{
	rawContent:IItem[],
  viewConstraint:IIndexItemRequest,
  searchKey:string,
	snackbar:{
		isShown:boolean,
		severity:string,
		msg:[]
	},
	editDialog:{
		isShown:boolean
	}
}

const colName: string[] = ["NUM","ITEM CODE", "NAME", 
"DESCRIPTION", "PRICE", "STOCK", "CAPACITY","TOTAL SOLD", "GENERATED INCOME","ACTION"]

const initItem={
	itemCode:'',
	name:'',
	description:'',
	price:0,
	stock:0,
	capacity:0,
}

const getInitViewConstraint = () => ({
  owner:jwt_decode(localStorage.getItem("JWT")).sub,
  category:""
})


export class StorePage extends React.Component<Props,any> {
	
	state:IStorePage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
      viewConstraint:getInitViewConstraint(),
      searchKey:"",
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

	addItem = async (data:IUpsertItemRequest) => {
		await serviceAddItem(data).subscribe(
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
				console.log("add item err:"+err);
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
				console.log(this.state.rawContent[0].itemCode);
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
    }, () => console.log(this.state.searchKey))
  }

	async componentDidMount(){
		this.loadAllItems();
	}

	render(){
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

            </Box>
          </Box>
          <Box display="flex" flexWrap="wrap">
          {
							this.state.rawContent.map(
							(c:IItem, idx:number) => {
                if((c.description.includes(this.state.searchKey) 
                || c.name.includes(this.state.searchKey)))
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