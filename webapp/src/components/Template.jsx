import React from "react";

//import { Input } from "reactstrap";
//import { InputLabel, Select, MenuItem } from "@mui/material";

//import { BiArrowBack } from "react-icons/bi";

import Loading_v1 from "../assets/images/loading_v1.gif";

function Template() {

    return (
        <div>
            
            <h1 style={{textAlign: "center"}}>
            <img src={Loading_v1} width="50px" alt="loading gif" />
                <br />
                Loading ...
            </h1>
        </div>
    );
}

export default Template;