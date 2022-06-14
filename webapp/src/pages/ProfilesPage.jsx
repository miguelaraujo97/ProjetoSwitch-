import React, { useContext, useState } from "react";
import { createProfile } from "../context/actions/ProfileActions";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from '@mui/material/Alert';
import TitleHeader from '../components/HeaderPageWithButton';
import { useNavigate } from "react-router-dom";

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
    console.log(JSON.stringify(profile));
    createProfile(URL, request, dispatch);
  }

  //if  um estadpo eslse outro estado
  if (data !== "") {
    return (
      <div>

        <TitleHeader
          title="Profiles"
          buttonName="Create"
          color="success"
          route=""
        />

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
          <div style={{ padding: 20 }} >

            <Button variant="contained" onClick={() => clickSubmit()}> Create</Button>
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
          <TitleHeader
            title="Profiles"
            buttonName="Create"
            route=""
          />
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
            <div style={{ padding: 20 }} >

              <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
            </div>
            <p></p>
          </Box>


          <Alert severity="error">Profile already exists</Alert>
        </div>
      );
    }
    else {
      return (
        <>
          <TitleHeader
            title="Profiles"
            buttonName="Create"

            color="success"
            route=""
          />
          <Box
            component="form"
            sx={{
              "& .MuiTextField-root": { m: 2, width: "50ch" },
            }}
            noValidate
            autoComplete="off"
          >


            <h2>Profiles list and create form</h2>

            {/* <TextField
              id="profile-description"
              label="New Profile Description"
              onChange={(e) => setProfile({ ...profile, name: e.target.value })}
            />
            <Button 
            variant="outlined" 
            onClick={() => clickSubmit()}> Create</Button> */}

            {/* <div style={{ padding: 20 }} >

              <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
            </div> */}
            <p></p>
          </Box>
        </>
      );
    }
  }
}

export default Profiles;
