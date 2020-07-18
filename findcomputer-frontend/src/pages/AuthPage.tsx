import React from 'react';
import SwipeableViews from 'react-swipeable-views';
import { makeStyles, Theme, useTheme } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import { Container, Paper } from '@material-ui/core';
import { LoginPage } from './LoginPage';
import { RegisterPage } from './RegisterPage';

interface TabPanelProps {
  children?: React.ReactNode;
  dir?: string;
  index: any;
  value: any;
}

const coloredBg:CSS.Properties = {
	background: 'linear-gradient(to right, #03478c, #008033)',
	width:'100%',
	height:'100vh',
	margin:'0'
}

function TabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function a11yProps(index: any) {
  return {
    id: `full-width-tab-${index}`,
    'aria-controls': `full-width-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    backgroundColor: theme.palette.background.paper,
    width: 500,
  },
}));

export function AuthPage(props) {
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event: React.ChangeEvent<{}>, newValue: number) => {
    setValue(newValue);
  };

  const handleChangeIndex = (index: number) => {
    setValue(index);
  };

  return (
		<div style={coloredBg}>
    	<Container  style={{paddingTop:'15%',textAlign:'center'}}>
					<Paper elevation={3} style={{padding:'2%'}}>
						<AppBar position="static" color="default">
								<Tabs
								value={value}
								onChange={handleChange}
								indicatorColor="primary"
								textColor="primary"
								variant="fullWidth"
								aria-label="full width tabs example"
								>
								<Tab label="Login" {...a11yProps(0)} />
								<Tab label="Register" {...a11yProps(1)} />
								</Tabs>
						</AppBar>
						<SwipeableViews
								axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
								index={value}
								onChangeIndex={handleChangeIndex}
						>
								<TabPanel value={value} index={0} dir={theme.direction}>
									<LoginPage/>
								</TabPanel>
								<TabPanel value={value} index={1} dir={theme.direction}>
									<RegisterPage/>
								</TabPanel>
						</SwipeableViews>
					</Paper>
				</Container>
    </div>
  );
}
