import React from "react";
import { Link } from "react-router-dom";
import ProjectsTable from "../components/ProjectsTable";
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';


function Projects() {
  return (
    <>
      <h1>PROJECTS</h1>

      <hr />

      <Stack spacing={2} direction="row">
        <Link to="/projects/create">
          <Button variant="contained" href="#contained-buttons">Create Project</Button>
        </Link>
        <Link to="/projects/typologies">
          <Button variant="contained" href="#contained-buttons">Create Typology</Button>
        </Link>
        <Link to="/projects/resources">
          <Button variant="contained" href="#contained-buttons">Create Resource </Button>
        </Link>
      </Stack>

      <ProjectsTable />
    </>
  );
}

export default Projects;
