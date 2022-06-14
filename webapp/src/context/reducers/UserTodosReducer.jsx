import {
    FETCH_USER_TODOS_STARTED,
    FETCH_USER_TODOS_SUCCESS,
    FETCH_USER_TODOS_FAILURE,
    DELETE_USER_TODOS,
} from '../actions/UserTodosActions'


export default function userTodosReducer(state, action) {

    switch (action.type) {

        case FETCH_USER_TODOS_STARTED:
            return {
                ...state,
                todos: {
                    loading: true,
                    error: null,
                    data: [],
                    userid: action.payload.userid,
                }
            }
        case FETCH_USER_TODOS_SUCCESS:
            return {
                ...state,
                todos: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data],
                    userid: state.todos.userid,
                }
            }
        case FETCH_USER_TODOS_FAILURE:
            return {
                ...state,
                todos: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                    userid: 0,
                }
            }
        case DELETE_USER_TODOS:
            return {
                ...state,
                todos: {
                    loading: false,
                    error: null,
                    data: [],
                    userid: 0,
                }
            }
        default:
            return state
    }
}
