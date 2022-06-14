import {
    CREATE_PROJECT_STARTED,
    CREATE_PROJECT_SUCCESS,
    CREATE_PROJECT_FAILURE,
    CREATE_PROJECT_DEFAULT_SUCCESS
} from "../actions/CreateProjectActions";


export default function createProjectReducer(state, action) {
    switch (action.type) {
        case CREATE_PROJECT_FAILURE:
            return {
                ...state,
                createProjectData: {
                    loading: false,
                    error: action.payload.error,
                    success: null,
                    data: []
                }
            }
        case CREATE_PROJECT_SUCCESS:
            return {
                ...state,
                createProjectData: {
                    loading: false,
                    error: null,
                    success: action.payload.success,
                    data: [...action.payload.data]
                }
            }
        case CREATE_PROJECT_STARTED:
            return {
                ...state,
                createProjectData: {
                    loading: true,
                    error: null,
                    success: null,
                    data: []
                }
            }
        case CREATE_PROJECT_DEFAULT_SUCCESS:
            return {
                ...state,
                createProjectData: {
                    loading: false,
                    error: null,
                    success: action.payload.success,
                    data: [...action.payload.data]
                }
            }
        default:
            return state
    }
}
