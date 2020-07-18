import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel } from '@material-ui/core';
import * as yup from 'yup';
import { ICategory } from '../../../data/interfaces';


const validationSchema = yup.object({
	name: yup.string().required("Item Name must be filled"),
	price: yup.number().required("Item Price must be filled"),
	category: yup.string().required("Item Category must be filled")
})

const TextFieldWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,type,...props}) => {
	const [field, meta] = useField<{}>(props); // hook dari formik
	const errorText = meta.error && meta.touched ? meta.error : "";
	return(
		<TextField label={placeholder} type={type}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
	)
}

const SelectWValidation:any = ({...props}) => {
	const [field, meta] = useField<{}>(props); 
	const errorText = meta.error && meta.touched ? meta.error : "";
	console.log(meta, "METAFROMCONTROL");
return(
	<Select {...props}
		{...field} helperText={errorText} error={!!errorText} variant="outlined" />
)
}

const TextAreaWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,...props}) => {
	const [field, meta] = useField<{}>(props);
	const errorText = meta.error && meta.touched ? meta.error : "";
	return(
		<TextareaAutosize aria-label={placeholder} rowsMin={3} placeholder={placeholder} {...field} 
		helperText={errorText} error={!!errorText} />
	)
}

export class ItemForm extends React.Component<any,any>{
	render(){
		return (
			<div>
				<Formik
					initialValues={{
						id:this.props.item.id,
						description:this.props.item.description,
						name:this.props.item.name,
						price:this.props.item.price,
						category:this.props.item.category,
						errors:'',
						values:''
					}}
					
					onSubmit = {(data, { setSubmitting }) => {
						// setSubmitting itu untuk async request ke server dan sembari disable button (ini function)
						setSubmitting(true)
						console.log(data);

						this.props.submitData(data);
						
						setSubmitting(false);
						console.log("done submit add data")
					}}
					
					validationSchema = {validationSchema}
				>
					{
					({ errors, values, isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
				
					<form onSubmit={handleSubmit}>
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item name"
								name="name" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item price"
								name="price" 
								type="number" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextAreaWValidation
								placeholder="item description"
								name="description"
								type="input"
								// as={TextareaAutosize}	
								/>
						</div>
						<div style={{padding:'2%'}}>   
							<FormControl
									variant="outlined" style={{width:"100%"}}>
									<InputLabel htmlFor="outlined-age-native-simple">item categories</InputLabel>
									<SelectWValidation
										inputProps={{ displayEmpty:true}}
										name="category"
										label="item categories">
											<option aria-label="None" value=""/>
											{
												this.props.categories.map(
														(e:ICategory) => {
															return <option value={e.name}
															selected={
																(e.name === this.props.item.category && this.props.isEdit)
															}>{e.name}</option>
														}
												)
											}
										</SelectWValidation>
									</FormControl>
							</div>

						<Button disabled={isSubmitting} type="submit">
							{/* diable button saat nunggu request */}
							submit
						</Button>

					</form>
					)
					}
				</Formik>
			</div>
		)
	}
}