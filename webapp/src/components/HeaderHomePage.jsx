import React, { useContext } from "react";

//import { BiArrowBack } from "react-icons/bi";

import AppContext from '../context/AppContext';

import '../assets/css/Header.css';

export default function HeaderHomePage(props) {
    const { loginUser } = useContext(AppContext);

    return (
        <header>
            <h2>{props.title} {loginUser.name}</h2>
            {props.message}
            <hr />
        </header>
    );
}
