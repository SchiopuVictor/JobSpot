import '../styles/Internshipuri.css';
import { useEffect, useState } from 'react';
import black from '../assets/black.jpg';
import InternshipDetails from '../pages/InternshipDetails.jsx'
import { jwtDecode } from 'jwt-decode';
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext"; 
import {getInternships} from '../config/internshipConfig.jsx';

function Internshipuri() {

const navigate = useNavigate();
const [min, setMin] = useState(0);
const [max, setMax] = useState(20000);
const {accessToken} = useAuth();
const [error,setErorr] = useState("");
const colors = [
    "#E8F5E9",
    "#E3F2FD",
    "#FCE4EC",
    "#FFF3E0",
    "#F3E5F5",
    "#E0F7FA"
];

const decodeToken = accessToken ? jwtDecode(accessToken) : null;

const [allInternships,setAllInternships] = useState([]);

 useEffect(()=>
    {
    
    async function fetchInternships() {
    try{
        const response = await getInternships();
        setAllInternships(response.data);
    
    }catch(error){
        console.log(error?.response?.data?.mesagge || "incarcarea internshipurilor a iesuat!!");
    }}
        fetchInternships();
    },[]);

    return (
        <div className='page'>
            <div className='search'>
                <div className='search-bar'>
                    <svg className="btn-icon"
                         viewBox="0 0 24 24"
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.214 0 4.24-.805 5.81-2.13l3.66 3.66c.147.146.34.22.53.22s.383-.073.53-.22c.295-.293.295-.767.02-1.06zM11 18c-3.86 0-7-3.14-7-7s3.14-7 7-7 7 3.14 7 7-3.14 7-7 7z"/>
                    </svg>
                    <select name="selectInternship">
                        <option value="">Search</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <hr className='vertical-hr' />

                <div className='search-location'>
                    <svg
                        className='point-search'
                        viewBox="-2.4 -2.4 28.80 28.80"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <g id="SVGRepo_iconCarrier">
                            <path d="M4 10.1433C4 5.64588 7.58172 2 12 2C16.4183 2 20 5.64588 20 10.1433C20 14.6055 17.4467 19.8124 13.4629 21.6744C12.5343 22.1085 11.4657 22.1085 10.5371 21.6744C6.55332 19.8124 4 14.6055 4 10.1433Z" />
                        </g>
                    </svg>
                    <select name="selectLocation">
                        <option value="">Location</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <hr className='vertical-hr' />

                <div className='search-experience'>
                    <svg
                        className='experience-icon'
                        viewBox="0 -2.01 57.856 57.856"
                        xmlns="http://www.w3.org/2000/svg">
                        <g id="SVGRepo_iconCarrier">
                            <path id="suitcase_work" data-name="suitcase work" d="M986.8,360.974c-.007-3.76-2.637-7.035-6.218-7.073H968.915v-5.2a3.841,3.841,0,0,0-3.832-3.835H950.657a3.843,3.843,0,0,0-3.837,3.835v5.2H935.158c-3.584.038-6.211,3.313-6.216,7.073v6.763a1.665,1.665,0,0,1,0,.258,3.836,3.836,0,0,0,.106.9,6.505,6.505,0,0,0,2.707,3.634v17.641a8.558,8.558,0,0,0,8.536,8.536h35.156a8.563,8.563,0,0,0,8.538-8.536V372.524a6.53,6.53,0,0,0,2.7-3.641,3.729,3.729,0,0,0,.106-.888c0-.055,0-.1,0-.135s0-.076,0-.123ZM949.232,348.7a1.438,1.438,0,0,1,1.425-1.424h14.426a1.438,1.438,0,0,1,1.425,1.424v5.2H949.227Zm32.339,25.267v16.2a6.163,6.163,0,0,1-6.124,6.125H940.291a6.167,6.167,0,0,1-6.127-6.125v-16.2c.082.041.186.078.27.119.466.22.949.434,1.458.642a54.109,54.109,0,0,0,14.793,3.076v2.569a3.018,3.018,0,0,0,3.012,3.012h8.339a3.016,3.016,0,0,0,3.012-3.012V377.8a70.2,70.2,0,0,0,7.307-.993,42.571,42.571,0,0,0,7.489-2.083h0c.515-.21,1-.427,1.469-.648C981.4,374.039,981.489,374,981.571,373.964Zm-28.476,1.587v-1.108a.617.617,0,0,1,.6-.6h8.339a.617.617,0,0,1,.6.6v1.108h0v4.818a.62.62,0,0,1-.6.6H953.7a.619.619,0,0,1-.6-.6v-4.818Zm27.485-19.236c1.929,0,3.8,1.965,3.8,4.659V367.7a1.3,1.3,0,0,0-.007.161,1.387,1.387,0,0,0,.007.176,2.162,2.162,0,0,1-.047.326c-.124.827-1.153,1.956-3.012,3.008-.134.076-.274.151-.415.223s-.276.143-.424.215c-.176.088-.361.174-.551.259-.309.141-.63.28-.977.414a45.067,45.067,0,0,1-9.778,2.42c-1.32.194-2.7.352-4.132.477v-.935a3.016,3.016,0,0,0-3.012-3.014H953.7a3.017,3.017,0,0,0-3.012,3.014v.935q-1.74-.153-3.375-.375a46.383,46.383,0,0,1-10.533-2.522c-.351-.135-.679-.279-1-.422-.174-.079-.338-.158-.5-.238s-.328-.16-.485-.242c-.129-.068-.253-.133-.375-.2-1.865-1.055-2.9-2.186-3.024-3.009a2.913,2.913,0,0,1-.048-.322c0-.024.006-.067.011-.129l0-.064,0-.062c-.005-.059-.009-.1-.011-.126l0-6.693c0-2.694,1.875-4.657,3.806-4.659Z" transform="translate(-928.942 -344.862)" />
                        </g>
                    </svg>
                    <select name="selectExperience">
                        <option value="">Experience</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <hr className='vertical-hr' />

                <div className='search-salari'>
                    <svg
                        className="money-icon"
                        viewBox="0 0 24 24"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            d="M22 10C19.79 10 18 8.21 18 6H22V10Z
                            M18 18C18 15.79 19.79 14 22 14V18H18Z
                            M2 14C4.21 14 6 15.79 6 18H2V14Z
                            M6 6C6 8.21 4.21 10 2 10V6H6Z"
                        />
                        <path
                            d="M14.07 9.5H11.33C10.6 9.5 10 10.06 10 10.75C10 11.44 10.6 12 11.33 12H13.11C13.85 12 14.44 12.56 14.44 13.25C14.44 13.94 13.85 14.5 13.11 14.5H10M12 9.5V8.5M12 14.5V15.5"
                        />
                    </svg>
                    <select name="selectSalary">
                        <option value="">Salary</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <hr className='vertical-hr' />

                <div className='seatch-salari-range'>
                    <div className='upper'>
                        <div className='upper-left'>
                            <h3>Salary range</h3>
                        </div>
                        <div className='upper-right'>
                            ${min} - ${max}
                        </div>
                    </div>
                    <div className="down">
                        <div
                            className="slider-track"
                            style={{
                                background: `linear-gradient(
                                    to right,
                                    #ddd ${min}%,
                                    #2563eb ${min}%,
                                    #2563eb ${max}%,
                                    #ddd ${max}%
                                )`
                            }}
                        ></div>

                        <input
                            type="range"
                            min="0"
                            max="100"
                            value={min}
                            onChange={(e) => setMin(Math.min(Number(e.target.value), max))}
                        />

                        <input
                            type="range"
                            min="0"
                            max="20000"
                            value={max}
                            onChange={(e) => setMax(Math.max(Number(e.target.value), min))}
                        />
                    </div>
                </div>
            </div>

            <div className='main-container'>
                <aside className='left-container'>
                    <div className='card-filter' style={{backgroundImage:`url(${black})`}}>
                        <h2>Get your best profesion with SpotJob</h2>

                        {
                            decodeToken?.role !== "Company" ? 
                        (
                            <Link className='moreInfo' to ="/">
                                Learn more
                            </Link>
                        ):
                            (
                            <Link className='' to ="/addintership">
                                Add Internship
                            </Link> 
                            )
                        }
                        
                    </div>
                    <div className='filter-part'>
                        <div className='filter-title'>
                            <h2>Filter</h2>
                            <svg xmlns="http://w3.org" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                            <path d="M12 19l-7-7 7-7"></path>
                            </svg>
                        </div>
                        <div className='filter-group'>
                            <h2>Working schedule</h2>
                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Full time</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Part time</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Project work</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Volunteering</label>
                            </div>

                        </div>

                        <div className='filter-group'>
                            <h2>Emplayment type</h2>
                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Full day</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Flexible schedule</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Shift work</label>
                            </div>

                            <div className='filter-option'>
                                <input type='checkbox'/>
                                <label>Distant work</label>
                            </div>
                        </div>
                    </div>
                </aside>
                <section className='right-container'>
                        <div className='title-container'>
                            <div className='right-text'>
                                <h2>Recommanded jobs</h2>
                                <label>{allInternships.length}</label>
                            </div>
                            <div className='sort-by'>
                                <h2>Sort by: </h2>
                                <select name="selectSalary">
                                    <option value="">Last updated</option>
                                    <option value="salary">Salary</option>
                                </select>
                            </div>
                        </div> 

                        <div className='jobs-grid'>
                            {
                                allInternships.map((intern) => {
                                    return (
                                        <div className='internship-card' key={intern.id}>
                                           <div className='top-card'
                                                style={{backgroundColor:colors[Math.floor(Math.random() * colors.length)]}}
                                                >
                                                <div className='text-card'>
                                                    <div className='informa-left'>
                                                        <label>{intern.createdAt}</label>
                                                        <h4>{intern.title}</h4>
                                                        <h2>{intern.companyName}</h2>
                                                    </div> 
                                                    <div className='informa-right'>
                                                        <svg width="64px" height="64px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                            <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                                                            <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g>
                                                            <g id="SVGRepo_iconCarrier"> 
                                                                <path fillRule="evenodd" clipRule="evenodd" d="M6.85665 2.30447C8.2922 2.16896 10.3981 2 12 2C13.6019 2 15.7078 2.16896 17.1433 2.30447C18.4976 2.4323 19.549 3.51015 19.6498 4.85178C19.7924 6.74918 20 9.90785 20 12.2367C20 14.022 19.8781 16.2915 19.7575 18.1035C19.697 19.0119 19.6365 19.8097 19.5911 20.3806C19.5685 20.6661 19.5496 20.8949 19.5363 21.0526L19.5209 21.234L19.5154 21.2966L19.5153 21.2976C19.5153 21.2977 19.5153 21.2977 18.7441 21.2308L19.5153 21.2976C19.4927 21.5553 19.3412 21.7845 19.1122 21.9075C18.8831 22.0305 18.6072 22.0309 18.3779 21.9085L12.1221 18.5713C12.0458 18.5307 11.9542 18.5307 11.8779 18.5713L5.62213 21.9085C5.39277 22.0309 5.11687 22.0305 4.88784 21.9075C4.65881 21.7845 4.50732 21.5554 4.48466 21.2977L5.25591 21.2308C4.48466 21.2977 4.48467 21.2978 4.48466 21.2977L4.47913 21.234L4.46371 21.0526C4.45045 20.8949 4.43154 20.6661 4.40885 20.3806C4.3635 19.8097 4.30303 19.0119 4.24255 18.1035C4.12191 16.2915 4 14.022 4 12.2367C4 9.90785 4.20763 6.74918 4.3502 4.85178C4.45102 3.51015 5.50236 2.4323 6.85665 2.30447ZM5.93179 19.9971L11.1455 17.2159C11.6791 16.9312 12.3209 16.9312 12.8545 17.2159L18.0682 19.9971C18.1101 19.4598 18.1613 18.7707 18.2124 18.0019C18.3327 16.1962 18.4516 13.9687 18.4516 12.2367C18.4516 9.97099 18.2482 6.86326 18.1057 4.96632C18.0606 4.366 17.5938 3.89237 16.9969 3.83603C15.5651 3.70088 13.5225 3.53846 12 3.53846C10.4775 3.53846 8.43487 3.70088 7.00309 3.83603C6.40624 3.89237 5.9394 4.366 5.89429 4.96632C5.75175 6.86326 5.54839 9.97099 5.54839 12.2367C5.54839 13.9687 5.66734 16.1962 5.78756 18.0019C5.83874 18.7707 5.88993 19.4598 5.93179 19.9971Z" fill="#000000">
                                                                </path> 
                                                            </g>
                                                        </svg>
                                                        <img  
                                                            className='img-url'
                                                            src={intern.imgUrl
                                                                ? `http://localhost:8080${intern.imgUrl}`
                                                                : "/default-logo.png"}
                                                             alt={intern.companyName} />
                                                    </div>

                                                </div>
                                                <div className='info-part'>
                                                    <label>
                                                        {intern.type}
                                                    </label>
                                                    <label>
                                                        {intern.working_schedule}
                                                    </label>
                                                    <label>
                                                        {intern.duration}
                                                    </label>
                                                    <label>
                                                        {intern.status}
                                                    </label>
                                                </div>
                                            </div> 
                                            <div className='down-card'>
                                                <div className='plata'>
                                                    <h3>${intern.salary}/month</h3>
                                                    <h4>{intern.location}</h4>
                                                </div>
                                                <div className='detail-btn'>
                                                    <button onClick={()=>navigate("/infointernship")}>
                                                        Details
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        );
                                    }
                                )
                            }
                        </div>
                    </section>
            </div>

        </div>
    );
}

export default Internshipuri;