import { makeHTTPRequest } from "../../services/Service";

export const CREATE_RESOURCE_STARTED = "CREATE_RESOURCE_STARTED";
export const CREATE_RESOURCE_SUCCESS = "CREATE_RESOURCE_SUCCESS";
export const CREATE_RESOURCE_FAILURE = "CREATE_RESOURCE_FAILURE";

const CREATE_RESOURCE = "createResource";

export function createResources(url, request, dispatch) {

    const success = (res) => dispatch(createResourceSuccess(res));

    const failure = (err) => dispatch(createResourceFailure(err.message));
    makeHTTPRequest(url, request, success, failure);

}

export function createResourceSuccess(message) {
    return {
        reducerFile: CREATE_RESOURCE,
        type: CREATE_RESOURCE_SUCCESS,
        payload: {
            data: message,
        },
    };
}

export function createResourceFailure(message) {
    return {
        reducerFile: CREATE_RESOURCE,
        type: CREATE_RESOURCE_FAILURE,
        payload: {
            error: message,
        },
    };
}
export function createResourceStarted() {
    return {
        reducerFile: CREATE_RESOURCE,
        type: CREATE_RESOURCE_STARTED,
    };
}