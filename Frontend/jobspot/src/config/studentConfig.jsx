import axios from "axios";

const url = "http://localhost:8080/api";

function postStudent (data){
    return(axios.post(`${url}/student`,data));
}

function registerUser(data){
    return(axios.post(`${url}/users`,data));
}

function postCompany(data){
    return(axios.post(`${url}/company`,data));
}

export {postStudent,registerUser,postCompany};
