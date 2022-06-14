import React, { useState } from 'react'
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import InputDate from "../components/form/InputDate";
/// TIME
import moment from "moment";

import { Row, Col } from 'reactstrap';


const CreateSprintForm = (props) => {
    const { dispatch } = props;

    const createStartDate = moment().format("YYYY-MM-DD");

    const [formInfo, setFormInfo] = useState({ plannedStartDate: createStartDate });

    const [formState, setFormState] = useState(true);

    /* const handleChange = (event) => {
        const { name, value } = event.target
        const newFormInfo = { ...formInfo, [name]: value }
        setFormInfo(newFormInfo);
    } */



    const submitForm = () => {
        //console.log(formInfo);
        //setFormState(false);
        dispatch(formInfo);
    }

    //const { description } = formInfo;

    const formSprint =
        <>
            <Row>
                <Col sm="8">
                    <InputDate id="planned-start-date"
                        name="plannedStartDate"
                        label="Planned Start Date"
                        defaultValue=""
                        value={formInfo.plannedStartDate ? formInfo.plannedStartDate : ""}
                        //minDate={createMinDate}
                        //maxDate=""
                        placeholder=""
                        helpMessage=""
                        onChange={(val) =>
                            setFormInfo({ ...formInfo, plannedStartDate: (val?.isValid() === true) ? val.format('YYYY-MM-DD') : null })
                        }
                    />
                </Col>
                <Col sm="4">
                    <Button fullWidth
                        variant="outlined"
                        color="success"
                        style={{ height: "40px" }}
                        onClick={submitForm}
                        >Create Sprint</Button>

                </Col>
            </Row>
        </>
        ;



    return (
        <>

            {formState ? formSprint : ""}
            
        </>
    );

}
export default CreateSprintForm;