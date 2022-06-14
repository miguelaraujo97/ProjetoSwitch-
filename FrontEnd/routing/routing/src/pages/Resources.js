import React, {useContext, useState} from "react";

import {createResource} from "../context/Actions";
import {URL_API} from "../services/Services";
import AppContext from "../context/AppContext";

import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from '@mui/material/Alert';

function Resources() {
    const {state, dispatch} = useContext(AppContext);
    const {profiles} = state;
    const {error, data} = profiles;

    const initialState = {
        projectCode: "",
        email: "",
        startDate: "",
        endDate: "",
        percentageAllocation: "",
        costPerHour: "",
        role: ""
    };
    const [resource, setResource] = useState(initialState);


    function clickSubmit() {
        // console.log(initialState);
        // console.log(profile);

        const URL = `${URL_API}/resources`;

        const request = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(resource),
        };
        // console.log("1 beforeCreate1");
        createResource(URL, request, dispatch);
    }

    //if  um estadpo eslse outro estado
    if (data !== "") {
        return (
            <div>
                <Box
                    component="form"
                    sx={{
                        "& .MuiTextField-root": {m: 2, width: "50ch"},
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        id="project-code"
                        label="Project Code"
                        onChange={(e) => setResource({
                            ...resource,
                            projectCode: e.target.value
                        })}
                    />

                    <TextField
                        id="user-email"
                        label="User Email"
                        onChange={(e) => setResource({
                            ...resource,
                            email: e.target.value
                        })}
                    />

                    <input
                        id="start-date"
                        label="Start Date"
                        type="date"
                        onChange={(e) => setResource({
                            ...resource,
                            startDate: e.target.value
                        })}
                    />

                    <input
                        id="end-date"
                        label="End Date"
                        type="date"
                        onChange={(e) => setResource({
                            ...resource,
                            endDate: e.target.value
                        })}
                    />

                    <TextField
                        id="percentage-of-allocation"
                        label="Percentage of Allocation"
                        onChange={(e) => setResource({
                            ...resource,
                            percentageAllocation: e.target.value
                        })}
                    />

                    <TextField
                        id="cost-per-hour"
                        label="Cost per Hour"
                        onChange={(e) => setResource({
                            ...resource,
                            costPerHour: e.target.value
                        })}
                    />

                    <TextField
                        id="role"
                        label="Role"
                        onChange={(e) => setResource({
                            ...resource,
                            role: e.target.value
                        })}
                    />

                    <p></p>

                    <Button onClick={() => clickSubmit()}> Submit</Button>

                    <p></p>
                </Box>
                <Alert severity="success">Resource successfully created</Alert>
            </div>
        );
    } else {
        if (error !== null) {
            return (
                <div>
                    <Box
                        component="form"
                        sx={{
                            "& .MuiTextField-root": {m: 2, width: "50ch"},
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <TextField
                            id="project-code"
                            label="Project Code"
                            onChange={(e) => setResource({
                                ...resource,
                                projectCode: e.target.value
                            })}
                        />

                        <TextField
                            id="user-email"
                            label="User Email"
                            onChange={(e) => setResource({
                                ...resource,
                                email: e.target.value
                            })}
                        />

                        <input
                            id="start-date"
                            label="Start Date"
                            type="date"
                            onChange={(e) => setResource({
                                ...resource,
                                startDate: e.target.value
                            })}
                        />

                        <input
                            id="end-date"
                            label="End Date"
                            type="date"
                            onChange={(e) => setResource({
                                ...resource,
                                endDate: e.target.value
                            })}
                        />

                        <TextField
                            id="percentage-of-allocation"
                            label="Percentage of Allocation"
                            onChange={(e) => setResource({
                                ...resource,
                                percentageAllocation: e.target.value
                            })}
                        />

                        <TextField
                            id="cost-per-hour"
                            label="Cost per Hour"
                            onChange={(e) => setResource({
                                ...resource,
                                costPerHour: e.target.value
                            })}
                        />

                        <TextField
                            id="role"
                            label="Role"
                            onChange={(e) => setResource({
                                ...resource,
                                role: e.target.value
                            })}
                        />

                        <p></p>

                        <Button onClick={() => clickSubmit()}> Submit</Button>

                        <p></p>
                    </Box>
                    <Alert severity="error">Couldn't add resource</Alert>
                </div>
            );
        }
        return (
            <Box
                component="form"
                sx={{
                    "& .MuiTextField-root": {m: 2, width: "50ch"},
                }}
                noValidate
                autoComplete="off"
            >
                <TextField
                    id="project-code"
                    label="Project Code"
                    onChange={(e) => setResource({
                        ...resource,
                        projectCode: e.target.value
                    })}
                />

                <TextField
                    id="user-email"
                    label="User Email"
                    onChange={(e) => setResource({
                        ...resource,
                        email: e.target.value
                    })}
                />

                <input
                    id="start-date"
                    label="Start Date"
                    type="date"
                    onChange={(e) => setResource({
                        ...resource,
                        startDate: e.target.value
                    })}
                />

                <input
                    id="end-date"
                    label="End Date"
                    type="date"
                    onChange={(e) => setResource({
                        ...resource,
                        endDate: e.target.value
                    })}
                />

                <TextField
                    id="percentage-of-allocation"
                    label="Percentage of Allocation"
                    onChange={(e) => setResource({
                        ...resource,
                        percentageAllocation: e.target.value
                    })}
                />

                <TextField
                    id="cost-per-hour"
                    label="Cost per Hour"
                    onChange={(e) => setResource({
                        ...resource,
                        costPerHour: e.target.value
                    })}
                />

                <TextField
                    id="role"
                    label="Role"
                    onChange={(e) => setResource({
                        ...resource,
                        role: e.target.value
                    })}
                />

                <p></p>

                <Button onClick={() => clickSubmit()}> Submit</Button>

                <p></p>
            </Box>

        );
    }
}

export default Resources;
