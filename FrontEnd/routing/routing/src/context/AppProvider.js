import React, { useReducer } from 'react';
import PropTypes from "prop-types";
import { Provider } from './AppContext';
import reducer from './Reducer';

const initialState = {
    profiles: {
        loading: false,
        error: null,
        success: null,
        data: "",
    },

    typologies: {
        loading: false,
        error: null,
        success: null,
        data: []
    },

    resources: {
        loading: false,
        error: null,
        success: null,
        data: ""
    },

    projects: {
        loading: true,
        error: null,
        data: []
    },

    userStories: {
        loading: false,
        error: null,
        // success: null,
        data: []
    },
    users: {
      loading: null,
      error: null,
      success: null,
      data: []
    },
    profileUpdate:{
      loading: true,
      error: null,
      data: "",
    },

    editProject:{
      loading:true,
      error: null,
      data: [],
    },

    getProjectByCode:{
      loading:true,
      error: null,
      data: [],
    }



  };

  const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
      <Provider value={{
        state,
    
        dispatch
      }}>
        {props.children}
      </Provider>
    );
  };
  AppProvider.propTypes = {
    children: PropTypes.node,
  };

  export default AppProvider;