import React from "react";

//import { BiArrowBack } from "react-icons/bi";

//import AppContext from '../../context/AppContext';

import '../../assets/css/Header.css';

function Header() {
    //const { headers } = useContext(AppContext);

    return (
        <header >
            {/* <h1 className="text--title">Project G4</h1> */}
            <h2 className="text--sub-title">Project Management System</h2>
            {/* <span id="accountUser" className="account--user-profile">{headers.profile}</span>
            <a href="#logout" className="account--logout"><BiArrowBack /> LOG OUT</a> */}

            {/* <hr /> */}
        </header>
    );
}

export default Header;