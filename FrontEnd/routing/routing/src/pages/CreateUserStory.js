import React, {useContext, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import AppContext from "../context/AppContext";
import CreateUserStoryForm from '../components/CreateUserStoryForm';
import {submitUserStoryCreationForm} from "../context/Actions";
import {URL_API} from '../services/Services.js'

function CreateUserStory()  {

    const {state, dispatch} = useContext(AppContext);
    const { userStories } = state;
    const {error, success } = userStories
    const id = useParams();

    const submitForm = (formInfo) => {

        const url = `${URL_API}/projects/${(id.projectCode)}/user-stories`;
        const request = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formInfo)
        }

        console.log(url);
        console.log(request.body);
        console.log(formInfo)
        submitUserStoryCreationForm(url, request, dispatch);
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
            <>
                <div>
                    <p></p>
                    <CreateUserStoryForm dispatch={submitForm}/>
                </div>
            </>
        );

    }};

export default CreateUserStory;