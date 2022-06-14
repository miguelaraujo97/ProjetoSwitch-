import React, { useContext, useEffect, useState } from "react";
import { useMatch } from "react-router-dom";
import AppContext from '../context/AppContext';
import NavTabPane from "../components/NavTabPane";
import ProjectInfoHeader from "../components/ProjectsInfoHeader";

import { fetchProjectByCode } from '../context/actions/FetchProjectActions';
import { URL_API } from '../services/Service';

import TitleHeader from "../components/HeaderPageWithButton";


function ProjectInfo(props) {

    //// PROJECT DETAILS INFORMATION

    
    const { state, dispatch } = useContext(AppContext);
    const { activeProjectCode, projectByCode } = state;
    const { loading, error, data } = projectByCode;

    const match = useMatch('/projects/:projectCode')
    const actualProjectCode = activeProjectCode != null ? activeProjectCode : match.params.projectCode;


    useEffect(() => {

        const URL = URL_API + "/projects/"+actualProjectCode;
        const request = { method: "GET",
            headers: { "Content-Type": "application/json",  }
        };
        fetchProjectByCode(URL, request, dispatch);

    }, [dispatch]);


    const projectName = data[0] != null ? data[0].name : "";
    //const projectCode = data[0] != null ? data[0].code : "";



    return (
        <>
            <TitleHeader
                title={projectName}
                buttonName="Back to projects"
                route="/projects"
            />

            <ProjectInfoHeader />
            
            <NavTabPane />
        </>
    );
}

export default ProjectInfo;