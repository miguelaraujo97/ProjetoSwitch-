import {
    FETCH_PBL_STARTED,
    FETCH_PBL_SUCCESS,
    FETCH_PBL_FAILURE
} from "../actions/ProductBacklogActions";


export default function productBacklogActions(state, action) {

    switch (action.type) {
        case FETCH_PBL_STARTED:
            return {
                ...state,
                productBacklog: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PBL_SUCCESS:
            return {
                ...state,
                productBacklog: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_PBL_FAILURE:
            return {
                ...state,
                productBacklog: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }
        default:
            return state

    }

}