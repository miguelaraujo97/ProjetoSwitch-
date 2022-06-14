import {
    FETCH_SPRINT_STARTED,
    FETCH_SPRINT_SUCCESS,
    FETCH_SPRINT_FAILURE,

    CREATE_SPRINT_STARTED,
    CREATE_SPRINT_SUCCESS,
    CREATE_SPRINT_FAILURE

} from "../actions/SprintActions";


export default function sprintReducer(state, action) {

    switch (action.type) {
        case FETCH_SPRINT_STARTED:
            return {
                ...state,
                sprints: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_SPRINT_SUCCESS:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_SPRINT_FAILURE:
            return {
                ...state,
                sprints: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        case CREATE_SPRINT_STARTED:
            return {
                ...state,
                sprint: {
                    loading: true,
                    error: null,
                    success : null,
                    data: []
                }
            }
        case CREATE_SPRINT_SUCCESS:
            return {
                ...state,
                sprint: {
                    loading: false,
                    error: null,
                    success : action.payload.data,
                    data: action.payload.data
                }
            }
        case CREATE_SPRINT_FAILURE:
            return {
                ...state,
                sprint: {
                    loading: false,
                    error: action.payload.error,
                    success : null,
                    data: ""
                }
            }
        default:
            return state
    }

}