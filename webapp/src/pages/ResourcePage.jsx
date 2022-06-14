/// CONTEXT
import React, { useContext, useState, useEffect } from "react";
import AppContext from "../context/AppContext";
import { URL_API } from '../services/Service';

import { fetchResources } from '../context/actions/ResourcesActions';


/// SERVICES
///Actions
/// COMPONENTS

// 
import { useMatch } from "react-router-dom";

//import { FormControl, TextField } from "@mui/material";


// BUTTON ERASE
/// TIME
import '../assets/css/Projects.css'
import ResourcesTable from "../components/ResourcesTable";
import CollapseButtonCreateResource from "../components/CollapseButtonCreateResource";
import CollapseButtonEditResource from "../components/CollapseButtonEditResource";

import ResourceForm from "../components/ResourceForm";
import Alert from '@mui/material/Alert';



function ResourcePage(props) {
    const { state, dispatch } = useContext(AppContext);
    const { createResource } = state;
    const { resources } = state;
    const dataResources = resources.data;
    const { error, success } = createResource;
    const { data } = createResource;
    const match = useMatch('/projects/:projectCode')
    const actualProjectCode = match.params.projectCode;



    const [resourceEdition, setEdition] = useState(false);
    const [resourceID, setResourceID] = useState("");
    const [resourceToEdit, setResourceToEdit] = useState("")


    useEffect(() => {

        const URL = URL_API + '/projects/' + actualProjectCode + '/resources';

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchResources(URL, request, dispatch);

    }, [dispatch, resourceToEdit, actualProjectCode]);


    useEffect(() => {

        (dataResources[0] ? dataResources[0] : []).forEach((element) => (
            (element.resourceId === resourceID) ? setResourceToEdit(element) : ""
        ));
        if (resourceToEdit) {
            setEdition(true);
        }

    }
        , [resourceID, resourceToEdit, dataResources]);



    const setResourceIDFromForm = (resID) => {
        setResourceID(resID);
    }
     
    const cleanResourceToEdit = (res)=>{
        setResourceToEdit(res);
        setResourceID(res);
    }


    const editResource =
        <ResourceForm purpose="Edit resource"
            data={resourceToEdit}
            projectCode={actualProjectCode}
            dispatch={dispatch} />

    let message = "";
    if (error !== null) {
        message = <Alert severity="error">This is an error alert — check it out!</Alert>
            ;
    } else {
        if (data !== "") {
            message = <Alert severity="success">This is a success alert — check it out!</Alert>
        }
    }

    if (error === true) {
        console.log("failure");
        return (
            <div>
                <h1>"failure"</h1>
            </div>

        );
    } else {
        if (success === true) {
            console.log("success");
            return (
                <div>
                    <h1>"success"</h1>
                </div>
            );
        }
        return (

            <>
                <CollapseButtonCreateResource data={data}
                    projectCode={actualProjectCode}
                    dispatch={dispatch} />
                <hr />
                {/* {resourceEdition ? editResource : ""} */}
                {resourceEdition ? <CollapseButtonEditResource data={data}
                    projectCode={actualProjectCode}
                    dispatch={dispatch}
                    resourceToEdit={resourceToEdit} /> : ""}
                {message}

                <ResourcesTable state={state}
                    setResourceID={setResourceIDFromForm}
                    cleanResourceToEdit={cleanResourceToEdit}
                    actualProjectCode={actualProjectCode} />
            </>
        );
    }




}
export default ResourcePage;
