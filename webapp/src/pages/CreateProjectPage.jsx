/// CONTEXT
import React, { useContext, useState, useEffect } from 'react';
import AppContext from "../context/AppContext";
//import { useNavigate } from 'react-router-dom';
/// SERVICES
import { URL_API } from "../services/Service";
/// ACTIONS
import { createProjectPOST } from "../context/actions/CreateProjectActions";
import { fetchTypologyActions } from "../context/actions/FetchTypologyActions";

/// COMPONENTS
//import HeaderPage from "../components/HeaderPage";
import TitleHeader from "../components/HeaderPageWithButton";


import { Button, Alert } from "@mui/material";
import { Form } from 'reactstrap';

import InputSelect from "../components/form/InputSelect";
import InputText from "../components/form/InputText";
import InputDate from "../components/form/InputDate";

/// TIME
import moment from "moment";

import '../assets/css/Projects.css'
import { Row, Col } from 'reactstrap';

import { BiEraser } from "react-icons/bi";



export default function CreateProjectPage() {
    const { state, dispatch } = useContext(AppContext);
    const { createProjectData, typologies } = state;
    const { data } = createProjectData;

    const [project, setProject] = useState(createProjectData);

    const createStartDate = moment().format("YYYY-MM-DD");
    //const createMinDate = moment().subtract(1, 'day').format("YYYY-MM-DD");
    const createEndDate = moment(createStartDate).add(2, 'weeks').format("YYYY-MM-DD");

    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [formState, setFormState] = useState(true);


    /// initial values
    const initialState = {
        code: "",
        name: "",
        customer: "",
        projectDescription: "",
        budget: "",
        description: "",
        sprintDuration: "1",
        numberOfPlannedSprints: "1",
        status: "inception",
        startDate: createStartDate,
        endDate: createEndDate,
        businessSector: "",
    };

    const demoValues = {
        code: "A0001" + moment(),
        name: "Dummy 1",
        customer: "Steve Jobs",
        projectDescription: "Project small description",
        budget: 10000.0,
        description: "TYP-812c1386-42d3-422b-bb6b-5368082c2d69",
        sprintDuration: 2,
        numberOfPlannedSprints: 6,
        status: "planned",
        startDate: createStartDate,
        endDate: createEndDate,
        businessSector: "IT services",
    };


    const statusValues = [
        { value: "planned", label: "Planned" },
        { value: "inception", label: "Inception" },
        { value: "elaboration", label: "Elaboration" },
        { value: "construction", label: "Construction" },
        { value: "transition", label: "Transition" },
        { value: "warranty", label: "Warranty" },
        { value: "closed", label: "Closed" }
    ];

    /// end initial values

    useEffect(() => {

        const URL_TYPOLOGIES = URL_API + "/typologies";
        const request = {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        };
        fetchTypologyActions(URL_TYPOLOGIES, request, dispatch);


    }, [dispatch]);

    const submitHandler = (e) => {
        e.preventDefault();
        setErrorMessage('');
        setSuccessMessage('');
        
        if (validateForm(e) === true) {

            const URL = URL_API + "/projects";
            const request = {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(project)
            };

            createProjectPOST(URL, request, responsessss);

            function responsessss(res) {
                //console.log("createProjectPOST",res);
                if (res.type === "CREATE_PROJECT_SUCCESS") {
                    setFormState(false);
                    setSuccessMessage('Project created with success');
                }

                if (res.type === "CREATE_PROJECT_FAILURE") {
                    setErrorMessage('ERROR!');
                }

            }

            //console.log(state);
        } else {
            setErrorMessage('Please fill mandatory fields!');
        }

    };

    const resetForm = () => {
        //createProjectDefaultValues(initialState);
        //setProject(initialState);
        setProject(initialState);

    }

    const setDummyData = () => {
        setProject(demoValues);
    }


    function validateForm(e) {
        // To validate one by one
        console.log(project);
        if (project.data === null && project.code === "") {
            return false;
        }
        return true;
    }

    const formProjects = 
            
        <Form id="createProjectForm" onSubmit={submitHandler}>
            <Row>
                <Col sm="6">
                    <InputText id="project-code"
                        name="code"
                        type="text"
                        label="Code"
                        placeholder="A0001"
                        value={project.code}
                        onChange={(e) =>
                            setProject({ ...project, code: e.target.value })
                        }
                    />
                </Col>
                <Col sm="12">
                    <InputText id="project-name"
                        //name="name"
                        type="text"
                        label="Name"
                        placeholder="Dummy 100"
                        value={project.name}
                        onChange={(e) =>
                            setProject({ ...project, name: e.target.value })
                        }
                    />
                </Col>
                <Col sm="6">
                    <InputText id="project-customer"
                        type="text"
                        label="Customer"
                        value={project.customer}
                        placeholder="Name of the customer"
                        //helpMessage="help message"
                        onChange={(e) =>
                            setProject({ ...project, customer: e.target.value })
                        }
                    />
                </Col>
                <Col sm="6">
                    <InputText id="business-sector"
                        type="text"
                        label="Business Sector"
                        value={project.businessSector}
                        placeholder=""
                        helpMessage=""
                        onChange={(e) =>
                            setProject({ ...project, businessSector: e.target.value })
                        }
                    />
                </Col>
                <Col sm="12">
                    <InputText id="project-description"
                        type="text"
                        label="Project Description"
                        value={project.projectDescription}
                        placeholder=""
                        helpMessage=""
                        onChange={(e) =>
                            setProject({ ...project, projectDescription: e.target.value })
                        }
                    />
                </Col>

                <Col sm="6">
                    <InputText id="budget"
                        type="number"
                        label="Budget"
                        value={project.budget}
                        min="0"
                        placeholder=""
                        helpMessage=""
                        onChange={(e) =>
                            setProject({ ...project, budget: e.target.value })
                        }
                    />
                </Col>
                <Col sm="6">
                    <InputSelect id="typology"
                        name="typology"
                        label="Typology"
                        data_value="typologyId"
                        data_label="typologyDescription"
                        selectValue=""
                        data={typologies.data != null ? typologies.data[0] : []}
                        onChange={(e) =>
                            setProject({ ...project.data, description: e.target.value })
                        }
                    />

                </Col>
                <Col sm="4">
                    <InputText id="sprint-duration"
                        type="number"
                        label="Sprint Duration (Weeks)"
                        min="1"
                        value={project.sprintDuration}
                        placeholder=""
                        helpMessage=""
                        onChange={(e) =>
                            setProject({ ...project, sprintDuration: e.target.value })
                        }
                    />
                </Col>
                <Col sm="4">
                    <InputText id="number-of-planned-sprints"
                        type="number"
                        label="Number of Planned Sprints"
                        min="1"
                        max="1000"
                        value={project.numberOfPlannedSprints}
                        placeholder=""
                        helpMessage=""
                        onChange={(e) =>
                            setProject({ ...project, numberOfPlannedSprints: e.target.value })
                        }
                    />
                </Col>
                <Col sm="4">
                    <InputSelect id="status"
                        name="status"
                        label="Status"
                        //data_value="value"
                        //data_label="label"
                        disabled="false"
                        selectValue="planned"
                        data={statusValues}
                        onChange={(e) =>
                            setProject({ ...project, status: e.target.value })
                        }
                    />
                </Col>
                <Col sm="6">
                    <InputDate id="start-date"
                        name="startDate"
                        label="Start Date"
                        defaultValue=""
                        value={project.startDate ? project.startDate : ""}
                        //minDate={createMinDate}
                        //maxDate=""
                        placeholder=""
                        helpMessage=""
                        onChange={(val) =>
                            setProject({ ...project, startDate: (val?.isValid() === true) ? val.format('YYYY-MM-DD') : null })
                        }
                    />
                </Col>
                <Col sm="6">
                    <InputDate id="end-date"
                        name="endDate"
                        label="End Date"
                        value={project.endDate ? project.endDate : ""}
                        //minDate={createMinDate}
                        //maxDate=""
                        placeholder=""
                        helpMessage=""
                        onChange={(val) =>
                            setProject({ ...project, endDate: (val?.isValid() === true) ? val.format('YYYY-MM-DD') : null })
                        }
                    />
                </Col>
            </Row>

            <Button
                variant="outlined"
                color="error"
                type="reset"
                style={{ marginRight: "10px" }}
                title="Reset form"
                onClick={resetForm}
            ><BiEraser /><br /></Button>

            <Button
                variant="outlined"
                color="secondary"
                type="button"
                onClick={setDummyData}
            >Set With Dummy data</Button>

            <Button
                variant="outlined"
                color="success"
                type="submit"
                style={{ float: "right" }}
            >Create Project</Button>

        </Form >
;


if (data) {
    return (
        <>
            <TitleHeader
                title="Create Project"
                buttonName="Back to Projects"
                route="/projects"
            />

            {formState ? formProjects : ""}

            <br />

            {errorMessage && (
                <Alert severity="error">{errorMessage}</Alert>
            )}

            {successMessage && (
                <Alert severity="success">{successMessage}</Alert>
            )}
        </>
    );
}else{
    return "No Data ..."
}

}
