import React from 'react';
import { UncontrolledCollapse, CardBody, Card } from 'reactstrap';
import '../assets/css/Projects.css'
import Button from "@mui/material/Button";
import { Box } from "@mui/material";
import ResourceForm from './ResourceForm';



export default function CollapseButtonEditResource(props) {
    const actualProjectCode = props.projectCode;
    const dispatch = props.dispatch;
    const { resourceToEdit } = props;


    return (
        <div>

            <Box>
                <Button id="toggler"
                    color="primary"
                    variant="outlined"
                    size="sm"
                    className='mb-2'
                >
                    Edit Resource
                </Button>

                <UncontrolledCollapse toggler="#toggler" defaultOpen={true} >
                    <Card>
                        <CardBody>
                            <ResourceForm purpose="Edit resource"
                                data={resourceToEdit}
                                projectCode={actualProjectCode}
                                dispatch={dispatch} />
                        </CardBody>
                    </Card>
                </UncontrolledCollapse>

            </Box>
        </div>
    );
}




