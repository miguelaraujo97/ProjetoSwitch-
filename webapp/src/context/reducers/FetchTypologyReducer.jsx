import {
    FETCH_TYPOLOGIES_STARTED,
    FETCH_TYPOLOGIES_SUCCESS,
    FETCH_TYPOLOGIES_FAILURE
} from '../actions/FetchTypologyActions'


function fetchTypologiesReducer(state, action) {
    switch (action.type) {
        case FETCH_TYPOLOGIES_STARTED:
            return {
                ...state,
                typologies: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_TYPOLOGIES_SUCCESS:
            return {
                ...state,
                typologies: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_TYPOLOGIES_FAILURE:
            return {
                ...state,
                typologies: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

        default:
            return state
    }
}
export default fetchTypologiesReducer;