import React, { useContext, useState } from "react";
import { createUser } from "../context/Actions";
import { URL_API } from "../services/Services";
import AppContext from "../context/AppContext";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from '@mui/material/Alert';

function CreateUser() {
  const { state, dispatch } = useContext(AppContext);
  const { users } = state;
  const { error, data } = users;

  const initialState = { userName: "", email: "", function: "", password: "", photo:"" };
  const [user, setUser] = useState(initialState);

  function clickSubmit() {
    
    const URL = `${URL_API}/users`;

    const request = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    };
    createUser(URL, request, dispatch);
  }

  if (data !== "") {
  
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
          id="user-name"
          label="User Name"
          onChange={(e) => setUser({ ...user, userName: e.target.value })}
        />

        <TextField
          id="user-email"
          label="Email"
          onChange={(e) => setUser({ ...user, email: e.target.value })}
        />

        <TextField
          id="user-function"
          label="Function"
          onChange={(e) => setUser({ ...user, function: e.target.value })}
        />

        <TextField
          id="user-password"
          label="Password"
          onChange={(e) => setUser({ ...user, password: e.target.value })}
        />

        <TextField
          id="user-photo"
          label="Photo"
          onChange={(e) => setUser({ ...user, photo: e.target.value })}
        />

        <p></p>

        <Button onClick={() => clickSubmit()}> Submit</Button>

        <p></p>
      </Box>
      <Alert severity="success">User successfully created</Alert>
      </div>
    )
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
          id="user-name"
          label="User Name"
          onChange={(e) => setUser({ ...user, userName: e.target.value })}
        />

        <TextField
          id="user-email"
          label="Email"
          onChange={(e) => setUser({ ...user, email: e.target.value })}
        />

        <TextField
          id="user-function"
          label="Function"
          onChange={(e) => setUser({ ...user, function: e.target.value })}
        />

        <TextField
          id="user-password"
          label="Password"
          onChange={(e) => setUser({ ...user, password: e.target.value })}
        />

        <TextField
          id="user-photo"
          label="Photo"
          onChange={(e) => setUser({ ...user, photo: e.target.value })}
        />

        <p></p>

        <Button onClick={() => clickSubmit()}> Submit</Button>

        <p></p>
      </Box>
          <Alert severity="error">Profile already exists</Alert> 
        </div>
      );
    }
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
          id="user-name"
          label="User Name"
          onChange={(e) => setUser({ ...user, userName: e.target.value })}
        />

        <TextField
          id="user-email"
          label="Email"
          onChange={(e) => setUser({ ...user, email: e.target.value })}
        />

        <TextField
          id="user-function"
          label="Function"
          onChange={(e) => setUser({ ...user, function: e.target.value })}
        />

        <TextField
          id="user-password"
          label="Password"
          onChange={(e) => setUser({ ...user, password: e.target.value })}
        />

        <TextField
          id="user-photo"
          label="Photo"
          onChange={(e) => setUser({ ...user, photo: e.target.value })}
        />

        <p></p>

        <Button onClick={() => clickSubmit()}> Submit</Button>

        <p></p>
      </Box>
      
    );
  }
}

export default CreateUser;
