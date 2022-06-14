import { makeHTTPRequest } from "../services/Services";

export const CREATE_PROFILE_STARTED = "CREATE_PROFILE_STARTED";
export const CREATE_PROFILE_SUCCESS = "CREATE_PROFILE_SUCCESS";
export const CREATE_PROFILE_FAILURE = "CREATE_PROFILE_FAILURE";

export const CREATE_TYPOLOGY_STARTED = "CREATE_TYPOLOGY_STARTED";
export const CREATE_TYPOLOGY_SUCCESS = "CREATE_TYPOLOGY_SUCCESS";
export const CREATE_TYPOLOGY_FAILURE = "CREATE_TYPOLOGY_FAILURE";

export const CREATE_RESOURCE_STARTED = "CREATE_RESOURCE_STARTED";
export const CREATE_RESOURCE_SUCCESS = "CREATE_RESOURCE_SUCCESS";
export const CREATE_RESOURCE_FAILURE = "CREATE_RESOURCE_FAILURE";

export const CREATE_PROJECT_STARTED = "CREATE_PROJECT_STARTED";
export const CREATE_PROJECT_SUCCESS = "CREATE_PROJECT_SUCCESS";
export const CREATE_PROJECT_FAILURE = "CREATE_PROJECT_FAILURE";

export const SUBMIT_USERSTORY_SUCCESS = 'SUBMIT_USERSTORY_SUCCESS';
export const SUBMIT_USERSTORY_FAIL = 'SUBMIT_USERSTORY_FAIL';
export const SUBMIT_USERSTORY_STARTED = 'SUBMIT_USERSTORY_STARTED';

export const CREATE_USER_STARTED = "CREATE_USER_STARTED";
export const CREATE_USER_SUCCESS = "CREATE_USER_SUCCESS";
export const CREATE_USER_FAILURE = "CREATE_USER_FAILURE";

export const UPDATE_PROFILE_STARTED = 'UPDATE_PROFILE_STARTED'
export const UPDATE_PROFILE_SUCCESS = 'UPDATE_PROFILE_SUCCESS'
export const UPDATE_PROFILE_FAILURE = 'UPDATE_PROFILE_FAILURE'

export const EDIT_PROJECT_STARTED = "EDIT_PROJECT_STARTED";
export const EDIT_PROJECT_SUCCESS = "EDIT_PROJECT_SUCCESS";
export const EDIT_PROJECT_FAILURE = "EDIT_PROJECT_FAILURE";

export const SEARCH_BY_PROFILE_SUCCESS = "SEARCH_BY_PROFILE_SUCCESS";
export const SEARCH_BY_PROFILE_FAILURE = "SEARCH_BY_PROFILE_FAILURE";




export function createProfile(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(createProfileSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(createProfileFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
  // console.log("3 success" success);
  // console.log("4 failure", failure);
}

export function createProfileFailure(message) {
  return {
    type: CREATE_PROFILE_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function createProfileSuccess(message) {
  return {
    type: CREATE_PROFILE_SUCCESS,
    payload: {
      data: message,
    },
  };
}

export function createProfileStarted() {
  return {
    type: CREATE_PROFILE_STARTED,
  };
}

export function createTypology(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(createTypologySuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(createTypologyFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
  // console.log("3 success" success);
  // console.log("4 failure", failure);
}

export function createTypologyFailure(message) {
  return {
    type: CREATE_TYPOLOGY_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function createTypologySuccess(message) {
  return {
    type: CREATE_TYPOLOGY_SUCCESS,
    payload: {
      success: message,
    },
  };
}

export function createTypologyStarted() {
  return {
    type: CREATE_TYPOLOGY_STARTED,
  };
}

export function createResource(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(createProfileSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(createProfileFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
  // console.log("3 success" success);
  // console.log("4 failure", failure);
}

export function createResourceFailure(message) {
  return {
    type: CREATE_RESOURCE_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function createResourceSuccess(message) {
  return {
    type: CREATE_RESOURCE_SUCCESS,
    payload: {
      data: message,
    },
  };
}

export function createProject(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(createProjectSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(createProjectFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
  // console.log("3 success" success);
  // console.log("4 failure", failure);
}

export function createProjectFailure(message) {
  return {
    type: CREATE_PROJECT_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function createProjectSuccess(message) {
  return {
    type: CREATE_PROJECT_SUCCESS,
    payload: {
      data: message,
    },
  };
}

export function createProjectStarted() {
  return {
    type: CREATE_PROJECT_STARTED,
  };
}

export function submitUserStoryCreationForm(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(submitUserStoryCreationFormSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(submitUserStoryCreationFormFailure(err.message));

  makeHTTPRequest(url, request, success, failure);

}

export function submitUserStoryCreationFormSuccess(info) {
  return {
    type: SUBMIT_USERSTORY_SUCCESS,
    payload: {
      data: info,
    }

  }
}

export function submitUserStoryCreationFormFailure(message) {
  return {
    type: SUBMIT_USERSTORY_FAIL,
    payload: {
      error: message
    }
  }
}

export function submitUserStoryCreationFormStarted() {
  return {
    type: SUBMIT_USERSTORY_STARTED,
  }
}

export function createUser(url, request, dispatch) {
  const success = (res) => dispatch(createUserSuccess(res));
  const failure = (err) => dispatch(createUserFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

export function createUserFailure(message) {
  return {
    type: CREATE_USER_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function createUserSuccess(message) {
  return {
    type: CREATE_USER_SUCCESS,
    payload: {
      data: message,
    },
  };
}

export function createUserStarted() {
  return {
    type: CREATE_USER_STARTED,
  };
}

export function updateProfile(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => {
    dispatch(updateProfileSuccess(res));
    console.log(res)
  };
  //função ser executado em caso de falha
  const failure = (err) => dispatch(updateProfileFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

export function updateProfileSuccess(result) {
  return {
    type: UPDATE_PROFILE_SUCCESS,
    payload: {
      data: result
    }
  }
}

export function updateProfileFailure(message) {
  return {
    type: UPDATE_PROFILE_FAILURE,
    payload: {
      error: message
    }
  }
}

export function updateProfileStarted() {
  return {
    type: UPDATE_PROFILE_STARTED,

  }
}

export function editProject(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => dispatch(editProjectSuccess(res));
  //função ser executado em caso de falha
  const failure = (err) => dispatch(editProjectFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
  // console.log("3 success" success);
  // console.log("4 failure", failure);
}

export function editProjectFailure(message) {
  return {
    type: CREATE_RESOURCE_FAILURE,
    payload: {
      error: message,
    },
  };
}

export function editProjectSuccess(message) {
  return {
    type: CREATE_RESOURCE_SUCCESS,
    payload: {
      success: message,
    },
  };
}

export function editProjectStarted() {
  return {
    type: CREATE_RESOURCE_STARTED,
  };
}

export function searchUserProfile(url, request, dispatch) {
  //função ser executado em caso de sucesso
  const success = (res) => {
    dispatch(searchUserProfileSuccess(res));
    console.log(res)
  };
  //função ser executado em caso de falha
  const failure = (err) => dispatch(searchUserProfileFailure(err.message));
  makeHTTPRequest(url, request, success, failure);
}

export function searchUserProfileSuccess(result) {
  return {
    type: SEARCH_BY_PROFILE_SUCCESS,
    payload: {
      data: [...result]
    }
  }
}

export function searchUserProfileFailure(message) {
  return {
    type: SEARCH_BY_PROFILE_FAILURE,
    payload: {
      error: message
    }
  }
}
