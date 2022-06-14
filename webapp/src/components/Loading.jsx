import React from "react";

import Loading_v1 from "../assets/images/loading_v1.gif";

function Loading() {

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

export default Loading;