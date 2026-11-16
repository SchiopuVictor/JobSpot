import React from 'react';
import '../styles/footer.css';
import { useAuth } from '../context/AuthContext';
import { Link, useNavigate } from 'react-router-dom';

function FooterComponent() {
    const { accessToken, logout } = useAuth();
    const navigate = useNavigate();

    return (
        <footer className="footer-container">
            <div className="container">
                <div className="row gy-4">

                    <div className="col-12 col-md-5">
                        <Link to="/" className="footer-brand">JobSpot</Link>
                        <p className="footer-description">
                            Găsește rapid cele mai potrivite internship-uri și oportunități de carieră pentru studenți și absolvenți.
                        </p>
                    </div>

                    <div className="col-6 col-md-2">
                        <h6 className="footer-heading">Useful Link</h6>
                        <ul className="footer-list">
                            <li><Link to="/" className="footer-link">Home</Link></li>
                            <li><Link to="/solutions" className="footer-link">Solutions</Link></li>
                            <li><Link to="/pricing" className="footer-link">Pricing</Link></li>
                            <li><Link to="/contact" className="footer-link">Contact</Link></li>
                        </ul>
                    </div>

                    <div className="col-6 col-md-2">
                        <h6 className="footer-heading">Product</h6>
                        <ul className="footer-list">
                            <li><Link to="/internship" className="footer-link">Internships</Link></li>
                            <li><Link to="/companies" className="footer-link">Companies</Link></li>
                            <li><Link to="/jobs" className="footer-link">Jobs</Link></li>
                        </ul>
                    </div>

                    <div className="col-6 col-md-3">
                        <h6 className="footer-heading">Account</h6>
                        {accessToken === null ? (
                            <ul className="footer-list">
                                <li><Link to="/register" className="footer-link">Sign up</Link></li>
                                <li><Link to="/login" className="footer-link">Login</Link></li>
                            </ul>
                        ) : (
                            <ul className="footer-list">
                                <li>
                                    <button
                                        type="button"
                                        className="footer-link"
                                        onClick={() => {
                                            logout();
                                            navigate("/login");
                                        }}
                                    >
                                        Sign out
                                    </button>
                                </li>
                            </ul>
                        )}
                    </div>
                </div>

                <div className="footer-bottom">
                    <span className="footer-copy">
                        Copyright © 2026 JobSpot.com
                    </span>

                    <div className="footer-social">
                        <a href="https://twitter.com" target="_blank" rel="noopener noreferrer" className="footer-social-btn" aria-label="X">
                            X
                        </a>
                        <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" className="footer-social-btn" aria-label="Facebook">
                            f
                        </a>
                        <a href="https://instagram.com" target="_blank" rel="noopener noreferrer" className="footer-social-btn" aria-label="Instagram">
                            IG
                        </a>
                    </div>
                </div>

            </div>
        </footer>
    );
}

export default FooterComponent;