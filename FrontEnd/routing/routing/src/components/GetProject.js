import React, {useState } from 'react';
import { getProjectByCode } from '../context/Actions';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { Box } from '@mui/system';




function GetProject(props){

    const {data, error}=props.getProjectUpdate;

    const initialState=props.initialState;

    const [getprojectupdate,setGetProjectUpdate] = useState(initialState)


    function clickSubmit(){
        let URL = props.url + `/projects/${(id.projectCode)}`

        const request = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(getprojectupdate)
        }
        getProject(URL,request, props.dispatch )

    }
    
    if (data !== ""){
        return (
            <Alert severity="success">This is a success alert — check it out!</Alert>
            // <h1>Updated profile from user {data.name}</h1>
        )
    } else {
        if (error!== null){
            return(
<Alert severity="error">This is an error alert — check it out!</Alert>            )
        } else{
            return (
                <Box
                component="form"  
                sx={{"& .MuiTextField-root": { m: 2, width: "50ch" },
            }}  noValidate      autoComplete="off">
                    <div>
                        <TextField
                            id="project-code"
                            label="Project Code"
                            onChange={(e) => setProfileUpdate({ ...getprojectupdate, pr: e.target.value })}
                        />
                        <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>                            
                    </div>
                </Box>
            )
        }
    }
}
export default GetProject;