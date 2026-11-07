import axios from "axios";

const api = axios.create( 
    {
        baseURL: "http://localhost:8080/api",
        withCredentials: true
    });

    let accessToken = null;

    api.interceptors.request.use(
    (config)=>{
        if(accessToken!==null){
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    });

    export function setAuthToken(token){
        accessToken = token;
    }

    export default api;

