import { makeHTTPRequest } from "../../services/Services";

export const FETCH_PBL_STARTED = 'FETCH_PBL_STARTED';
export const FETCH_PBL_SUCCESS = 'FETCH_PBL_SUCCESS';
export const FETCH_PBL_FAILURE = 'FETCH_PBL_FAILURE';


export function fetchProductBacklog(url, request, dispatch) {

    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchProductBacklogSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchProductBacklogFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchProductBacklogStarted() {
    return {
        type: FETCH_PBL_STARTED,
    }
}

export function fetchProductBacklogSuccess(data) {
    return {
        type: FETCH_PBL_SUCCESS,
        payload: {
            data: [data]
        }
    }
}

export function fetchProductBacklogFailure(message) {
    return {
        type: FETCH_PBL_FAILURE,
        payload: {
            error: message
        }
    }
}
