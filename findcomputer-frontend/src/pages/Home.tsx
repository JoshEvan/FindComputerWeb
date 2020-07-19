import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { SpecialButton } from '../components/molecules/SpecialButton';
import { Dashboard } from '../components/template/Dashboard';
import CSS from 'csstype'
import { Typography, TextField, Button, makeStyles, Theme, createStyles } from '@material-ui/core';

interface Props extends RouteComponentProps{};

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    margin: {
      margin: theme.spacing(1),
    },
    extendedIcon: {
      marginRight: theme.spacing(1),
    },
  }),
);
const plainLinkStyle: CSS.Properties = {
  textDecoration: "none",
  color:'#000'
}

export const Home: React.FC<Props> = ({ history,location,match }) => {
	const classes = useStyles();
		return (
			<div>
					<Dashboard 
						titlePage=""
						content={
						<div>
							<br/>
							<div style={{display:'table'}}>
								<div style={{float: 'left', width:'25%'}}>
									<img id="loading-img" style={{width:'70%', height:'auto'}} src="../assets/imgs/logo.png"/>
								</div>
								<div style={{float: 'left', width:'75%'}}>
								<Typography variant="h3" component="h2" gutterBottom>
									Find Computer
								</Typography>
								<Typography variant="overline" display="block" gutterBottom>
									One Stop Solution Marketplace For Computers
								</Typography>
								<Link to="/items" style={plainLinkStyle}>
									<Button size="large" color="primary" className={classes.margin} variant="contained">
										Buy Something
									</Button>
								</Link>
								<Link to="/store" style={plainLinkStyle}>
									<Button size="large" color="primary" className={classes.margin} variant="outlined">
										Sell Something
									</Button>
								</Link>
								</div>
							</div>
						</div>}
					/>
			</div>
    )
};
