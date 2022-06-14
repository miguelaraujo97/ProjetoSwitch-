import React, { useContext, useState } from "react";
import { createProfile } from "../context/Actions";
import { URL_API } from "../services/Services";
import AppContext from "../context/AppContext";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from '@mui/material/Alert';

function Profiles() {
  const { state, dispatch } = useContext(AppContext);
  const { profiles } = state;
  const { error, data } = profiles;

  const initialState = { name: "" };
  const [profile, setProfile] = useState(initialState);

  function clickSubmit() {
    console.log(initialState);
    console.log(profile);

    const URL = `${URL_API}/profiles`;

    const request = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(profile),
    };
    // console.log("1 beforeCreate1");
    console.log( JSON.stringify(profile));
    createProfile(URL, request, dispatch);
  }

  //if  um estadpo eslse outro estado
  if (data!== "") {
    return (
      <div>
         <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 2, width: "50ch" },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="profile-description"
          label="New Profile Description"
          onChange={(e) => setProfile({ ...profile, name: e.target.value })}
        />

        <p></p>
        <div style={{padding:20 }} >

          <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
        </div>
        <p></p>
      </Box>
         
         <Alert severity="success">Profile successfully created</Alert>
      </div>
    );
  } else {
    if (error !== null) {
      
      return (
        <div>
          <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 2, width: "50ch" },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="profile-description"
          label="New Profile Description"
          onChange={(e) => setProfile({ ...profile, name: e.target.value })}
        />

        <p></p>
        <div style={{padding:20 }} >

          <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
        </div>
        <p></p>
      </Box>
           
          
           <Alert severity="error">Profile already exists</Alert> 
        </div>
      );
    }
    else{
    return (
      <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 2, width: "50ch" },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="profile-description"
          label="New Profile Description"
          onChange={(e) => setProfile({ ...profile, name: e.target.value })}
        />

        <p></p>
        <div style={{padding:20 }} >

          <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
        </div>
        <p></p>
      </Box>
      
    );
      }
  }
}

export default Profiles;
