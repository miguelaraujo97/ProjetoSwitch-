import {


    CREATE_PROFILE_FAILURE,
    CREATE_PROFILE_SUCCESS,
    CREATE_PROFILE_STARTED

} from '../actions/ProfileActions'


export default function profileReducer(state, action) {

    switch (action.type) {

        case CREATE_PROFILE_FAILURE:
            return {
                ...state,
                profiles: {
                    loading: false,
                    error: action.payload.error,
                    success: null,
                    data: ""
                }
            }
        case CREATE_PROFILE_SUCCESS:
            return {
                ...state,
                profiles: {
                    loading: false,
                    error: null,
                    success: null,
                    data: action.payload.data
                }
            }
        case CREATE_PROFILE_STARTED:
            return {
                ...state,
                profiles: {
                    loading: true,
                    error: null,
                    success: null,
                    data: []
                }
            }

        default:
            return state
    }
}



