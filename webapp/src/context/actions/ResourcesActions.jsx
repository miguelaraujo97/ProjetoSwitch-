import { makeHTTPRequest } from "../../services/Service";

const RESOURCES = "resources";

export const FETCH_RESOURCES_STARTED = 'FETCH_RESOURCES_STARTED';
export const FETCH_RESOURCES_SUCCESS = 'FETCH_RESOURCES_SUCCESS';
export const FETCH_RESOURCES_FAILURE = 'FETCH_RESOURCES_FAILURE';


export function fetchResources(url, request, dispatch) {

    const success = (res) => dispatch(fetchResourcesSuccess(res));

    const failure = (err) => dispatch(fetchResourcesFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchResourcesStarted() {
    return {
        reducerFile: RESOURCES,
        type: FETCH_RESOURCES_STARTED,
    }
}

export function fetchResourcesSuccess(data) {
    return {
        reducerFile: RESOURCES,
        type: FETCH_RESOURCES_SUCCESS,
        payload: {
            data: [data]
        }
    }
}

export function fetchResourcesFailure(message) {
    return {
        reducerFile: RESOURCES,
        type: FETCH_RESOURCES_FAILURE,
        payload: {
            error: message
        }
    }
}