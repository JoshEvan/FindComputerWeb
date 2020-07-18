import React from 'react';
import { createStyles, Theme, makeStyles, withStyles } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';
import Button from '@material-ui/core/Button';
import Grid, { GridSpacing } from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import FaceIcon from '@material-ui/icons/Face';
import jwt_decode from 'jwt-decode';
import { serviceDeleteItem } from '../data/services/ItemService';
import { IDeleteItemResponse, HTTPCallStatus } from '../data/interfaces';
import { AlertDialog } from '../components/organism';

const useStyles = theme =>
  ({
    root: {
      width: '100%',
      maxWidth: 'auto',
      backgroundColor: theme.palette.background.paper,
    },
    chip: {
      margin: theme.spacing(0.5),
    },
    section1: {
      margin: theme.spacing(3, 2),
    },
    section2: {
      margin: theme.spacing(2),
    },
    section3: {
      margin: theme.spacing(3, 1, 1),
    },
  })


class ItemDetailPage extends React.Component<any,any> {
  
  constructor(props){
    super(props)
  }
  
 deleteItem = async (key:string) =>  {
  await serviceDeleteItem(key, jwt_decode(localStorage.getItem("JWT")).sub).subscribe(	
    (res:IDeleteItemResponse) => {
      this.props.parrentCallbackDelete(res,key)
      
    },
      (err)=>{
        console.log("delete item err:"+err);
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

  deleteConfirm =  (isYes:boolean, key:string) => {
    if(isYes) this.deleteItem(key);
  }
  

  render(){
    const { classes } = this.props;
  return (
    <div className={classes.root}>
      <div className={classes.section1}>
        <Grid container alignItems="center" spacing-xs-4>
          <Grid item xs>
            <Typography gutterBottom variant="h4" >
              {this.props.name}
            </Typography>
          </Grid>
          <Grid item>
            <Typography gutterBottom variant="h6">
              {this.props.price}
            </Typography>
          </Grid>
        </Grid>
        <Typography color="textSecondary" variant="body2">
          {this.props.description}
        </Typography>
      </div>
      <Divider variant="middle" />
      <div className={classes.section2}>
        <Typography gutterBottom variant="body1">
          Category
        </Typography>
        <div>
          <Chip className={classes.chip} color="primary" label={this.props.category} />
        </div>
        <Typography gutterBottom variant="body1">
          owner
        </Typography>
        <div>
          <FaceIcon color="primary"/> <Chip className={classes.chip} variant="default" label={this.props.owner} />
        </div>
      </div>
      <div className={classes.section3}>
        {
          localStorage.getItem("JWT") !== null && jwt_decode(localStorage.getItem("JWT")).sub !== this.props.owner
          && <Button color="primary">BUY</Button>
        }
        {
          localStorage.getItem("JWT") !== null && jwt_decode(localStorage.getItem("JWT")).sub === this.props.owner
          &&
          <React.Fragment>
            <Button color="primary">EDIT</Button>
            <AlertDialog
              color="secondary"
              usingAction={true}
              parentAllowance = {true}
              param={this.props.id}
              buttonTitle="delete"
              dialogTitle="This following item will be deleted"
              dialogYes="Yes"
              dialogNo="Cancel"
              dialogContent="Are you sure ?"
              parentCallback={ this.deleteConfirm }
            />
          </React.Fragment> 
        }
      </div>
    </div>
  );
  }
}

export default withStyles(useStyles)(ItemDetailPage);