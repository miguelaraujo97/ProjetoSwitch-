import { makeHTTPRequest } from "../../services/Service";

export const SUBMIT_USER_STORY_STARTED = "SUBMIT_USER_STORY_STARTED";
export const SUBMIT_USER_STORY_SUCCESS = "SUBMIT_USER_STORY_SUCCESS";
export const SUBMIT_USER_STORY_FAIL = "SUBMIT_USER_STORY_FAIL";

const USER_STORY = "userStory";


export function CreateUserStory(url, request, dispatch) {

    const success = (res) => dispatch(userStoryCreationSuccess(res));

    const failure = (err) => dispatch(userStoryCreationFailure(err.message));

    makeHTTPRequest(url, request, success, failure);

}

export function userStoryCreationSuccess(info) {
    return {
        reducerFile: USER_STORY,
        type: SUBMIT_USER_STORY_SUCCESS,
        payload: {
            data: info,
        }

    }
}

export function userStoryCreationFailure(message) {
    return {
        reducerFile: USER_STORY,
        type: SUBMIT_USER_STORY_FAIL,
        payload: {
            error: message
        }
    }
}

export function userStoryCreationStarted() {
    return {
        reducerFile: USER_STORY,
        type: SUBMIT_USER_STORY_STARTED,
    }
}