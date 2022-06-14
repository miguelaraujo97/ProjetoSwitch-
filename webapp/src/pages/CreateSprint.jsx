import React, { useContext } from 'react';
import { useParams } from 'react-router-dom';
import AppContext from "../context/AppContext";
import CreateSprintForm from '../components/CreateSprintForm';
import { createSprint } from '../context/actions/SprintActions';
import { URL_API } from '../services/Service';
import Alert from '@mui/material/Alert';


function CreateSprint() {

    const { state, dispatch } = useContext(AppContext);
    const { sprint } = state;
    const { error, data } = sprint;
    const id = useParams();

    const submitForm = (formInfo) => {

        const url = `${URL_API}/projects/${(id.projectCode)}/sprints`;
        const request = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }

        createSprint(url, request, dispatch);
    }

    if (error !== null) {
        return (
            <div>
                    <p />
                    <CreateSprintForm dispatch={submitForm} />
                    <Alert severity="error">This is an error alert — check it out!</Alert>
                </div>
            
        );
    } else {
        if (data !== "") {
            return (
                <div>
                <p />
                <CreateSprintForm dispatch={submitForm} />
                <Alert severity="success">This is a success alert — check it out!</Alert>
            </div>
            );
        } else {
            return (
                <>
                    <div>
                        <h5 style={{ marginBottom: "16px"}}>Create a sprint</h5>
                        <CreateSprintForm dispatch={submitForm} />
                    </div>
                </>
            );
        }
    }
};

export default CreateSprint;