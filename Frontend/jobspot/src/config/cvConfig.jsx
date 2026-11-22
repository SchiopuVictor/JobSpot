import api from "./axiosConfig";

export function postCv(data){
    return api.post(`/cv`,data);
}

export function getCvById(id){
    return api.get(`/cv/studentcv/${id}`);
}