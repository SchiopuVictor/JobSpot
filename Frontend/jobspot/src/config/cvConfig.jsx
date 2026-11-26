import api from "./axiosConfig";

export function postCv(file) {
    const formData = new FormData();
    formData.append("file", file);

    return api.post("/cv", formData);
}

export function getCvById(id){
    return api.get(`/cv/studentcv/${id}`);
}