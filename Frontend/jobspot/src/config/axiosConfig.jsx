import axios from "axios";

const api = axios.create( 
    {
        baseURL: "http://localhost:8080/api",
        withCredentials: true
    });

    let accessToken = null;
    let isRefreshing = false;
    let failedQueue = [];


     export function setAuthToken(token){
        accessToken = token;
    }

    function processQueue(error, token = null)
    {
        failedQueue.forEach(promise=>{
            if(error){
                promise.reject(error);
            }else{
                promise.resolve(token);
            }
        });
        failedQueue = [];
    }

    api.interceptors.request.use(
    (config)=>{
        if(accessToken!==null){
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    });

    api.interceptors.response.use(
        response=>
            response,
        async (error) =>{
            const originalRequest = error.config;
            
            if(error.response?.status===401 &&
                !originalRequest._retry && 
                !originalRequest.url?.includes("/auth/refresh") &&
                !originalRequest.url?.includes("/auth/login") 
            ){
                if(isRefreshing){
                    return new Promise((resolve, reject)=>{
                        failedQueue.push({resolve,reject});
                    }).then(token=>{
                        originalRequest.headers.Authorization = `Bearer ${token}`;
                        return api(originalRequest);
                    });
                }
                originalRequest._retry = true;
                isRefreshing = true;
                try{
                    const response = await api.post("/auth/refresh");
                    const newToken = response.data.token;
                    setAuthToken(newToken);
                    processQueue(null, newToken);
                    originalRequest.headers.Authorization = `Bearer ${newToken}`;
                    return api(originalRequest);

                }catch(refreshEror){
                    
                    processQueue(refreshEror,null);
                    setAuthToken(null);
                    
                    window.dispatchEvent(new Event("auth:logout"));

                    return Promise.reject(refreshEror);
                }finally{
                    isRefreshing = false;
                }
            }
            return Promise.reject(error);
        }
    );

    export default api;

