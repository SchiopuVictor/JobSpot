import axios from "axios";
import api from "./axiosConfig";

export function postApplication(data){
    return api.post(`/application/${data}`);
}