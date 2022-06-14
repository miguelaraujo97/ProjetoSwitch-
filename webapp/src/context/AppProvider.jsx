import React, { useReducer } from 'react';
import PropTypes from "prop-types";
import { Provider } from './AppContext';
import reducer from './AppReducer';

const loginUser = {
    id: "1",
    name: "Bill Gates",
    username: "billgates",
    email: "billgates@isep.ipp.pt",
    /// admin | director | user | dev
    profile: "dev",
    /// pm | sm | po | team | dev
    role: "dev"
};


const initialState = {

    ///////// PROJECT
    projects: {
        loading: true,
        error: null,
        data: [],
    },
    
    createProjectData: {
        loading: true,
        error: null,
        success: null,
        data: [],
    },

    editProject: {
        loading: true,
        error: null,
        data: [],
    },

    projectByCode: {
        loading: true,
        error: null,
        data: [],
    },

    getProjectByCode: {
        loading: true,
        error: null,
        data: [],
    },

    ///////// RESOURCES

    resources: {
        loading: true,
        error: null,
        data: [],
    },

    createResource: {
        loading: false,
        error: null,
        success: null,
        data: "",
    },

    ///////// USERS
    
    users: {
        loading: false,
        error: null,
        data: [],
    },

    user: {
        loading: false,
        error: null,
        data: ""
    },

    ///////// PROFILES
    
    profiles: {
        loading: false,
        error: null,
        success: null,
        data: "",
    },

    profileUpdate: {
        loading: true,
        error: null,
        data: "",
    },

    ///////// TYPOLOGIES

    typologies: {
        loading: false,
        error: null,
        success: null,
        data: []
    },

    ///////// PRODUCT BACKLOG

    productBacklog: {
        loading: true,
        error: null,
        data: []
    },
    //////// PRODUCT BACKLOG
    userStories: {
        loading: true,
        error: null,
        data: []
    },
    getUserStoryById: {
        loading: true,
        error: null,
        data: [],
    },

    ///////// SPRINTS

    sprints: {
        loading: true,
        error: null,
        success:null,
        data: []
    },

    sprint: {
        loading: true,
        error: null,
        success:null,
        data: ""
    }

};



const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            loginUser,
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