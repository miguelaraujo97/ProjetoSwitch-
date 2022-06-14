import {
    FETCH_PROJECT_BY_CODE_STARTED,
    FETCH_PROJECT_BY_CODE_SUCCESS,
    FETCH_PROJECT_BY_CODE_FAILURE
} from '../actions/FetchProjectActions'


export default function fetchProjectByCodeReducer(state, action) {

    switch (action.type) {
        case FETCH_PROJECT_BY_CODE_STARTED:
            return {
                ...state,
                projectByCode: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PROJECT_BY_CODE_SUCCESS:
            return {
                ...state,
                projectByCode: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_PROJECT_BY_CODE_FAILURE:
            return {
                ...state,
                projectByCode: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        default:
            return state
    }
}
