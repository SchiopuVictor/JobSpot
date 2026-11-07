import {createContext, useContext, useState } from "react";
import { setAuthToken } from "../config/axiosConfig";

const AuthContext = createContext();

export function AuthProvider({children}){
    const [accessToken,setAccessToken] = useState(null);

    function logout(){
        setAuthToken(null);
        setAccessToken(null);   
    }

    return (
        <AuthContext.Provider value = {{accessToken,setAccessToken,logout}}>
            {children}
        </AuthContext.Provider>
    );
}



export function useAuth(){
    return useContext(AuthContext);
}

export default AuthContext;


