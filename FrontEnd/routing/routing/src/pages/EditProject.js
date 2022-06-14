import React, { useContext, useState } from "react";
import { editProject } from "../context/Actions";
import { URL_API } from "../services/Services";
import AppContext from "../context/AppContext";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import { useParams } from 'react-router-dom'

function EditProject() {
const {state, dispatch} = useContext (AppContext);
const { projects } = state;
const { error, success} = projects;
const id = useParams();

const initialState = {
    code: "",
    projectDescription: "",
    sprintDuration: "",
    startDate: "",
    endDate: "",
    numberOfPlannedSprints: "",
    status: "",
};

const [project, setProject] = useState(initialState);

function clickSubmit() {
    // console.log(initialState);
    // console.log(profile);

    const URL = `${URL_API}/projects/${(id.projectCode)}`;

    const request = {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(project),
    };
    // console.log("1 beforeCreate1");
    editProject(URL, request, dispatch);
  }

  //if  um estado else outro estado
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
            id="project-description"
            label="Project Description"
            onChange={(e) =>
              setProject({ ...project, projectDescription: e.target.value })
            }
          
          />
          <TextField
            id="sprint-duration"
            label="Sprint Duration"
            onChange={(e) =>
              setProject({ ...project, sprintDuration: e.target.value })
            }
          />
          <TextField
            id="number-of-planned-sprints"
            label="Number of Planned Sprints"
            onChange={(e) =>
              setProject({ ...project, numberOfPlannedSprints: e.target.value })
            }
          />
          <TextField
            id="status"
            label="Status"
            onChange={(e) =>
              setProject({ ...project, status: e.target.value })
            }
          />
          
  
          <input
            id="start-date"
            label="Start Date"
            type="date"
            onChange={(e) =>
              setProject({ ...project, startDate: e.target.value })
            }
          />
  
          <input
            id="end-date"
            label="End Date"
            type="date"
            onChange={(e) =>
              setProject({ ...project, endDate: e.target.value })
            }
          />
          <p></p>
  
          <Button onClick={() => clickSubmit()}> Submit</Button>
  
          <p></p>
        </Box>
      );
    }

}

export default EditProject;