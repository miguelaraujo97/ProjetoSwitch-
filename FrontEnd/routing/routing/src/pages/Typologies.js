import React, {useContext, useState} from "react";
import { createTypology} from "../context/Actions";
import { URL_API } from "../services/Services";
import AppContext from "../context/AppContext";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

function Typologies() {
    const {state, dispatch} = useContext(AppContext)
    const { typologies } = state;
    const { error, success } = typologies;

    const initialState = {
        description: ""
    }

    const [typology, setTypology] = useState(initialState)


    function clickSubmit() {
        const URL = `${URL_API}/typologies`

        const request = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(typology),
        };
        console.log( JSON.stringify(typology));
        createTypology(URL, request, dispatch);
    }

    if (error === true) {
        return (
            <div>
                <h1>"failure"</h1>
            </div>
        );
    } else {
        if (success === true) {
            return (
                <div>
                    <h1>"success"</h1>
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
                    id="typology-description"
                    label="New Typology Description"
                    onChange={(e) => setTypology({ ...typology, description: e.target.value })}
                />

                <p></p>

                <Button onClick={() => clickSubmit()}> Submit</Button>

                <p></p>

            </Box>

        );
    }
}

export default Typologies;
