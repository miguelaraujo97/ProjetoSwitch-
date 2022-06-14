import React, {  useContext } from 'react';
import UpdateUserProfile from '../components/UpdateUserProfile';
import AppContext from '../context/AppContext';
import {URL_API} from '../services/Services'


function UpdateProfile() {

  const { state, dispatch} = useContext(AppContext);
  const {profileUpdate} = state;
  // const {data, error}=profileUpdate;

  const initialState={
    userID : '',
    profileID : ''};


  return (
    <div>
      <h1>Update users profiles</h1>
      <p>theses lines should be removed</p>
      <hr></hr>
      <UpdateUserProfile dispatch={dispatch} 
      profileUpdate={profileUpdate} 
      initialState = {initialState}
      url={URL_API}/>
    </div>
  );
}

export default UpdateProfile;
