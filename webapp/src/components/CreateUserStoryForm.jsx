import React, { useState } from 'react'
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { Row, Col, Form } from 'reactstrap';
import InputText from './form/InputText';


const CreateUserStoryForm = (props) => {
    const { dispatch } = props;
    const [formInfo, setFormInfo] = useState("");


    const handleChange = (event) => {
        const { name, value } = event.target
        const newFormInfo = { ...formInfo, [name]: value }
        setFormInfo(newFormInfo);
    }

    const submitForm = () => {
        dispatch(formInfo)
    }

    const { description } = formInfo;

    return (
        <>
            <Form id="createProjectForm">
                <Row>
                    <Col sm="8">

                        <TextField fullWidth
                            id="US description"
                            label="US description"
                            size="small"
                            name="description" value={description} onChange={handleChange}
                        />
                        {/* <InputText
                            type="text"
                            id="US description"
                            label="US description"
                            placeholder="Some description"
                            name="description"
                            value={description}
                            onChange={handleChange}
                        /> */}

                    </Col>
                    <Col sm="4">
                        <Button fullWidth
                            variant="outlined"
                            color="success"
                            style={{ height: "40px"}}
                            onClick={submitForm}>Create</Button>
                    </Col>
                </Row>
            </Form>
        </>
    );
}
export default CreateUserStoryForm;