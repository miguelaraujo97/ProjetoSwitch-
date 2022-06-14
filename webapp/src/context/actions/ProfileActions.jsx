import { makeHTTPRequest } from "../../services/Service";
export const CREATE_PROFILE_STARTED = "CREATE_PROFILE_STARTED";
export const CREATE_PROFILE_SUCCESS = "CREATE_PROFILE_SUCCESS";
export const CREATE_PROFILE_FAILURE = "CREATE_PROFILE_FAILURE";

const PROFILE = "profile";

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
        reducerFile: PROFILE,
        type: CREATE_PROFILE_FAILURE,
        payload: {
            error: message,
        },
    };
}

export function createProfileSuccess(message) {
    return {
        reducerFile: PROFILE,
        type: CREATE_PROFILE_SUCCESS,
        payload: {
            data: message,
        },
    };
}

export function createProfileStarted() {
    return {
        reducerFile: PROFILE,
        type: CREATE_PROFILE_STARTED,
    };
}