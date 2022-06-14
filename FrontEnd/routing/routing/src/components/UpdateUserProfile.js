import React, { useState } from 'react';
import { updateProfile } from '../context/Actions';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { Box } from '@mui/system';
import Alert from '@mui/material/Alert';
import Autocomplete from '@mui/material/Autocomplete';





function UpdateUserProfile(props) {
    //const { state, dispatch} = useContext(AppContext);
    //const {profileUpdate} = state;

    const { data, error } = props.profileUpdate;

    const initialState = props.initialState;

    const [profileupdate, setProfileUpdate] = useState(initialState)

    const top100Films = ['Visitor', 'Administrator', 'Director']

    function clickSubmit() {
        let URL = props.url + `/users/updateprofile`

        const request = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(profileupdate)
        }
        updateProfile(URL, request, props.dispatch)

    }

    if (data !== "") {
        return (
            <Alert severity="success">This is a success alert — check it out!</Alert>
            // <h1>Updated profile from user {data.name}</h1>
        )
    } else {
        if (error !== null) {
            return (
                <Alert severity="error">This is an error alert — check it out!</Alert>)
        } else {
            return (
                <Box
                    component="form"
                    sx={{
                        "& .MuiTextField-root": { m: 2, width: "50ch" },
                    }} noValidate autoComplete="off">
                    <div>
                        <TextField
                            id="profile-id"
                            label="Profile ID"
                            onChange={(e) => setProfileUpdate({ ...profileupdate, profileID: e.target.value })}
                        />
                        <TextField
                            id="profile-user"
                            label="User ID"
                            onChange={(e) => setProfileUpdate({ ...profileupdate, userID: e.target.value })}
                        />
                        <Button variant="contained" onClick={() => clickSubmit()}> Submit</Button>
                    </div>
                </Box>
            )
        }
    }
}
export default UpdateUserProfile;