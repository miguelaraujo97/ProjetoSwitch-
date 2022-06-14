import {
    CREATE_PROFILE_STARTED,
    CREATE_PROFILE_SUCCESS,
    CREATE_PROFILE_FAILURE,
    CREATE_TYPOLOGY_STARTED,
    CREATE_TYPOLOGY_SUCCESS,
    CREATE_TYPOLOGY_FAILURE,
    CREATE_RESOURCE_STARTED,
    CREATE_RESOURCE_SUCCESS,
    CREATE_RESOURCE_FAILURE,
    CREATE_PROJECT_STARTED,
    CREATE_PROJECT_SUCCESS,
    CREATE_PROJECT_FAILURE,
    SUBMIT_USERSTORY_STARTED,
    SUBMIT_USERSTORY_SUCCESS,
    SUBMIT_USERSTORY_FAIL,
    CREATE_USER_STARTED,
    CREATE_USER_SUCCESS,
    CREATE_USER_FAILURE,
    UPDATE_PROFILE_FAILURE,
    //UPDATE_PROFILE_STARTED,
    UPDATE_PROFILE_SUCCESS,
    EDIT_PROJECT_STARTED,
    EDIT_PROJECT_SUCCESS,
    EDIT_PROJECT_FAILURE,
    SEARCH_BY_PROFILE_FAILURE,
    SEARCH_BY_PROFILE_SUCCESS

} from './Actions'

import {
    FETCH_PROJECTS_STARTED,
    FETCH_PROJECTS_SUCCESS,
    FETCH_PROJECTS_FAILURE
} from './actions/ProjectActions'

import {
    FETCH_PBL_STARTED,
    FETCH_PBL_SUCCESS,
    FETCH_PBL_FAILURE
} from "./actions/ProductBacklogActions";

function reducer(state, action) {
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
        case FETCH_PBL_STARTED:
            return {
                ...state,
                userStories: {
                    loading: true,
                    error: null,
                    data: []
                }
            }
        case FETCH_PBL_SUCCESS:
            return {
                ...state,
                userStories: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }
        case FETCH_PBL_FAILURE:
            return {
                ...state,
                userStories: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            }

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

    case CREATE_TYPOLOGY_FAILURE:
      return {
        ...state,
        typologies: {
          loading: false,
          error: action.payload.error,
          success: null,
          data: []
        }
      }
    case CREATE_TYPOLOGY_SUCCESS:
      return {
        ...state,
        typologies: {
          loading: false,
          error: null,
          success: action.payload.success,
          data: []
        }
      }
    case CREATE_TYPOLOGY_STARTED:
      return {
        ...state,
        typologies: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
    case CREATE_RESOURCE_FAILURE:
      return {
        ...state,
        resources: {
          loading: false,
          error: action.payload.error,
          success: null,
          data: ""
        }
      }
    case CREATE_RESOURCE_SUCCESS:
      return {
        ...state,
        resources: {
          loading: false,
          error: null,
          success: null,
          data: action.payload.data
        }
      }
    case CREATE_RESOURCE_STARTED:
      return {
        ...state,
        resources: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
    case CREATE_PROJECT_FAILURE:
      return {
        ...state,
        resources: {
          loading: false,
          error: action.payload.error,
          success: null,
          data: []
        }
      }
    case CREATE_PROJECT_SUCCESS:
      return {
        ...state,
        resources: {
          loading: false,
          error: null,
          success: action.payload.success,
          data: []
        }
      }
    case CREATE_PROJECT_STARTED:
      return {
        ...state,
        resources: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
    case SUBMIT_USERSTORY_STARTED:
      return {
        ...state,
        userStories: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
    case SUBMIT_USERSTORY_SUCCESS:
      return {
        ...state,
        userStories: {
          loading: false,
          error: null,
          success: action.payload.data,
          data: []
        }
      }
    case SUBMIT_USERSTORY_FAIL:
      return {
        ...state,
        userStories: {
          loading: false,
          error: action.payload.error,
          success: false,
          data: []
        }
      }
    case CREATE_USER_FAILURE:
      return {
        ...state,
        users: {
          loading: false,
          error: action.payload.error,
          success: null,
          data: ""
        }
      }
    case CREATE_USER_SUCCESS:
      return {
        ...state,
        users: {
          loading: false,
          error: null,
          success: null,
          data: action.payload.data
        }
      }
    case CREATE_USER_STARTED:
      return {
        ...state,
        users: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
    case UPDATE_PROFILE_SUCCESS:
      return {
        ...state,
        profileUpdate: {
          loading: false,
          error: null,
          data: action.payload.data,
        }
      }
    case UPDATE_PROFILE_FAILURE:
      return {
        ...state,
        profileUpdate: {
          loading: false,
          error: action.payload.error,
          data: "",
        }
      }
    case EDIT_PROJECT_FAILURE:
      return {
        ...state,
        resources: {
          loading: false,
          error: action.payload.error,
          success: null,
          data: []
        }
      }
    case EDIT_PROJECT_SUCCESS:
      return {
        ...state,
        resources: {
          loading: false,
          error: null,
          success: action.payload.success,
          data: []
        }
      }
    case EDIT_PROJECT_STARTED:
      return {
        ...state,
        resources: {
          loading: true,
          error: null,
          success: null,
          data: []
        }
      }
      case SEARCH_BY_PROFILE_SUCCESS:
        return {
          ...state,
          users: {
            loading: false,
            error: null,
            data: [...action.payload.data]
          }
        }
      case SEARCH_BY_PROFILE_FAILURE:
        return {
          ...state,
          users: {
            loading: false,
            error: action.payload.error,
            data: "",
          }
        }


    default:
      return state
  }
}
export default reducer;