import React, { useContext, useState } from "react";
import { createProject } from "../context/Actions";
import { URL_API } from "../services/Services";
import AppContext from "../context/AppContext";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";

function CreateProject() {
  const { state, dispatch } = useContext(AppContext);
  const { projects } = state;
  const { error, success } = projects;

  const initialState = {
    code: "",
    name: "",
    customer: "",
    projectDescription: "",
    budget: "",
    description: "",
    sprintDuration: "",
    numberOfPlannedSprints: "",
    status: "",
    startDate: "",
    endDate: "",
    businessSector: "",
  };
  const [project, setProject] = useState(initialState);

  function clickSubmit() {
    // console.log(initialState);
    // console.log(profile);

    const URL = `${URL_API}/projects`;

    const request = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(project),
    };
    // console.log("1 beforeCreate1");
    createProject(URL, request, dispatch);
  }

  //if  um estadpo eslse outro estado
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
          id="project-code"
          label="Code"
          onChange={(e) =>
            setProject({ ...project, code: e.target.value })
          }
        />

        <TextField
          id="project-name"
          label="Name"
          onChange={(e) => setProject({ ...project, name: e.target.value })}
        />

        

        <TextField
          id="customer"
          label="Customer"
          onChange={(e) =>
            setProject({ ...project, customer: e.target.value })
          }
        />

        <TextField
          id="project-description"
          label="Project Description"
          onChange={(e) =>
            setProject({ ...project, projectDescription: e.target.value })
          }
        />
        <TextField
          id="budget"
          label="Budget"
          onChange={(e) =>
            setProject({ ...project, budget: e.target.value })
          }
        />
        <TextField
          id="description"
          label="Typology Description"
          onChange={(e) =>
            setProject({ ...project, description: e.target.value })
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

        <TextField
          id="business-sector"
          label="Business Sector"
          onChange={(e) => setProject({ ...project, businessSector: e.target.value })}/>

        

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

export default CreateProject;
