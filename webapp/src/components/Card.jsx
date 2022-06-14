import React from "react";

import { Card, CardTitle, CardText, Button } from 'reactstrap';

import { useNavigate } from 'react-router-dom';

function CardComponent(props) {

    const navigate = useNavigate();
    const navigateToUrl = (e) => {

        navigate(props.link, {replace: true});
    }

    return (
        <Card body>
            <CardTitle tag="h5">
                {props.title}
            </CardTitle>
            <CardText>
                {props.message}
            </CardText>
            <Button onClick={navigateToUrl}>
                {props.messageLink}
            </Button>
        </Card>
    );
}

export default CardComponent;