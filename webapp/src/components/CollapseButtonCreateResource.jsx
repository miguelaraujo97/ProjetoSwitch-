import React from 'react';
import { UncontrolledCollapse, CardBody, Card } from 'reactstrap';
import '../assets/css/Projects.css'
import Button from "@mui/material/Button";
import { Box } from "@mui/material";
import ResourceForm from './ResourceForm';



export default function CollapseButtonCreateResource(props) {
    const data = props.data;
    const actualProjectCode = props.projectCode;
    const dispatch = props.dispatch;


    return (
        <div>

            <Box>
                <Button id="toggler"
                    color="primary"
                    variant="outlined"
                    size="sm"
                    className='mb-2'
                >
                    Create new Resource
                </Button>

                <UncontrolledCollapse toggler="#toggler" >
                    <Card>
                        <CardBody>
                            <ResourceForm purpose="Create resource"
                                data={data}
                                projectCode={actualProjectCode}
                                dispatch={dispatch} />
                        </CardBody>
                    </Card>
                </UncontrolledCollapse>

            </Box>
        </div>
    );
}




