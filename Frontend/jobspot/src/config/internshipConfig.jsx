import axios from "axios";
import api from "./axiosConfig";

export function postInternship(data){
    return api.post(`/internship`,data);
}
