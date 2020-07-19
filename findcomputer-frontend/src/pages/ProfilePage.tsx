import React from 'react';
import { Formik, useField } from 'formik';
import { TextField, Button, Container, Paper, Backdrop, CircularProgress } from '@material-ui/core';
import * as yup from 'yup';
import { HTTPCallStatus, IUpdateUserRequest } from '../data/interfaces';
import jwt_decode from 'jwt-decode';
import { Redirect } from 'react-router-dom';
import { CustomizedSnackbars } from '../components/organism';
import { TextAreaWValidation } from '../components/molecules';
import { Dashboard } from '../components/template/Dashboard';
import { serviceShowUser, serviceUpdateUser } from '../data/services/AuthService';

const validationSchema = yup.object({
	username: yup.string().required("Username must be filled"),
  password: yup.string().required("Password must be filled"),
  newPassword: yup.string().required("New Password must be filled"),
  newPasswordConfirmation: yup.string()
  .oneOf([yup.ref('newPassword'), null], "new password not match")
  .required('New Password Confirmation is required')
})

const TextFieldWValidation:any = ({placeholder,type, disabled,...props}) => {
  const [field, meta] = useField<{}>(props); 
	const errorText = meta.error && meta.touched ? meta.error : "";
	return(
		<TextField label={placeholder} type={type} InputLabelProps={props.InputLabelProps}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" disabled={disabled}/>
	)
}

export class ProfilePage extends React.Component<any,any>{
	
	closeSnackbar = () => {
		this.setState({
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			pass:false
		});
  }

  loadUser = async (username:string) => {
    await serviceShowUser(username).subscribe(
      (res) => {
        console.log(res)
        console.log(res.data["username"])
        this.setState({
          user:{
            username:res.data["username"],
            profileInfo:res.data["profileInfo"]
          }
        })
      },
      (err) => {
        this.setSnackbarError(err)
      }
    )
  }
  
  setSnackbarError = (err) => {
    this.setState({
      snackbar:{
        isShown:true,
        severity:"error",
        msg:err.message.split()
      }
    })
  }

  componentDidMount(){
    this.loadUser(jwt_decode(localStorage.getItem("JWT")).sub)
  }
  
  submitUpdateUser = async (dataPayload : IUpdateUserRequest) => {
    console.log("updating user")
    dataPayload = {
      username:dataPayload.username,
      password:dataPayload.password,
      newPassword:dataPayload.newPassword,
      profileInfo:dataPayload.profileInfo.replace("\'", "\\'").replace("\"",String.raw`"\\""`)
    }
    await serviceUpdateUser(dataPayload).subscribe(
      (res) => {
        console.log("response received")
        this.setState({
          snackbar:{
            isShown:true,
            severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
            msg:res.data['message']
          }
        })
      },
      (err) => {
        this.setSnackbarError(err)
      }
    )
  }

	constructor(props){
		super(props)
		this.state={
      user:{
        username:"",
        profileInfo:"",
      },
			snackbar:{
				isShown:false,
				msg:"",
				parentCallback:this.closeSnackbar()
			}
		}
	}

	render(){
    if(!localStorage.getItem("JWT")){
			return  <Redirect to="/" />
    }
    let user = this.state.user
		return (
			<Dashboard 
						titlePage={jwt_decode(localStorage.getItem("JWT")).sub+"'s profile"}
						content={
              
              <Container  style={{textAlign:'center'}}>
                {
                  this.state.user.username === "" &&
                  <Backdrop style={{zIndex: '1',color:'#fff'}} open={true}>
                    <CircularProgress color="inherit" />
                  </Backdrop>
                }
                <Paper elevation={0} style={{padding:'2%'}}>
                  {this.state.snackbar.isShown && 
                    <span style={{width:"fit-content"}}>
                      <CustomizedSnackbars
                        severity={this.state.snackbar.severity}
                        msg={this.state.snackbar.msg}
                        parentCallback={this.closeSnackbar}
                        />
                    </span>
                  }
                  <Formik
                    enableReinitialize={true}
                    initialValues={{
                        username:user.username,
                        password: '',
                        newPassword:'',
                        newPasswordConfirmation:'',
                        profileInfo:user.profileInfo.replace("\\'","\'",).replace(String.raw`"\\""`,"\"")
                    }}
                    onSubmit = {(data, { setSubmitting }) => {
                        setSubmitting(true)
                        console.log("SUBMITTING")

                        this.submitUpdateUser(data);
                        
                        setSubmitting(false);
                    }}
                    validationSchema = {validationSchema}
                  >
                  {
                  ({ isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
                  
                  <form onSubmit={handleSubmit}>
                      {!this.props.isEdit && <div style={{padding:'2%'}}>
                          <TextFieldWValidation
                              disabled={true}
                              placeholder="username"
                              name="username" 
                              type="input" 
                              defaultValue={user.username}
                              as={TextField}/>
                      </div>}
                      <div style={{padding:'2%'}}>
                          <TextFieldWValidation
                              placeholder="old password"
                              name="password"
                              type="password"
                              as={TextField}
                              />
                      </div>
                      <div style={{padding:'2%'}}>
                          <TextFieldWValidation
                              placeholder="new password"
                              name="newPassword"
                              type="password"
                              as={TextField}
                              />
                      </div>
                      <div style={{padding:'2%'}}>
                          <TextFieldWValidation
                              placeholder="new password confirm"
                              name="newPasswordConfirmation"
                              type="password"
                              as={TextField}
                              />
                      </div>
                      <div style={{padding:'2%'}}>
                        <TextAreaWValidation
                          placeholder="user profile status"
                          name="profileInfo"
                          type="input"
                          />
                      </div>
                      
                      <Button disabled={isSubmitting} type="submit">
                          update profile
                      </Button>
                      
                  </form>
                  )}
            </Formik>
          </Paper>
        </Container>
        }
      />
	)
	}
}
