import React, {useContext, useEffect} from 'react';
import Stack from "@mui/material/Stack";
import {Link} from "react-router-dom";
import Button from "@mui/material/Button";



function UserStories(){
    return (
        <>
            <h1>User Stories</h1>
            <hr />
            <Stack spacing={2} direction="row">
                <Link to="create">
                    <Button variant="contained" href="#contained-buttons">Create User Story</Button>
                </Link>
                <Link to="product-backlog">
                    <Button variant="contained" href="#contained-buttons">Get Product Backlog</Button>
                </Link>
            </Stack>
        </>
    );
}
export default UserStories;
