import React, { useState } from "react";
import HeaderPage from "../components/HeaderPage";

import Button from "@mui/material/Button";

import InputSelect from "../components/form/InputSelect";
import InputText from "../components/form/InputText";
import InputDate from "../components/form/InputDate";

import { Row, Col } from 'reactstrap';

import { Form } from 'reactstrap';

import { URL_API } from "../services/Service";

import { createResources } from "../context/actions/CreateResourceActions";
import { BiEraser } from "react-icons/bi";




export default function ResourceForm(props) {
    const purpose = props.purpose;  //create or edit
    // const clickSubmit = props.submit;
    const data = props.data; //resource Data or empty states
    const actualProjectCode = props.projectCode;
    const dispatch = props.dispatch;



    const [resource, setResource] = useState(data);

    const resetResource = () => setResource("")

    const roleValues = [
        { value: "ProductOwner", label: "Product Owner" },
        { value: "ProjectManager", label: "Project Manager" },
        { value: "ScrumMaster", label: "Scrum Master" },
        { value: "Developer", label: "Developer" }
    ];

    const clickSubmit = async (e) => {
        e.preventDefault();
        let fetchMethod = ""
        if (purpose === "Create resource") {
            fetchMethod = "POST";
        } else {
            if (purpose === "Edit resource") {
                fetchMethod = "PATCH";
            }
        }
        if (validateForm(e)) {
            const URL = URL_API + '/projects/' + actualProjectCode + '/resources';
            const request = {
                method: fetchMethod,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(resource)
            };

            createResources(URL, request, dispatch);
        }
    };

    function validateForm(e) {

        return true;
    }



    return (
        <>

            <HeaderPage title={purpose} message="" />
            <Form onSubmit={clickSubmit}>
                <Row>

                    <Col sm="12">
                        <InputText
                            type="text"
                            id="email"
                            label="Email"
                            value={resource.email}
                            placeholder="email@amail.com"
                            onChange={(e) =>
                                setResource({ ...resource, email: e.target.value })
                            }
                        />
                    </Col>

                    <Col sm="6">
                        <InputDate
                            id="start-date"
                            label="Start Date"
                            defaultValue={resource.startDate}
                            //minDate={createStartDate}
                            value={resource.startDate}
                            maxDate=""
                            placeholder=""
                            helpMessage=""
                            onChange={(val) =>
                                setResource({ ...resource, startDate: (val?.isValid() === true) ? val.format('YYYY-MM-DD') : null })
                            }
                        />
                    </Col>
                    <Col sm="6">
                        <InputDate
                            id="end-date"
                            label="End Date"
                            defaultValue={resource.endDate}
                            //minDate={createStartDate}
                            value={resource.endDate}
                            maxDate=""
                            placeholder=""
                            helpMessage=""
                            onChange={(val) =>
                                setResource({ ...resource, endDate: (val?.isValid() === true) ? val.format('YYYY-MM-DD') : null })
                            }
                        />
                    </Col>

                    <Col sm="4">
                        <InputText
                            type="number"
                            id="percentageAllocation"
                            label="Percentage of Allocation"
                            value={resource.percentageAllocation}
                            min="0"
                            max="100"
                            placeholder=""
                            helpMessage=""
                            onChange={(e) =>
                                setResource({ ...resource, percentageAllocation: e.target.value })
                            }
                        />
                    </Col>
                    <Col sm="4">
                        <InputText
                            type="number"
                            id="cost-per-hour"
                            label="Cost per hour"
                            min="0"
                            max="1000"
                            value={resource.costPerHour}
                            placeholder=""
                            helpMessage=""
                            onChange={(e) =>
                                setResource({ ...resource, costPerHour: e.target.value })
                            }
                        />
                    </Col>
                    <Col sm="4">
                        <InputSelect
                            id="role"
                            label="Role"
                            disabled="false"
                            selectValue={resource.role}
                            defaultValue={resource.role}
                            data={roleValues}
                            onChange={(e) =>
                                setResource({ ...resource, role: e.target.value })
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
                    onClick={resetResource}

                ><BiEraser /><br /></Button>


                <Button
                    variant="outlined"
                    color="success"
                    style={{ float: "right", marginTop: "5px" }}
                    type="submit"
                >{purpose}</Button>
            </Form >
        </>

    );
}