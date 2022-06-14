import { makeHTTPRequest } from '../../services/Service';

const USERS_TODOS = "usersTodos";

export const FETCH_USER_TODOS_STARTED = 'FETCH_USER_TODOS_STARTED';
export const FETCH_USER_TODOS_SUCCESS = 'FETCH_USER_TODOS_SUCCESS';
export const FETCH_USER_TODOS_FAILURE = 'FETCH_USER_TODOS_FAILURE';
export const DELETE_USER_TODOS = 'DELETE_USER_TODOS';


export function fetchUserTodos(url, request, dispatch) {
    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchUserTodosSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchUserTodosFailure(err.message));

    makeHTTPRequest(url, request, success, failure);
}


export function fetchUserTodosStarted(id) {
    return {
        reducerFile: USERS_TODOS,
        type: FETCH_USER_TODOS_STARTED,
        payload: {
            userid: id
        }
    }
}

export function fetchUserTodosSuccess(todos) {
    return {
        reducerFile: USERS_TODOS,
        type: FETCH_USER_TODOS_SUCCESS,
        payload: {
            data: [...todos]
        }

    }
}

export function fetchUserTodosFailure(message) {
    return {
        reducerFile: USERS_TODOS,
        type: FETCH_USER_TODOS_FAILURE,
        payload: {
            error: message
        }
    }
}

export function deleteUserTodos() {
    return {
        reducerFile: USERS_TODOS,
        type: DELETE_USER_TODOS,
    }
}