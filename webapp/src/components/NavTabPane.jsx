import React from 'react';
import { TabContent, TabPane, Nav, NavItem, NavLink, Row, Col } from 'reactstrap';
//import { FormControl, TextField, FormHelperText } from "@mui/material";
import classnames from 'classnames';
import ProductBacklogTable from './ProductBacklogTable';
import ResourcesTable from './ResourcesTable';
import CreateUserStories from '../pages/CreateUserStories';
import CollapseButtonCreateResource from './CollapseButtonCreateResource';

import SprintTable from './SprintTable';
import CreateSprint from '../pages/CreateSprint';
import ResourcePage from '../pages/ResourcePage';

/// OPTIONS NAV TABS
/// https://reactstrap.github.io/?path=/docs/components-nav--navs


export default function NavTabPane(props) {

    const [activeTab, setActiveTab] = React.useState("1");


    return (
        <>
            <Nav tabs justified>

                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === "1" })}
                        onClick={() => setActiveTab("1")}
                    >
                        Product Backlog
                    </NavLink>
                </NavItem>

                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === "2" })}
                        onClick={() => setActiveTab("2")}
                    >
                        Resources
                    </NavLink>
                </NavItem>

                <NavItem>
                    <NavLink
                        className={classnames({ active: activeTab === "3" })}
                        onClick={() => setActiveTab("3")}
                    >
                        Sprints
                    </NavLink>
                </NavItem>

            </Nav>


            <TabContent
                activeTab={activeTab}
                style={{
                    paddingTop: "30px",
                    minHeight: "600px"
                }}
            >

                <TabPane tabId="1">
                    <Row>
                        <Col sm="12">
                            <h5>Create a new user story</h5>
                            <CreateUserStories />
                            <hr />
                            <h2>User Stories</h2>
                            <ProductBacklogTable project={props.projectCode} />
                        </Col>
                    </Row>
                </TabPane>

                <TabPane tabId="2">
                    <Row>
                        <Col sm="12">
                            <ResourcePage/>

                            {/* <CollapseButtonCreateResource />
                            <hr />
                            <h2>Resources</h2>
                            <ResourcesTable /> */}
                        </Col>
                    </Row>
                </TabPane>

                <TabPane tabId="3">
                    <Row>
                        <Col sm="12">

                            <CreateSprint />
                            <hr />
                            <h2>Sprints</h2>
                            <SprintTable project={props.projectCode} />
                        </Col>
                    </Row>
                </TabPane>

            </TabContent>
        </>
    );
}
