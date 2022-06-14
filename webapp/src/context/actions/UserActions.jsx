import { makeHTTPRequest } from '../../services/Service';

const USER = "user";

export const FETCH_USERS_STARTED = 'FETCH_USERS_STARTED';
export const FETCH_USERS_SUCCESS = 'FETCH_USERS_SUCCESS';
export const FETCH_USERS_FAILURE = 'FETCH_USERS_FAILURE';

export const SEARCH_BY_PROFILE_SUCCESS = "SEARCH_BY_PROFILE_SUCCESS";
export const SEARCH_BY_PROFILE_FAILURE = "SEARCH_BY_PROFILE_FAILURE";

export const UPDATE_PROFILE_SUCCESS = 'UPDATE_PROFILE_SUCCESS'
export const UPDATE_PROFILE_FAILURE = 'UPDATE_PROFILE_FAILURE'

export const CREATE_USER_SUCCESS = "CREATE_USER_SUCCESS"
export const CREATE_USER_FAILURE = "CREATE_USER_FAILURE"


export function fetchUsers(url, request, dispatch) {

    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchUsersSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchUsersFailure(err.message));

    makeHTTPRequest(url, request, success, failure);
}



export function fetchUsersSuccess(users) {
    return {
        reducerFile: USER,
        type: FETCH_USERS_SUCCESS,
        payload: {
            data:
                [...users]
        }

    }
}

export function fetchUsersFailure(message) {
    return {
        reducerFile: USER,
        type: FETCH_USERS_FAILURE,
        payload: {
            error: message
        }
    }
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
        reducerFile: USER,
        type: SEARCH_BY_PROFILE_SUCCESS,
        payload: {
            data: [...result]
        }
    }
}

export function searchUserProfileFailure(message) {
    return {
        reducerFile: USER,
        type: SEARCH_BY_PROFILE_FAILURE,
        payload: {
            error: message
        }
    }
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
        reducerFile: USER,
        type: UPDATE_PROFILE_SUCCESS,
        payload: {
            data: result
        }
    }
}

export function updateProfileFailure(message) {
    return {
        reducerFile: USER,
        type: UPDATE_PROFILE_FAILURE,
        payload: {
            error: message
        }
    }
}



export function createUser(url, request, dispatch) {
    const success = (res) => dispatch(createUserSuccess(res));
    const failure = (err) => dispatch(createUserFailure(err.message));
    makeHTTPRequest(url, request, success, failure);
}

export function createUserFailure(message) {
    return {
        reducerFile: USER,
        type: CREATE_USER_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function createUserSuccess(res) {
    return {
        reducerFile: USER,
        type: CREATE_USER_SUCCESS,
        payload: {
            data: res
        },
    };
}


