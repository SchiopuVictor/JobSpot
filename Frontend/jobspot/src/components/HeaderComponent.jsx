import '../styles/header.css';
import {useAuth} from '../context/AuthContext';
import { NavLink, useNavigate } from 'react-router-dom';

function HeaderComponent() {
    
    const {accessToken,logout} = useAuth(); 
    const navigate = useNavigate();
    return (
        <div>
            <header className="header">
                <nav className="navbar">
                    <NavLink className="navbar-brand" to="/">
                        JobSpot                
                    </NavLink>

                   <div className="navbar-info" id="navbarNav">
                        <ul className="jobspot-nav">
                            <li>
                                <NavLink className="jobspot-link" to="/" end>
                                    Home
                                </NavLink>
                            </li>

                            <li>
                                <NavLink className="jobspot-link" to="/internship">
                                    Internships
                                </NavLink>
                            </li>

                            <li>
                                <NavLink className="jobspot-link" to="/notificari">
                                    Notificari
                                </NavLink>
                            </li>

                            <li>
                                <NavLink className="jobspot-link" to="/cv">
                                    CV
                                </NavLink>
                            </li>
                        </ul>
                    </div>
                    {   
                        accessToken === null?
                        <div className="log-regis">
                            <NavLink to="/login" className="log-btn">
                                Login
                            </NavLink>
                            <NavLink to="/register" className="regi-btn">
                                Sign Up
                            </NavLink>
                        </div>
                        :
                        <div>
                            <button className="log-out"
                                onClick={()=>{
                                    logout()
                                    navigate("/login")
                                }}
                            >
                                Log out
                            </button>

                        </div>
                    }
                </nav>
            </header>
        </div>
    );
}

export default HeaderComponent;