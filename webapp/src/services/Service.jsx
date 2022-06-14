
// Dummy Data
export const URL_DUMMY_API_DUMMYJSON = 'https://dummyjson.com';
export const URL_DUMMY_API_JSONPLACEHOLDER = 'https://jsonplaceholder.typicode.com';
export const URL_DUMMY_API_ADVICE = 'https://api.adviceslip.com/advice';

// Localhost
export const URL_API = 'http://localhost:8080';



export function makeHTTPRequest(url, request, success, failure) {
    /* fetch(url, request)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message)); */

    fetch(url, request)
        .then(async  response => {
            const data = await response.json();

            //console.log("fetch data", data);
            //console.log("fetch response",response);

            // check for error response
            if (!response.ok && (response.status!==200 || response.status!==201)) {
                // get error message from body or default to response statusText
                //const error = data || response;
                failure(data || response);
            }else{
                success(data);
            }
            
        })
        .catch(err => failure(err.message));
}