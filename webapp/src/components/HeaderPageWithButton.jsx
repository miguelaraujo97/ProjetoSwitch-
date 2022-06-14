import React from "react";

import { Row, Col } from 'reactstrap';
import Button from "@mui/material/Button";

//import { BiArrowBack } from "react-icons/bi";
import { useNavigate } from 'react-router-dom';

function TitleHeader(props) {

    const navigate = useNavigate();
    const gotToLink = (e) => {
        navigate(props.route, { replace: true });
    }

    return (
        <Row>
            <Col sm="8">
                <h1>{props.title}</h1>
            </Col>
            <Col sm="4" >
                <Button
                    variant="outlined"
                    color={props.color}
                    style={{ float: "right", marginTop: "5px" }}
                    onClick={gotToLink}
                >{props.buttonName}</Button>
            </Col>
            <hr />
        </Row>
    );
}

export default TitleHeader;