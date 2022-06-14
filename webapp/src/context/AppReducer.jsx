import projectReducer from './reducers/ProjectReducer';
import fetchProjectByCodeReducer from './reducers/FetchProjectReducer';
import createProjectReducer from './reducers/CreateProjectReducer';
import createResourceReducer from './reducers/CreateResourceReducer';
import resourcesReducer from './reducers/ResourcesReducer';
import userReducer from './reducers/UserReducer';
import userTodosReducer from './reducers/UserTodosReducer';
import profileReducer from './reducers/ProfileReducer';
import userStoryReducer from './reducers/UserStoryReducer';
import productBacklogReducer from './reducers/ProductBacklogReducer';
import fetchTypologyReducer from './reducers/FetchTypologyReducer';
import sprintReducer from './reducers/SprintReducer';


export default function reducer(state, action) {
    //console.log(state);
    //console.log(action);

    switch (action.reducerFile) {
        case "project":
            return projectReducer(state, action);
        
        case "projectByCode":
            return fetchProjectByCodeReducer(state, action);
        
        case "createProject":
            return createProjectReducer(state, action);
        
        case "resources":
            return resourcesReducer(state, action);
        
        case "createResource":
            return createResourceReducer(state, action);
        
        case "user":
            return userReducer(state, action);
        
        case "userTodos":
            return userTodosReducer(state, action);
        
        case "profile":
            return profileReducer(state, action);
        
        case "userStoryReducer":
            return userStoryReducer(state, action);
        
        case "productBacklog":
            return productBacklogReducer(state, action);
        
        case "typology":
            return fetchTypologyReducer(state, action);
        
        case "sprint":
            return sprintReducer(state, action);    
        
        default:
            return state
    }
}