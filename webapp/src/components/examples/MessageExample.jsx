import React from "react";

///// THIS IS AN EXAMPLE
function switchMessage(props) {
    return <h1>Welcome, {props.name} - {props.edition}</h1>;
}

export default switchMessage;

/// HOW TO USE
/// <Welcome name="SWitCH" edition="2021/2022" />

