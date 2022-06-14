import React from 'react';
import { Link, Outlet } from 'react-router-dom'
import { useMatch } from 'react-router-dom';


function Project() {
    const match = useMatch('/projects/:projectCode')
    const id = match.params.projectCode
    return (
        <div>
            <strong>More Info on Project {id} </strong>
            <ul>
                <li>
                    <Link to={`/projects/${id}/user-stories`}>User Stories </Link>
                </li>
                <li>
                    <Link to={`/projects/${id}/edit`}>Edit Project </Link>
                </li>
            </ul>
            <Outlet />
        </div>
    )

}

export default Project;