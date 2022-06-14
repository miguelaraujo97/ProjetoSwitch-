import React from "react";

import TitleHeader from "../components/HeaderPageWithButton";

export default function Empty() {

    return (

            <>
                <TitleHeader
                title="404 - page not found"
                buttonName="Back to home page"
                route="/"
            />
                
            </>

    );
}
