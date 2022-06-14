import React from "react";

import { Row, Col } from 'reactstrap';
import CardComponent from "../components/Card";
import HeaderPage from "./layout/HeaderHomePage";


function Home() {

    return (
        <div>
            <HeaderPage title="Welcome" message="Have a nice day!" />

            <section>
                <Row>
                    <Col sm="6">
                        <CardComponent
                            title="Projects"
                            message="Manage projects and teams"
                            link="/projects"
                            messageLink="Open projects"
                        />
                    </Col>
                    <Col sm="6">
                        <CardComponent
                            title="Create New Project"
                            message="Create a new project"
                            link="/projects/create"
                            messageLink="New project"
                        />
                    </Col>
                </Row>
            </section>
        </div>
    );
}

export default Home;