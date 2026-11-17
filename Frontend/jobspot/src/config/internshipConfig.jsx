import axios from "axios";
import api from "./axiosConfig";

export async function postInternship(formData) {
    const { imgUrl, ...internshipData } = formData; 

    const data = new FormData();
    data.append(
        "internship",
        new Blob([JSON.stringify(internshipData)], { type: "application/json" })
    );

    if (imgUrl) {
        data.append("image", imgUrl); 
    }

    return api.post("/internship", data);
}

export function getInternships(){
    return api.get(`/internship/all`);
}

export function getIntershipById(id){
    return api.get(`/internship/${id}`);
}