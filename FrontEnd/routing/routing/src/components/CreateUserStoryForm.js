import React, {useEffect, useState} from 'react'
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";




const CreateUserStoryForm = (props) => {
    const {dispatch} = props;
    const [formInfo, setFormInfo] = useState("");


    const handleChange = (event) => {
        const {name, value} = event.target
        const newFormInfo = {...formInfo, [name]: value}
        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch(formInfo)
    }

    const {description} = formInfo;

    return (
        // <form>
        //     <label>User Story Description</label>
        //     <input type="text" name="description" value={ description } onChange={handleChange}/>
        //     <input type="button" value="Submit" onClick={submitForm}/>
        // </form>
    <Box component="form"
      sx={{
        "& .MuiTextField-root": { m: 2, width: "50ch" },
          }}
          noValidate
            autoComplete="off">
         <TextField
          id="US description"
          label="US description"
          name="description" value={ description } onChange={handleChange}
         />
       <p></p>
       <div style={{padding:20 }} >
        <Button variant="contained" onClick={submitForm}>Submit</Button>
      </div>
     </Box>
        
    );

}
export default CreateUserStoryForm;