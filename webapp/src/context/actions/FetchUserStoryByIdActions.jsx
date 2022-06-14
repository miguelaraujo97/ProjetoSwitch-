import { makeHTTPRequest } from "../../services/Service";

const USER_STORY_BY_ID = "userStoryReducer";

export const FETCH_USER_STORY_STARTED = 'FETCH_USER_STORY_STARTED';
export const FETCH_USER_STORY_SUCCESS = 'FETCH_USER_STORY_SUCCESS';
export const FETCH_USER_STORY_FAIL = 'FETCH_USER_STORY_FAIL';



export function fetchProjectByCode(url, request, dispatch) {

    //função ser executado em caso de sucesso
    const success = (res) => dispatch(fetchUserStoryByIdSuccess(res));
    //função ser executado em caso de falha
    const failure = (err) => dispatch(fetchUserStoryByIdFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function fetchUserStoryByIdStarted() {
    return {
        reducerFile: USER_STORY_BY_ID,
        type: FETCH_USER_STORY_STARTED,
    }
}

export function fetchUserStoryByIdSuccess(data) {
    return {
        reducerFile: USER_STORY_BY_ID,
        type: FETCH_USER_STORY_SUCCESS,
        payload: {
            data:
                [data]
        }
    }
}

export function fetchUserStoryByIdFailure(message) {
    return {
        reducerFile: USER_STORY_BY_ID,
        type: FETCH_USER_STORY_FAIL,
        payload: {
            error: message
        }
    }
}