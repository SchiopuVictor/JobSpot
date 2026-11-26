import { Link, useParams } from 'react-router-dom';
import '../styles/InternshipDetails.css'
import { useEffect, useState } from 'react';
import {getIntershipById, postInternship} from '../config/internshipConfig.jsx';
import { getCompanyInformation, getStudentInfo,getUserInfoByStudent } from '../config/studentConfig.jsx';
import { jwtDecode } from 'jwt-decode';
import { useAuth } from "../context/AuthContext";
import { postApplication } from '../config/applicatipnConfig.jsx';
import { postCv } from '../config/cvConfig.jsx';
import {getCvById} from "../config/cvConfig.jsx";
import Cv from './Cv.jsx';

function InternshipDetails(){
    const { accessToken } = useAuth();
    const {id} = useParams();
    const [error, setError] = useState("");
    const [showModal,setShowModal] = useState(false);
    const [internship, setInternships] = useState(null);
    const [company, setCopmany] = useState(null);
    const [cv,setCv] = useState(null);
    const [selectCv,setSelectCv] = useState(null);

    const decodeToken = accessToken ? jwtDecode(accessToken) : null;

    const [application,setApplication] = useState({
        cv_id: "",
        internship_id:"",
        coverLetter: ""
    });

    const [personalInfo, setPersonalInfo] = useState({
        fullName: "",
        phone: "",
        email: ""
    });

    useEffect(()=>{
        async function getInformation(){
            try{
                const response = await getIntershipById(id);
                setInternships(response.data);

                const studentResponse = await getStudentInfo(decodeToken?.sub);
                const userResponse = await getUserInfoByStudent(decodeToken?.sub);
                const cvResponse = await getCvById(studentResponse.data.id);
                setCv(cvResponse.data.length > 0 ? cvResponse.data[0] : null);
                
                setPersonalInfo({
                    fullName: `${studentResponse.data.firstName} ${studentResponse.data.lastName}`,
                    phone: studentResponse.data.phone,
                    email: userResponse.data.email
                });
                
                const companyResponse = await getCompanyInformation(response.data.company_id);
                setCopmany(companyResponse.data);

            }catch(err){
                console.log(err?.response?.data?.message || "Încărcarea internshipurilor a eșuat!");
                setError("Nu am putut încărca internshipurile. Încearcă din nou.");
            } 
        }

        getInformation();
    },[id]);

   async function handleChange(e){
        try{
            e.preventDefault();
            
            const finalCv = selectCv || cv;
            
            if (!finalCv) {
                setError("Trebuie să încarci un CV!");
                return;
            }

            let cvId;

            if (selectCv) {
                const response = await postCv(selectCv);
                cvId = response.data.id;
            }else {
                cvId = cv?.id;
            }

            if (!cvId) {
                setError("CV-ul nu a putut fi identificat!");
                return;
            }

            await postApplication({
                cv_id: cvId,
                internship_id: id,
                coverLetter: application.coverLetter
            });
        }catch(err){
            setError(
                err.response?.data?.message ||
                "A apărut o problemă la conectarea cu backend-ul"
            );
        }
    }

    return (
        <div className='pag'>
            <div className='info-pag'>
                <div className='page-title'>
                   <h2>{internship?.title}</h2>    
                </div>
                <div className='job-type'>
                    <div className='job-container'>
                        <div className='job-image'>
                            <img  
                                src={
                                    internship?.imgUrl
                                        ? `http://localhost:8080${internship.imgUrl}`
                                        : "/default-logo.png"
                                }
                                alt={internship?.companyName || "Company"}
                            />
                        </div>
                        <div className='job-subtitle'>
                             <h3>{internship?.companyName}</h3>
                            <h4>{internship?.location} ⋅ {internship?.type} ⋅ {internship?.status}</h4>
                        </div>
                    </div>
                    <div className='jobs-buttons'>
                        <button className='apply-btn' onClick={()=>setShowModal(true)}>
                            Apply for this internship
                        </button>
                        {showModal && (
                            <div className="apply-overlay" onClick={() => setShowModal(false)}>
                                <div
                                    className="apply-modal"
                                    role="dialog"
                                    aria-modal="true"
                                    aria-labelledby="apply-title"
                                    onClick={(e) => e.stopPropagation()}
                                >
                                    <button
                                        type="button"
                                        className="apply-close"
                                        aria-label="Close"
                                        onClick={() => setShowModal(false)}
                                    >
                                        ×
                                    </button>

                                    <h2 id="apply-title" className="apply-title">
                                        Apply for this <span>internship</span>
                                    </h2>
                                    <p className="apply-subtitle">
                                        Completează datele de mai jos și trimite-ți aplicația.
                                    </p>

                                    <form className="apply-form"
                                        onSubmit={handleChange}
                                    >
                                        <div className="apply-field">
                                            <label htmlFor="apply-name">Nume și Prenume</label>
                                            <input
                                                id="apply-name"
                                                type="text"
                                                placeholder="Ion Popescu"
                                                required
                                                value={personalInfo.fullName}
                                                onChange={(e)=>{
                                                    setPersonalInfo({...personalInfo,fullName:e.target.value})
                                                }}
                                            />
                                        </div>

                                        <div className="apply-row">
                                            <div className="apply-field">
                                                <label htmlFor="apply-phone">Număr telefon</label>
                                                <input
                                                    id="apply-phone"
                                                    type="tel"
                                                    placeholder="+373 600 00 000"
                                                    required
                                                    value={personalInfo?.phone}
                                                    onChange={(e)=>{
                                                        setPersonalInfo({...personalInfo,phone:e.target.value})
                                                    }}
                                                />
                                            </div>

                                            <div className="apply-field">
                                                <label htmlFor="apply-email">Email</label>
                                                <input
                                                    id="apply-email"
                                                    type="email"
                                                    placeholder="nume@email.com"
                                                    required
                                                    value={personalInfo?.email}
                                                    onChange={(e)=>{
                                                        setPersonalInfo({...personalInfo,email:e.target.value})
                                                    }}
                                                />
                                            </div>
                                        </div>

                                        <div className="apply-field">
                                            <span className="apply-label">CV</span>
                                            <label htmlFor="apply-cv" className="apply-upload">
                                                <span className="apply-upload-btn">Alege fișier</span>
                                                <span className="apply-upload-name">{selectCv
                                                    ? selectCv.name
                                                    : cv
                                                        ? cv.fileName
                                                        : "Niciun fișier selectat"
                                                }</span>
                                            </label>
                                            <input
                                                id="apply-cv"
                                                type="file"
                                                accept=".pdf,.doc,.docx"
                                                className="apply-upload-input"
                                                onChange={(e)=>{
                                                    setSelectCv(e.target.files[0]);
                                                }
                                            }
                                            />
                                        </div>

                                        <div className="apply-field">
                                            <label htmlFor="apply-letter">Scrisoare de aplicare</label>
                                            <textarea
                                                id="apply-letter"
                                                rows="4"
                                                placeholder="Spune-ne de ce ești potrivit pentru acest internship"
                                                value={application.coverLetter}
                                                onChange={(e)=>{
                                                    setApplication({...application,coverLetter:e.target.value});
                                                }}
                                            />
                                        </div>

                                        <div className="apply-actions">
                                            <button
                                                type="button"
                                                className="apply-cancel"
                                                onClick={() => setShowModal(false)}
                                            >
                                                Close
                                            </button>
                                            <button type="submit" className="apply-submit">
                                                Trimite
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        )}
                        <Link className='save-btn'>
                            <svg className="save-bookmark" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinejoin="round">
                                <path d="M6 3h12v18l-6-4-6 4z" />
                            </svg>
                            Save internship
                        </Link>
                    </div>
                </div>
            </div>
            <div className='more-info'>
                <div className='left-side'>
                    <h2>About the role</h2>
                    <h3>
                        {internship?.description}
                    </h3>
                    <hr />
                    <h2>
                        What you'll do
                    </h2>

                    <h3>
                        {internship?.responsibilities}
                    </h3>
                    <hr />

                    <h2>
                        What we're looking for
                    </h2>
                    <ul className="requirements-list">
                        {internship?.requirements
                            ?.split(/\r?\n/)
                            .filter(item => item.trim() !== "")
                            .map((item, index) => (
                        <li key={index}>{item}</li>
                         ))
                        }
                    </ul>
                </div>
                <aside className='right-aside'>
                    <div className='salary-range'>
                        <h3>${internship?.salary} /month</h3>
                        <label>Estimated salary</label>
                    </div>

                    <div className='benefits-part'>
                        <h3>Benefits</h3>
                        <h4>
                            <svg 
                                className='part-svg'
                                viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                                <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round" stroke="#CCCCCC" strokeWidth="0.24000000000000005"></g>
                                <g id="SVGRepo_iconCarrier">
                                    <path 
                                        d="M12 7V12L14.5 10.5M21 12C21 16.9706 16.9706 21 12 21C7.02944 21 3 16.9706 3 12C3 7.02944 7.02944 3 12 3C16.9706 3 21 7.02944 21 12Z" stroke="#000000" strokeWidth="1.344" strokeLinecap="round" strokeLinejoin="round">
                                    </path>
                                </g>
                            </svg>
                            {internship?.duration}
                        </h4>

                        <h4>
                            <svg 
                                className='part-svg'
                                viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#000000" strokeWidth="0.00024000000000000003">
                                <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                                <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g>
                                <g id="SVGRepo_iconCarrier">
                                    <path 
                                        fillRule="evenodd" clipRule="evenodd" d="M7 1.75C7.41421 1.75 7.75 2.08579 7.75 2.5V3.26272C8.41203 3.24999 9.1414 3.24999 9.94358 3.25H14.0564C14.8586 3.24999 15.588 3.24999 16.25 3.26272V2.5C16.25 2.08579 16.5858 1.75 17 1.75C17.4142 1.75 17.75 2.08579 17.75 2.5V3.32709C18.0099 3.34691 18.2561 3.37182 18.489 3.40313C19.6614 3.56076 20.6104 3.89288 21.3588 4.64124C22.1071 5.38961 22.4392 6.33855 22.5969 7.51098C22.6472 7.88567 22.681 8.29459 22.7037 8.74007C22.7337 8.82106 22.75 8.90861 22.75 9C22.75 9.06932 22.7406 9.13644 22.723 9.20016C22.75 10.0021 22.75 10.9128 22.75 11.9436V14.0564C22.75 15.8942 22.75 17.3498 22.5969 18.489C22.4392 19.6614 22.1071 20.6104 21.3588 21.3588C20.6104 22.1071 19.6614 22.4392 18.489 22.5969C17.3498 22.75 15.8942 22.75 14.0564 22.75H9.94359C8.10583 22.75 6.65019 22.75 5.51098 22.5969C4.33856 22.4392 3.38961 22.1071 2.64124 21.3588C1.89288 20.6104 1.56076 19.6614 1.40314 18.489C1.24997 17.3498 1.24998 15.8942 1.25 14.0564V11.9436C1.24999 10.9127 1.24998 10.0021 1.27701 9.20017C1.25941 9.13645 1.25 9.06932 1.25 9C1.25 8.90862 1.26634 8.82105 1.29627 8.74006C1.31895 8.29458 1.35276 7.88566 1.40314 7.51098C1.56076 6.33856 1.89288 5.38961 2.64124 4.64124C3.38961 3.89288 4.33856 3.56076 5.51098 3.40313C5.7439 3.37182 5.99006 3.34691 6.25 3.32709V2.5C6.25 2.08579 6.58579 1.75 7 1.75ZM2.76309 9.75C2.75032 10.4027 2.75 11.146 2.75 12V14C2.75 15.9068 2.75159 17.2615 2.88976 18.2892C3.02502 19.2952 3.27869 19.8749 3.7019 20.2981C4.12511 20.7213 4.70476 20.975 5.71085 21.1102C6.73851 21.2484 8.09318 21.25 10 21.25H14C15.9068 21.25 17.2615 21.2484 18.2892 21.1102C19.2952 20.975 19.8749 20.7213 20.2981 20.2981C20.7213 19.8749 20.975 19.2952 21.1102 18.2892C21.2484 17.2615 21.25 15.9068 21.25 14V12C21.25 11.146 21.2497 10.4027 21.2369 9.75H2.76309ZM21.1683 8.25H2.83168C2.8477 8.06061 2.86685 7.88123 2.88976 7.71085C3.02502 6.70476 3.27869 6.12511 3.7019 5.7019C4.12511 5.27869 4.70476 5.02502 5.71085 4.88976C6.73851 4.75159 8.09318 4.75 10 4.75H14C15.9068 4.75 17.2615 4.75159 18.2892 4.88976C19.2952 5.02502 19.8749 5.27869 20.2981 5.7019C20.7213 6.12511 20.975 6.70476 21.1102 7.71085C21.1331 7.88123 21.1523 8.06061 21.1683 8.25Z" fill="#000000">
                                    </path> 
                                </g>
                            </svg>
                            {internship?.applicationDeadline}
                        </h4>

                        <h4>
                            <svg 
                                className='part-svg'
                                fill="#000000" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 0 484.006 484.006" xmlSpace="preserve" stroke="#000000" strokeWidth="0.00484006">
                                    <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                                    <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g>
                                    <g id="SVGRepo_iconCarrier"> 
                                    <g> 
                                    <g> <polygon points="484.006,189.845 484.006,179.186 241.897,62.561 0,179.083 0,189.946 241.897,306.471 ">
                                        </polygon> 
                                    <path d="M83.808,251.571v69.7l145.836,70.25h24.506l145.435-70.057c0.14-0.307,0.271-0.613,0.402-0.922V251.57l-158.09,76.152 L83.808,251.571z">
                                    </path> 
                                        <polygon points="458.506,358.195 458.506,222.599 439.359,232.255 439.359,358.195 426.883,396.203 445.742,421.445 452.123,421.445 470.981,396.203 ">
                                        </polygon> 
                                    </g> </g> </g>
                            </svg>
                            {internship?.type}
                        </h4>
                        <h4>
                            <svg 
                                className='part-svg'
                                fill="#000000" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" stroke="#000000" strokeWidth="0.01024">
                                    <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                                    <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g>
                                    <g id="SVGRepo_iconCarrier">
                                        <path d="M746 835.28L544.529 723.678c74.88-58.912 95.216-174.688 95.216-239.601v-135.12c0-89.472-118.88-189.12-238.288-189.12-119.376 0-241.408 99.664-241.408 189.12v135.12c0 59.024 24.975 178.433 100.624 239.089L54 835.278S0 859.342 0 889.342v81.088c0 29.84 24.223 54.064 54 54.064h692c29.807 0 54.031-24.224 54.031-54.064v-81.087c0-31.808-54.032-54.064-54.032-54.064zm-9.967 125.215H64.002V903.28c4.592-3.343 11.008-7.216 16.064-9.536 1.503-.688 3.007-1.408 4.431-2.224l206.688-112.096c18.848-10.224 31.344-29.184 33.248-50.528s-7.008-42.256-23.712-55.664c-53.664-43.024-76.656-138.32-76.656-189.152V348.96c0-45.968 86.656-125.12 177.408-125.12 92.432 0 174.288 78.065 174.288 125.12v135.12c0 50.128-15.568 145.84-70.784 189.28a64.098 64.098 0 0 0-24.224 55.664 64.104 64.104 0 0 0 33.12 50.849l201.472 111.6c1.777.975 4.033 2.031 5.905 2.848 4.72 2 10.527 5.343 14.783 8.288v57.887zM969.97 675.936L765.505 564.335c74.88-58.912 98.224-174.688 98.224-239.601v-135.12c0-89.472-121.872-190.128-241.28-190.128-77.6 0-156.943 42.192-203.12 96.225 26.337 1.631 55.377 1.664 80.465 9.664 33.711-26.256 76.368-41.872 122.656-41.872 92.431 0 177.278 79.055 177.278 126.128v135.12c0 50.127-18.56 145.84-73.775 189.28a64.098 64.098 0 0 0-24.224 55.664 64.104 64.104 0 0 0 33.12 50.848l204.465 111.6c1.776.976 4.032 2.032 5.904 2.848 4.72 2 10.527 5.344 14.783 8.288v56.912H830.817c19.504 14.72 25.408 35.776 32.977 64h106.192c29.807 0 54.03-24.224 54.03-54.064V730.03c-.015-31.84-54.047-54.096-54.047-54.096z">
                                        </path>
                                    </g>
                            </svg>
                            
                            {internship?.working_schedule}
                        </h4>
                    </div>

                    <div className='company-info'>
                        <img src={
                                internship?.imgUrl
                                    ? `http://localhost:8080${internship.imgUrl}`
                                    : "/default-logo.png"
                                }
                                alt={internship?.companyName || "Company"}
                         />
                        <h3>{internship?.companyName}</h3>                        
                        <p>{company?.description}</p>
                        <label>{company?.industry}</label>
                        <a href={company?.website} target = "_blank" 
                            className="company-profile">
                            View company profile ↗
                        </a>
                        <div className='verify'>
                            <span>✔</span>
                            <p>Employer profile verified</p>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    );
}

export default InternshipDetails;