import { makeHTTPRequest } from "../../services/Service";

const CREATE_PROJECT = "createProject";

export const CREATE_PROJECT_STARTED = "CREATE_PROJECT_STARTED";
export const CREATE_PROJECT_SUCCESS = "CREATE_PROJECT_SUCCESS";
export const CREATE_PROJECT_FAILURE = "CREATE_PROJECT_FAILURE";
export const CREATE_PROJECT_DEFAULT_SUCCESS = "CREATE_PROJECT_DEFAULT_FAILURE";


export function createProjectPOST(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(createProjectSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(createProjectFailure(err));

    makeHTTPRequest(url, request, success, failure);
    // console.log("3 success" success);
    // console.log("4 failure", failure);
}

export function createProjectStarted() {
    return {
        reducerFile: CREATE_PROJECT,
        type: CREATE_PROJECT_STARTED,
    };
}

export function createProjectSuccess(data) {
    return {
        reducerFile: CREATE_PROJECT,
        type: CREATE_PROJECT_SUCCESS,
        payload: {
            data: [data]
        },
    };
}

export function createProjectFailure(error) {
    return {
        reducerFile: CREATE_PROJECT,
        type: CREATE_PROJECT_FAILURE,
        payload: {
            error: error
        },
    };
}

export function createProjectDefaultValues(data) {
    return {
        reducerFile: CREATE_PROJECT,
        type: CREATE_PROJECT_DEFAULT_SUCCESS,
        payload: {
            data: [data]
        },
    };
}

