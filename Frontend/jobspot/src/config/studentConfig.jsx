import axios from "axios";
import api from "./axiosConfig";

const url = "http://localhost:8080/api";

export function postStudent (data){
    return(axios.post(`${url}/student`,data));
}

export function registerUser(data){
    return(axios.post(`${url}/users`,data));
}

export function postCompany(data){
    return(axios.post(`${url}/company`,data));
}

export function getCompanyInformation(id){
    return(api.get(`/company/${id}`));
}


