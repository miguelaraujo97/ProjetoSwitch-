import {
    FETCH_RESOURCES_STARTED,
    FETCH_RESOURCES_SUCCESS,
    FETCH_RESOURCES_FAILURE
} from '../actions/ResourcesActions'


export default function resourcesReducer(state, action) {

    switch (action.type) {
        case FETCH_RESOURCES_STARTED:
            return {
                ...state,
                resources: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_RESOURCES_SUCCESS:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_RESOURCES_FAILURE:
            return {
                ...state,
                resources: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        default:
            return state
    }
}
