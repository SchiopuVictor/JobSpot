import { useState } from "react";
import {postStudent,registerUser,postCompany} from "../config/studentConfig";
import { useNavigate } from "react-router-dom";

function Register() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");
    const [error,setErorr] =useState("");
    const navigation = useNavigate();
    
    //student
    const [studentFormData, setStudentFormData] = useState({
        user_id:"",
        firstName:"",
        lastName:"",
        phone:"",
        university:"",
        faculty:"",
        studyYear:"",
        city:"",
        description:""
    });

    //company
    const [companyFormData,setCompanyFormData] = useState({
        user:"",
        companyName:"",
        description:"",
        website:"",
        phone:"",
        address:"",
        city:"",
        industry:""
    });

   async function handleSubmit(e){
            e.preventDefault();
            try{
                const response = await registerUser({
                    email,
                    password,
                    role
                });
                
                const userId = response.data.id;

                if( role === "Student"){
                    await postStudent({
                        ...studentFormData,
                        user_id: userId
                    });
                    navigation("/login");
                }else if (role === "Company"){
                    await postCompany({
                        ...companyFormData,
                        user: userId
                    });
                    navigation("/login");
                }

            }catch(err){
                setErorr(err.response?.data?.message || "A aparut o problmea la conectare!!");
            } 
    }

    return (
        <form className="auth-form auth-form--register" onSubmit={handleSubmit}>
            <h2 className="auth-title">Create your JobSpot account</h2>

            <input
                className="auth-form__input"
                type="email"
                placeholder="Email"
                autoComplete="username"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                className="auth-form__input"
                type="password"
                placeholder="Password"
                autoComplete="new-password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <select
                className="auth-form__input auth-form__select"
                onChange={(e) => setRole(e.target.value)}
            >
                <option value="">Select account type</option>
                <option value="Student">Student</option>
                <option value="Company">Company</option>
            </select>

            {role !== "" &&
                (role === "Student" ? (

                    <div className="auth-form__extra">

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Nume" 
                            value={studentFormData.firstName}
                            onChange={(e)=>setStudentFormData({...studentFormData, firstName:e.target.value})}
                        />

                        <input className="auth-form__input"
                            type="text" 
                            placeholder="Prenume" 
                            value={studentFormData.lastName}   
                            onChange={(e)=>setStudentFormData({...studentFormData, lastName: e.target.value})} 
                        />
                    
                        <input className="auth-form__input"
                            type="tel"
                            placeholder="Telefon" 
                            value={studentFormData.phone}
                            onChange={(e)=>setStudentFormData({...studentFormData, phone:e.target.value})}
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="University" 
                            value={studentFormData.university}
                            onChange={(e)=>setStudentFormData({...studentFormData, university:e.target.value})}
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Facultatea" 
                            value={studentFormData.faculty}
                            onChange={(e)=>setStudentFormData({...studentFormData, faculty: e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="number" 
                            placeholder="Faculty year" 
                            value={studentFormData.studyYear}
                            onChange={(e)=>setStudentFormData({...studentFormData, studyYear: e.target.value})}
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="City" 
                            value={studentFormData.city}
                            onChange={(e)=>setStudentFormData({...studentFormData, city: e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Descriere" 
                            value={studentFormData.description}
                            onChange={(e)=>setStudentFormData({...studentFormData, description: e.target.value})}    
                        />

                    </div>
                ) : (
                    <div className="auth-form__extra">

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Nume Company"
                            value={companyFormData.companyName}
                            onChange={(e)=>setCompanyFormData({...companyFormData, companyName:e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="url" 
                            placeholder="Website" 
                            value={companyFormData.website}
                            onChange={(e)=>setCompanyFormData({...companyFormData, website: e.target.value})}
                        />

                        <input className="auth-form__input" 
                            type="tel" 
                            placeholder="Telefon" 
                            value={companyFormData.phone}
                            onChange={(e)=>setCompanyFormData({...companyFormData, phone: e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Adresa" 
                            value={companyFormData.address}
                            onChange={(e)=>setCompanyFormData({...companyFormData, address: e.target.value})}
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="City" 
                            value={companyFormData.city}
                            onChange={(e)=>setCompanyFormData({...companyFormData, city: e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Industry" 
                            value={companyFormData.industry}
                            onChange={(e)=>setCompanyFormData({...companyFormData, industry: e.target.value})}    
                        />

                        <input className="auth-form__input" 
                            type="text" 
                            placeholder="Descriere" 
                            value={companyFormData.description}
                            onChange={(e)=>setCompanyFormData({...companyFormData, description:e.target.value})}
                        />

                    </div>
                ))}

            <button className="auth-button" 
                    type="submit"
            >
                SIGN UP
            </button>
        </form>
    );
}

export default Register;