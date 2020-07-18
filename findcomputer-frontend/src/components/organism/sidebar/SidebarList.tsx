import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import DashboardIcon from '@material-ui/icons/Dashboard';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import PeopleIcon from '@material-ui/icons/People';
import BarChartIcon from '@material-ui/icons/BarChart';
import LayersIcon from '@material-ui/icons/Layers';
import ReceiptIcon from '@material-ui/icons/Receipt';
import LocalAtmIcon from '@material-ui/icons/LocalAtm';
import AssignmentIcon from '@material-ui/icons/Assignment';
import { Link } from 'react-router-dom';
import { makeStyles } from '@material-ui/core/styles'
import CSS from 'csstype'

// const useStyles = makeStyles(theme => ({
//   plainLink:{
//     textDecoration: "none",
//     color:'#000'
//   }})
// );


const plainLinkStyle: CSS.Properties = {
  textDecoration: "none",
  color:'#000'
}

export const mainListItems = (
  <div>
      <Link to="/" style={plainLinkStyle}>
        <ListItem button>
          <ListItemIcon>
            <DashboardIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItem>
      </Link>
      <Link to="/items"  style={plainLinkStyle}>
          <ListItem button>
          <ListItemIcon>
              <ShoppingCartIcon />
          </ListItemIcon>
          <ListItemText primary="Marketplace" />
          </ListItem>
      </Link>
      <Link to="/transactions" style={plainLinkStyle}>
        <ListItem button>
          <ListItemIcon>
            <LocalAtmIcon />
          </ListItemIcon>
          <ListItemText primary="My Shop" />
        </ListItem>
      </Link>
      {/* <Link to="/customers" style={plainLinkStyle}>
          <ListItem button>
          <ListItemIcon>
              <PeopleIcon />
          </ListItemIcon>
          <ListItemText primary="Customers" />
          </ListItem>
      </Link>
    </div>}
    <Link to="/productions" style={plainLinkStyle}>
      <ListItem button>
        <ListItemIcon>
          <LayersIcon />
        </ListItemIcon>
        <ListItemText primary="Productions" />
      </ListItem>
    </Link>
    <Link to="/payments" style={plainLinkStyle} >
    <ListItem button>
      <ListItemIcon>
        <ReceiptIcon />
      </ListItemIcon>
      <ListItemText primary="Payment types" />
    </ListItem>
    </Link> */}
  </div>
);
