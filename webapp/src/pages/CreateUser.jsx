import React, { useContext } from "react";
import { createUser } from "../context/actions/UserActions";
import { URL_API } from "../services/Service";
import AppContext from "../context/AppContext";
import Alert from '@mui/material/Alert';
import CreateUserForm from "../components/CreateUserForm";
import TitleHeader from "../components/HeaderPageWithButton";

function CreateUser() {
  const { state, dispatch } = useContext(AppContext);
  const { user } = state;
  const { error, data } = user;

  const submitForm = (formInfo) => {
    const URL = `${URL_API}/users`;
    console.log(URL);
    const request = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formInfo),
    };
    createUser(URL, request, dispatch);
  }


  if (data !== "") {

    return (
      <div>
        <TitleHeader
          title="Users"
          buttonName="Back to users"
          route="/users/"
        />
        <CreateUserForm dispatch={submitForm} />

        <Alert severity="success">User successfully created</Alert>
      </div>
    )
  } else {
    if (error !== null) {
      return (
        <div>
          <TitleHeader
            title="Users"
            buttonName="Back to users"
            route="/users/"
          />
          <CreateUserForm dispatch={submitForm} />
          <Alert severity="error">Fail to Create</Alert>
        </div>
      );
    } else {
      return (
        <div>
          <TitleHeader
            title="Users"
            buttonName="Back to users"
            route="/users/"
          />
          <CreateUserForm dispatch={submitForm} />
        </div>
      );
    }
  }
}

export default CreateUser;
