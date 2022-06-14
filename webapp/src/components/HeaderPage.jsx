import React from "react";

import '../assets/css/Header.css';

function HeaderPage(props) {

    return (
        <header>
            <h2>{props.title}</h2>
            {props.message}
            <hr />
        </header>
    );
}

export default HeaderPage;