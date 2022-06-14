import React from 'react';
import { Link } from "react-router-dom";


function Administrator()  {
    return (
      <div>
        <h1>Administrator</h1>
        <ul>
        <li>
          <Link to="/users/updateprofile">Update User Profile</Link>
        </li>
      </ul>
      </div>
      )
  
}
export default Administrator;