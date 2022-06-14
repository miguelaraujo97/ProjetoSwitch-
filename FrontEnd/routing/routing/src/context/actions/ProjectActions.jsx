import { makeHTTPRequest } from "../../services/Services";

export const FETCH_PROJECTS_STARTED = 'FETCH_PROJECTS_STARTED';
export const FETCH_PROJECTS_SUCCESS = 'FETCH_PROJECTS_SUCCESS';
export const FETCH_PROJECTS_FAILURE = 'FETCH_PROJECTS_FAILURE';


export function fetchProjects(url, request, dispatch) {
    
    
    
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProjectsSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProjectsFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchProjectsStarted() {
    return {
        type: FETCH_PROJECTS_STARTED,
    }
}

export function fetchProjectsSuccess(data) {
    return {
        type: FETCH_PROJECTS_SUCCESS,
        payload: {
            data: [data]
        }
    }
}

export function fetchProjectsFailure(message) {
    return {
        type: FETCH_PROJECTS_FAILURE,
        payload: {
            error: message
        }
    }
}
