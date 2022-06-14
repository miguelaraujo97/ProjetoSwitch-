import React, {  useContext } from 'react';
import AppContext from '../context/AppContext';
import {URL_API} from '../services/Services'


function GetProjectByCode() {

  const { state, dispatch} = useContext(AppContext);
  const {get} = state;
  // const {data, error}=profileUpdate;

  const initialState={
    userID : '',
    profileID : ''};


  return (
    <div>
      <h1>Get Project By Code</h1>
      <p>theses lines should be removed</p>
      <hr></hr>
      <GetProjectByCode dispatch={dispatch} 
      // getProjectByCode={getProjectByCode} 
      initialState = {initialState}
      url={URL_API}/>
    </div>
  );
}

export default GetProjectByCode;