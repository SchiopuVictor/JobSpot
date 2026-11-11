import { useState } from "react";
import '../styles/CreateInternship.css';
import {postInternship} from '../config/internshipConfig.jsx';
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import { useAuth } from "../context/AuthContext"; 

function CreateInternship(){

    const [error,setErorr] =useState("");
    const navigate = useNavigate();
    const {accessToken} = useAuth();

    const [formData,setFormData] = useState(
        {
            title:"",
            description:"",
            requirements:"",
            responsibilities:"",
            location:"",
            type:"",
            duration:"",
            availablePositions:"",
            salary:"",
            applicationDeadline:"",
            status:""
        }
    );
    
    async function handleSubmit(e) {
        e.preventDefault()
        try{

            const decodeToken = jwtDecode(accessToken);
            const companyId = decodeToken.sub;

            const response = await postInternship({...formData,company_id: companyId});
            navigate('/internship');

        }catch(error){
            setErorr(error.response?.data?.message || "A aparut o problema la conectare cu backend-ul"
            )
        }
    };

    return (
            <div className="page">
                <div className="container-title">
                    <h2 className="title">Add Your Internship</h2>
                    <h4 className="text">Share your internship and connect with future professionals</h4>
                </div> 
                <div className="asede">
                    <aside>
                        <div className="info-card">
                            <h2>
                            Let's have the future <span>TODAY</span>
                            </h2>

                            <div className="direct-card">
                            <h3 className="h3-t">Your Future</h3>
                            <h3 className="h3-m">JobSpot</h3>
                            <h3 className="h3-b">//FUTURE</h3>
                            </div>

                            <h3>Internship</h3>
                        </div>
                    </aside>
                    <div className="internship">
                        <form className="auth-form internship-form" onSubmit={handleSubmit}> 
                            <input type="text"
                                placeholder="Title"
                                className="intput-feald" 
                                value={formData.title}
                                onChange={(e)=>setFormData({...formData, title: e.target.value})}
                            />
                           
                            <input type="text" 
                                placeholder="Requirements"
                                className="intput-feald"
                                value={formData.requirements}
                                onChange={(e)=>setFormData({...formData, requirements: e.target.value})}
                            />
                            <input type="text"
                                placeholder="Responsibilites"
                                className="intput-feald" 
                                value={formData.responsibilities}
                                onChange={(e)=>setFormData({...formData, responsibilities: e.target.value})}
                            />
                            <input type="text" 
                                placeholder="Location"
                                className="intput-feald"
                                value={formData.location}
                                onChange={(e)=>setFormData({...formData, location: e.target.value})}
                            />
                            <select className="intput-feald select-form"
                                onChange={(e) => setFormData({...formData, type: e.target.value})}
                                >
                                <option value="">Internship Type</option>
                                <option value="Remote">Remote</option>
                                <option value="Hybrid">Hybrid</option>
                                <option value="On_Site">On_Site</option>
                            </select>

                            <input type="text"
                                placeholder="Duratoin" 
                                className="intput-feald"
                                value={formData.duration}
                                onChange={(e)=>setFormData({...formData, duration: e.target.value})}
                            />
                            <input type="text" 
                                placeholder="Available Position"
                                className="intput-feald"
                                value={formData.availablePositions}
                                onChange={(e)=>setFormData({...formData, availablePositions: e.target.value})}
                            />

                            <input type="text"
                                placeholder="Salary"
                                className="intput-feald"
                                value={formData.salary}
                                onChange={(e)=>setFormData({...formData, salary: e.target.value})}
                           />
                            
                            <input type="text"
                                placeholder="Application Deadline"
                                className="intput-feald"
                                value={formData.applicationDeadline}
                                onChange={(e)=>{setFormData({...formData, applicationDeadline: e.target.value})}}
                            />

                            <select className="intput-feald select-form"
                                onChange={(e) => setFormData({...formData, status: e.target.value})}

                                >
                                <option value="">Internship Status</option>
                                <option value="Active">Active</option>
                                <option value="Closed">Closed</option>
                                <option value="Expired">Expired</option>
                            </select>
                            
                            <input type="text" 
                                placeholder="Description"
                                className="intput-feald full-width"
                                value={formData.description}
                                onChange={(e)=>setFormData({...formData, description: e.target.value})}
                            />

                            <div>
                                <button className="form-button" type="submit">
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                 </div>
            </div>
    );
}

export default CreateInternship;