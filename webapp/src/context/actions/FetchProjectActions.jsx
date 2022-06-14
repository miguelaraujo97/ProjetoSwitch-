import { makeHTTPRequest } from "../../services/Service";

const PROJECT_BY_CODE = "projectByCode";

export const FETCH_PROJECT_BY_CODE_STARTED = 'FETCH_PROJECT_BY_CODE_STARTED';
export const FETCH_PROJECT_BY_CODE_SUCCESS = 'FETCH_PROJECT_BY_CODE_SUCCESS';
export const FETCH_PROJECT_BY_CODE_FAILURE = 'FETCH_PROJECT_BY_CODE_FAILURE';


export function fetchProjectByCode(url, request, dispatch) {
       
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProjectByCodeSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProjectByCodeFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchProjectByCodeStarted() {
    return {
        reducerFile: PROJECT_BY_CODE,
        type: FETCH_PROJECT_BY_CODE_STARTED,
    }
}

export function fetchProjectByCodeSuccess(data) {
    return {
        reducerFile: PROJECT_BY_CODE,
        type: FETCH_PROJECT_BY_CODE_SUCCESS,
        payload: {
            data:
                [data]
        }
    }
}

export function fetchProjectByCodeFailure(message) {
    return {
        reducerFile: PROJECT_BY_CODE,
        type: FETCH_PROJECT_BY_CODE_FAILURE,
        payload: {
            error: message
        }
    }
}
