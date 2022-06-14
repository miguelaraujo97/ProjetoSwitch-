import React, { useContext, useEffect, useState } from 'react';
import AppContext from '../context/AppContext';
import { Outlet } from "react-router-dom";
import UsersTable from '../components/UsersTable';
import { URL_API } from '../services/Service'
import { searchUserProfile } from '../context/actions/UserActions';
import TitleHeader from '../components/HeaderPageWithButton';
import InputSelect from '../components/form/InputSelect';



function Users() {
  const { state, dispatch } = useContext(AppContext);
  const { users } = state;

  const [profile, setProfile] = useState("Visitor");
  const profiles = [
    { value: "Visitor", label: "Visitor" },
    { value: "Administrator", label: "Administrator" }
  ];

  const handleChange = (event) => {
    setProfile(event.target.value);
  };


  useEffect(() => {
    if (profile) {
      let URL = URL_API + `/users/by-profile/` + profile;
      console.log(URL)

      const request = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      };
      searchUserProfile(URL, request, dispatch);
    }
  }
    , [profile, dispatch]);

  return (
    <div>


      <TitleHeader
        title="Users"
        buttonName="Create"
        color="success"
        route="/users/create"
      />

     
        <InputSelect
          id="profile"
          label="Profile name"
          disabled="false"
          selectValue="Visitor"
          data={profiles}
          onChange={handleChange}
           />
           <hr />

      <UsersTable users={users} />
      <Outlet />
    </div>
  );
}

export default Users;
