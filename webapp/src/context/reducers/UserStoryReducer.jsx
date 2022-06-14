
import {
    SUBMIT_USER_STORY_FAIL,
    SUBMIT_USER_STORY_SUCCESS,
    SUBMIT_USER_STORY_STARTED,


} from "../actions/UserStoryActions";
import {

    FETCH_USER_STORY_FAIL,
    FETCH_USER_STORY_SUCCESS,
    FETCH_USER_STORY_STARTED,
} from "../actions/FetchUserStoryByIdActions";


export default function userStoryReducer(state, action) {

    switch (action.type) {
        case SUBMIT_USER_STORY_STARTED:
            return {
                ...state,
                userStories: {
                    loading: true,
                    error: null,
                    success: null,
                    data: []
                }
            }
        case SUBMIT_USER_STORY_SUCCESS:
            return {
                ...state,
                userStories: {
                    loading: false,
                    error: null,
                    success: action.payload.data,
                    data: []
                }
            }
        case SUBMIT_USER_STORY_FAIL:
            return {
                ...state,
                userStories: {
                    loading: false,
                    error: action.payload.error,
                    success: false,
                    data: []
                }
            }
        
        
        case FETCH_USER_STORY_STARTED:
            return {
                ...state,
                getUserStoryById: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_USER_STORY_SUCCESS:
            return {
                ...state,
                getUserStoryById: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_USER_STORY_FAIL:
            return {
                ...state,
                getUserStoryById: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        default:
            return state
    }
}