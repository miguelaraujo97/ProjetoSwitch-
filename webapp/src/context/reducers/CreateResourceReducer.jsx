import {
    CREATE_RESOURCE_STARTED,
    CREATE_RESOURCE_SUCCESS,
    CREATE_RESOURCE_FAILURE
} from "../actions/CreateResourceActions";


function createResourceReducer(state, action) {
    switch (action.type) {
        case CREATE_RESOURCE_FAILURE:
            return {
                ...state,
                createResource: {
                    loading: false,
                    error: action.payload.error,
                    success: null,
                    data: ""
                }
            }
        case CREATE_RESOURCE_SUCCESS:
            return {
                ...state,
                createResource: {
                    loading: false,
                    error: null,
                    success: null,
                    data: action.payload.data
                }
            }
        case CREATE_RESOURCE_STARTED:
            return {
                ...state,
                createResource: {
                    loading: true,
                    error: null,
                    success: null,
                    data: ""
                }
            }
        default:
            return state
    }
}
export default createResourceReducer;