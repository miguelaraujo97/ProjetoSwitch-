import React, { useContext} from "react";
import AppContext from '../context/AppContext';
import { Row, Col, Progress } from "reactstrap";
//import { Box } from "@mui/material";
import ProjectStatus from "./ProjectStatusComponent";

/// TIME
import moment from "moment";

import "../assets/css/Projects.css";

export default function PorjectInfo() {

    const { state } = useContext(AppContext);
    const { projectByCode } = state;
    const { loading, error, data } = projectByCode;

    
    //// GET VALUES FROM STORE
    const projectName = data[0] != null ? data[0].name : "";
    const projectCode = data[0] != null ? data[0].code : "";
    const projectCustomer = data[0] != null ? data[0].customer : "";
    const projectBusinessSector = data[0] != null ? data[0].businessSector : "";
    const projectDescription = data[0] != null ? data[0].projectDescription : "";
    
    const projectBudget = data[0] != null ? data[0].budget.toFixed(2).replace(".",",") : "";
    const projectTypologyDescription = data[0] != null ? data[0].typologyDescription : "";

    const projectStatus = data[0] != null ? data[0].status : "";
    const projectNumberOfPlannedSprints = data[0] != null ? data[0].numberOfPlannedSprints : "";
    const projectSprintDuration = data[0] != null ? data[0].sprintDuration : "";

    const projectStartDate = data[0] != null ? moment(data[0].startDate).format("DD/MM/YYYY") : "";
    const projectEndDate = data[0] != null ? moment(data[0].endDate).format("DD/MM/YYYY") : "";
    

    /// CREATE QR CODE AFTER PROJECT CREATION
    const qrCodeUrl = projectCode!="" ? "https://api.qrserver.com/v1/create-qr-code/?size=100x100&data=" + projectCode : "";
    

    return (
        <>


            <Row>
                <Col sm="2">
                    <img src={qrCodeUrl} alt="qrcode"></img>
                    <div className="info--label">Scan with your phone</div>

                </Col>
                <Col sm="7">

                    <div className="info--label">Code</div>
                    <div className="info--name">{projectCode}</div>
                    <hr />
                    <div className="info--label">Name</div>
                    <div className="info--name">{projectName}</div>
                    <hr />
                    <div className="info--label">Business Sector</div>
                    <div className="info--name">{projectBusinessSector}</div>
                    <hr />
                    <Row>
                        <Col sm="6">
                            <div className="info--label">Start date</div>
                            <div className="info--name">{projectStartDate}</div>
                            <hr />
                        </Col>
                        <Col sm="6">
                            <div className="info--label">End date</div>
                            <div className="info--name">{projectEndDate}</div>
                            <hr />
                        </Col>
                    </Row>
                </Col>

                <Col sm="3">
                    <div className="info--label">Customer</div>
                    <div className="info--name">{projectCustomer}</div>
                    <hr />
                    <div className="info--label">Bugdet</div>
                    <div className="info--name">{projectBudget} €</div>
                    <hr />
                    <div className="info--label">Default Sprint Duration (planned sprints)</div>
                    <div className="info--name">{projectSprintDuration} weeks ({projectNumberOfPlannedSprints} planned)</div>
                    <hr />
                    <div className="info--label">Status</div>
                    <div className="info--name">{projectStatus}</div>
                    <hr />
                </Col>
            </Row>

            <hr />
            <h2>Overview</h2>
            <br />  

            <ProjectStatus projectStatus={projectStatus}/>
            
            <br />
            Sprint calendar and other graphics
            <br />
            <hr />
            <h2>Description</h2>
            
            <div style={{ textAlign: "justify", textJustify: "inter-word" }}>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
            Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
            when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
            It has survived not only five centuries, but also the leap into electronic typesetting, 
            remaining essentially unchanged. It was popularised in the 1960s with the release of 
            Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing 
            software like Aldus PageMaker including versions of Lorem Ipsum.
            </div>

            <hr />
            
            <br />
        </>
    );
}
