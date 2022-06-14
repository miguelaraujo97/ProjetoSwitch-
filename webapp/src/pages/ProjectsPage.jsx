import React, { useState } from 'react';
import ProjectsTable from "../components/ProjectsTable";

import '../assets/css/Projects.css'
import TitleHeader from '../components/HeaderPageWithButton';

function Projects() {

    ////// PROJECTS LIST

    return (
        <div>
            <TitleHeader
                title="Projects"
                buttonName="Create"
                color="success"
                route="/projects/create"
            />

            <ProjectsTable />
        </div>
    );
}

export default Projects;