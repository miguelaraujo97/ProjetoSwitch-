import { makeHTTPRequest } from "../../services/Service";

const TYPOLOGY = "typology";

export const FETCH_TYPOLOGIES_STARTED = "FETCH_TYPOLOGIES_STARTED";
export const FETCH_TYPOLOGIES_SUCCESS = "FETCH_TYPOLOGIES_SUCCESS";
export const FETCH_TYPOLOGIES_FAILURE = "FETCH_TYPOLOGIES_FAILURE";


export function fetchTypologyActions(url, request, dispatch) {
    
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchTypologiesSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchTypologiesFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchTypologiesStarted() {
    return {
        reducerFile: TYPOLOGY,
        type: FETCH_TYPOLOGIES_STARTED,
    }
}

export function fetchTypologiesSuccess(data) {
    return {
        reducerFile: TYPOLOGY,
        type: FETCH_TYPOLOGIES_SUCCESS,
        payload: {
            data: [data]
        }
    }
}

export function fetchTypologiesFailure(message) {
    return {
        reducerFile: TYPOLOGY,
        type: FETCH_TYPOLOGIES_FAILURE,
        payload: {
            error: message
        }
    }
}
