import '../styles/header.css';
import {useAuth} from '../context/AuthContext';
import { Link, useNavigate } from 'react-router-dom';

function HeaderComponent() {
    
    const { accessToken,logout} = useAuth(); 
    const navigate = useNavigate();
    return (
        <div>
            <header className="header">
                <nav className="navbar 
                     navbar-expand-md 
                     navbar-light 
                     bg-white 
                     rounded-pill 
                     shadow-sm
                     px-4 
                     d-flex 
                     justify-content-between 
                     align-items-center 
                     position-absolute
                     top-0 
                     start-50
                     translate-middle-x
                     z-3">
                    <Link className="navbar-brand fw-bold text-dark" to="/">
                        JobSpot                
                    </Link>
                    <button 
                        className="navbar-toggler" 
                        type="button" 
                        data-toggle="collapse" 
                        data-target="#navbarNav" 
                        aria-controls="navbarNav" 
                        aria-expanded="false" 
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse justify-content-center" id="navbarNav">
                        <ul className="navbar-nav gap-3">
                            <li className="nav-item">
                                <Link className="nav-link fw-semibold text-primary" to="/">
                                    Home
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link fw-semibold text-secondary" to="/internship">
                                    Internships
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link fw-semibold text-secondary" to="/notificari">
                                    Notificari
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link fw-semibold text-secondary" to="/cv">
                                    CV
                                </Link>
                            </li>
                        </ul>
                    </div>
                    {   
                        accessToken === null?
                        <div className="d-none d-md-block">
                            <Link to="/login" className="btn btn-primary rounded-pill px-4 hover-shadow hover-invert">
                                Login
                            </Link>
                            <Link to="/register" className="btn btn-outline-primary rounded-pill px-4 ms-2 hover-shadow hover-invert">
                                Register
                            </Link>
                        </div>
                        :
                        <div>
                            <button className="btn btn-primary rounded-pill px-4 hover-shadow hover-invert"
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