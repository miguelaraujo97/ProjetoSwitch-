import React, { useContext, useEffect, useState } from 'react';
import AppContext from '../context/AppContext';
import { Link, Outlet } from "react-router-dom";
import UsersTable from '../components/UsersTable';
import { URL_API } from '../services/Services';
import { searchUserProfile } from '../context/Actions';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Stack from '@mui/material/Stack';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';




function Users() {
  const { state, dispatch } = useContext(AppContext);
  const { users } = state;

  const [profile, setProfile] = useState("");

  // function clickSubmitProfiles() {
  //   let URL = URL_API + `/users/by-profile/` + profile;
  //   console.log(URL)

  //   const request = {
  //     method: "GET",
  //     headers: {
  //       "Content-Type": "application/json",
  //     }
  //   };
  //   searchUserProfile(URL, request, dispatch);
  // }

  // useEffect(() => {
  //   const URL = URL_API + "/users/by-profile/Visitor";
  //   const request = {
  //     method: "GET",
  //     headers: {
  //       "Content-Type": "application/json",
  //     }
  //   };
  //   searchUserProfile(URL, request, dispatch);

  // }, [dispatch]);
  const handleChange = (event) => {
    setProfile(event.target.value);
  };

  useEffect(() => {
    if (profile) {
      let URL = URL_API + `/users/by-profile/` + profile;
      console.log(URL)

      const request = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      };
      searchUserProfile(URL, request, dispatch);
    }
  }
    , [profile, dispatch]);

  return (
    <div>
      <h1>USERS</h1>
      <hr />

      <Stack spacing={2} direction="row">
        <Link to="/users/create"  >
          <Button variant="contained" href="#contained-buttons">Create User</Button>
        </Link>
      </Stack>

      <hr />

      <Stack spacing={2} direction="row">
        <Select
          labelId="profile"
          id="Profile"
          value={profile}
          label="Profile"
          onChange={handleChange}
        >
          <MenuItem value={"Visitor"}>Visitor</MenuItem>
          <MenuItem value={"Administrator"}>Administrator</MenuItem>
        </Select>

        {/* <TextField
          id="email"
          label="Email"
          onChange={(e) => setProfile(e.target.value)}
        />
        <Button variant="contained" onClick={() => clickSubmitProfiles()}> Submit</Button> */}
      </Stack>
      <UsersTable users={users} />
      <Outlet />
    </div>
  );
}

export default Users;
