import { makeHTTPRequest } from "../../services/Service";

const SPRINT = "sprint";

export const FETCH_SPRINT_STARTED = 'FETCH_SPRINT_STARTED';
export const FETCH_SPRINT_SUCCESS = 'FETCH_SPRINT_SUCCESS';
export const FETCH_SPRINT_FAILURE = 'FETCH_SPRINT_FAILURE';

export const CREATE_SPRINT_STARTED = 'CREATE_SPRINT_STARTED';
export const CREATE_SPRINT_SUCCESS = 'CREATE_SPRINT_SUCCESS';
export const CREATE_SPRINT_FAILURE = 'CREATE_SPRINT_FAILURE';



export function fetchSprint(url, request, dispatch) {

    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchSprintSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchSprintFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchSprintStarted() {
    return {
        reducerFile: SPRINT,
        type: FETCH_SPRINT_STARTED,
    }
}

export function fetchSprintSuccess(data) {
    return {
        reducerFile: SPRINT,
        type: FETCH_SPRINT_SUCCESS,
        payload: {
            data: [...data]
        }
    }
}

export function fetchSprintFailure(message) {
    return {
        reducerFile: SPRINT,
        type: FETCH_SPRINT_FAILURE,
        payload: {
            error: message
        }
    }
}

export function createSprint(url, request, dispatch) {

    //função ser executado em caso de sucesso
    const success = (res) => {
        dispatch(createSprintSuccess(res)); console.log(res)};
    //função ser executado em caso de falha
    const failure = (err) => {
        dispatch(createSprintFailure(err.message));console.log(err.message)};

    makeHTTPRequest(url, request, success, failure);

}

export function createSprintStarted() {
    return {
        reducerFile: SPRINT,
        type: CREATE_SPRINT_STARTED,
    }
}

export function createSprintSuccess(data) {
    return {
        reducerFile: SPRINT,
        type: CREATE_SPRINT_SUCCESS,
        payload: {
            data: data
        }
    }
}
export function createSprintFailure(message) {
    return {
        reducerFile: SPRINT,
        type: CREATE_SPRINT_FAILURE,
        payload: {
            error: message
        }
    }
}

