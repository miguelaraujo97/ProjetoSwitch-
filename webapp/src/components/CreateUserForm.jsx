import React, { useState } from "react";
import { Row, Col } from 'reactstrap';
import InputText from "../components/form/InputText";

import { Button } from "@mui/material";
import { Form } from 'reactstrap';
import { Box } from "@mui/material";
import { BiEraser } from "react-icons/bi";



export default function CreateUserForm(props) {
  const { dispatch } = props;

  const initialState = {
    userName: "",
    email: "",
    function: "",
    password: "",
    photo: ""
  };

  const [formInfo, setFormInfo] = useState(initialState);

  const handleChange = (event) => {
    const { name, value } = event.target
    const newFormInfo = { ...formInfo, [name]: value }
    console.log(event.target.value)
    setFormInfo(newFormInfo);
    console.log(formInfo.userName);
  }

  const submitForm = () => {
    dispatch(formInfo)
  }


  return (
    <Box /* onSubmit={submitForm} */>
      <Row>
        <Col sm="6">
          <InputText
            type="text"
            id="user-name"
            label="User name"
            value={formInfo.userName}
            onChange={(e) => setFormInfo({ ...formInfo, userName: e.target.value })}
          />
        </Col>
        <Col sm="6">
          <InputText
            type="text"
            id="user-email"
            label="Email"
            name="email"
            value={formInfo.email}
            onChange={(e) => setFormInfo({ ...formInfo, email: e.target.value })}
          />
        </Col>

        <Col sm="6">
          <InputText
            type="text"
            id="user-function"
            label="Function"
            name="function"
            value={formInfo.function}
            onChange={(e) => setFormInfo({ ...formInfo, function: e.target.value })}
          />
        </Col>

        <Col sm="6">
          <InputText
            type="password"
            id="user-password"
            label="Password"
            name="password"
            value={formInfo.password}
            onChange={(e) => setFormInfo({ ...formInfo, password: e.target.value })}
          />
        </Col>

        <Col sm="6">
          <InputText
            type="text"
            id="user-phot"
            label="Photo"
            name="photo"
            placeholder="https://image.jpg"
            value={formInfo.photo}
            onChange={(e) => setFormInfo({ ...formInfo, photo: e.target.value })}
          />
        </Col>

      </Row>

      <Button
        variant="outlined"
        color="success"
        type="submit"
        onClick={submitForm}
      >Create User</Button>

    </Box>

  );

}