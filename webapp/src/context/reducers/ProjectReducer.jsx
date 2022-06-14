import {
    FETCH_PROJECTS_STARTED,
    FETCH_PROJECTS_SUCCESS,
    FETCH_PROJECTS_FAILURE
} from '../actions/ProjectActions'


export default function projectsReducer(state, action) {

    switch (action.type) {
        case FETCH_PROJECTS_STARTED:
            return {
                ...state,
                projects: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROJECTS_SUCCESS:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_PROJECTS_FAILURE:
            return {
                ...state,
                projects: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        default:
            return state
    }
}
