import {
    FETCH_USERS_STARTED,
    FETCH_USERS_SUCCESS,
    FETCH_USERS_FAILURE,

    SEARCH_BY_PROFILE_SUCCESS,
    SEARCH_BY_PROFILE_FAILURE,

    UPDATE_PROFILE_SUCCESS,
    UPDATE_PROFILE_FAILURE,

    CREATE_USER_SUCCESS,
    CREATE_USER_FAILURE


} from '../actions/UserActions'


export default function userReducer(state, action) {

    switch (action.type) {

        case FETCH_USERS_STARTED:
            return {
                ...state,
                users: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_USERS_SUCCESS:
            return {
                ...state,
                users: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_USERS_FAILURE:
            return {
                ...state,
                users: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case SEARCH_BY_PROFILE_SUCCESS:
            return {
                ...state,
                users: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case SEARCH_BY_PROFILE_FAILURE:
            return {
                ...state,
                users: {
                    loading: false,
                    error: action.payload.error,
                    data: "",
                }
            }

        case UPDATE_PROFILE_SUCCESS:
            return {
                ...state,
                profileUpdate: {
                    loading: false,
                    error: null,
                    data: action.payload.data,
                }
            }
        case UPDATE_PROFILE_FAILURE:
            return {
                ...state,
                profileUpdate: {
                    loading: false,
                    error: action.payload.error,
                    data: "",
                }
            }

        case CREATE_USER_FAILURE:
            return {
                ...state,
                user: {
                    loading: false,
                    error: action.payload.error,
                    success: null,
                    data: ""
                }
            }
        case CREATE_USER_SUCCESS:
            return {
                ...state,
                user: {
                    loading: false,
                    error: null,
                    success: null,
                    data: action.payload.data
                }
            }

        default:
            return state
    }
}



