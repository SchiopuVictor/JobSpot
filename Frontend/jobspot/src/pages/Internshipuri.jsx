import '../styles/Internshipuri.css';
import { useEffect, useState } from 'react';
import black from '../assets/black.jpg';
import { jwtDecode } from 'jwt-decode';
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { getInternships } from '../config/internshipConfig.jsx';

const SALARY_MAX = 20000;
const SALARY_STEP = 100;

function Internshipuri() {
    const navigate = useNavigate();
    const { accessToken } = useAuth();

    const [min, setMin] = useState(0);
    const [max, setMax] = useState(SALARY_MAX);
    const [error, setError] = useState("");
    const [allInternships, setAllInternships] = useState([]);

    const decodeToken = accessToken ? jwtDecode(accessToken) : null;

    useEffect(() => {
        async function fetchInternships() {
            try {
                const response = await getInternships();
                setAllInternships(response.data);
            } catch (err) {
                console.log(err?.response?.data?.message || "Încărcarea internshipurilor a eșuat!");
                setError("Nu am putut încărca internshipurile. Încearcă din nou.");
            }
        }
        fetchInternships();
    }, []);

    const minPercent = (min / SALARY_MAX) * 100;
    const maxPercent = (max / SALARY_MAX) * 100;

    return (
        <div className="int-page">

            <div className="int-search">

                <div className="int-search-item">
                    <svg className="int-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <circle cx="11" cy="11" r="7" />
                        <path d="M20 20l-3.5-3.5" />
                    </svg>
                    <select name="selectInternship" aria-label="Search">
                        <option value="">Search</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <span className="int-divider" />

                <div className="int-search-item">
                    <svg className="int-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <path d="M12 21s-7-6.2-7-11a7 7 0 0 1 14 0c0 4.8-7 11-7 11z" />
                        <circle cx="12" cy="10" r="2.5" />
                    </svg>
                    <select name="selectLocation" aria-label="Location">
                        <option value="">Location</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <span className="int-divider" />

                <div className="int-search-item">
                    <svg className="int-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <rect x="3" y="7" width="18" height="13" rx="2" />
                        <path d="M9 7V5a2 2 0 0 1 2-2h2a2 2 0 0 1 2 2v2M3 13h18" />
                    </svg>
                    <select name="selectExperience" aria-label="Experience">
                        <option value="">Experience</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <span className="int-divider" />

                <div className="int-search-item">
                    <svg className="int-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <rect x="2" y="6" width="20" height="12" rx="2" />
                        <circle cx="12" cy="12" r="2.5" />
                    </svg>
                    <select name="selectSalary" aria-label="Salary">
                        <option value="">Salary</option>
                        <option value="Designer">Designer</option>
                        <option value="Developer">Developer</option>
                        <option value="Backend">Backend</option>
                    </select>
                </div>

                <span className="int-divider" />

                <div className="int-range">
                    <div className="int-range-head">
                        <h3>Salary range</h3>
                        <span>${min} - ${max}</span>
                    </div>
                    <div className="int-range-slider">
                        <div
                            className="int-range-track"
                            style={{
                                background: `linear-gradient(
                                    to right,
                                    #D8CBB3 ${minPercent}%,
                                    #2F6BFF ${minPercent}%,
                                    #2F6BFF ${maxPercent}%,
                                    #D8CBB3 ${maxPercent}%
                                )`
                            }}
                        />
                        <input
                            type="range"
                            min="0"
                            max={SALARY_MAX}
                            step={SALARY_STEP}
                            value={min}
                            aria-label="Minimum salary"
                            onChange={(e) => setMin(Math.min(Number(e.target.value), max))}
                        />
                        <input
                            type="range"
                            min="0"
                            max={SALARY_MAX}
                            step={SALARY_STEP}
                            value={max}
                            aria-label="Maximum salary"
                            onChange={(e) => setMax(Math.max(Number(e.target.value), min))}
                        />
                    </div>
                </div>
            </div>

            <div className="int-main">

                <aside className="int-sidebar">
                    <div className="int-promo" style={{ backgroundImage: `url(${black})` }}>
                        <h2>Get your best profession with JobSpot</h2>

                        {decodeToken?.role !== "Company" ? (
                            <Link className="int-promo-link" to="/">
                                Learn more
                            </Link>
                        ) : (
                            <Link className="int-promo-link" to="/addintership">
                                Add Internship
                            </Link>
                        )}
                    </div>

                    <div className="int-filter">
                        <div className="int-filter-head">
                            <h2>Filter</h2>
                        </div>

                        <div className="int-filter-group">
                            <h3>Working schedule</h3>
                            <label className="int-filter-option"><input type="checkbox" /> Full time</label>
                            <label className="int-filter-option"><input type="checkbox" /> Part time</label>
                            <label className="int-filter-option"><input type="checkbox" /> Project work</label>
                            <label className="int-filter-option"><input type="checkbox" /> Volunteering</label>
                        </div>

                        <div className="int-filter-group">
                            <h3>Employment type</h3>
                            <label className="int-filter-option"><input type="checkbox" /> Full day</label>
                            <label className="int-filter-option"><input type="checkbox" /> Flexible schedule</label>
                            <label className="int-filter-option"><input type="checkbox" /> Shift work</label>
                            <label className="int-filter-option"><input type="checkbox" /> Remote work</label>
                        </div>
                    </div>
                </aside>

                <section className="int-results">
                    <div className="int-results-head">
                        <div className="int-results-title">
                            <h2>Recommended jobs</h2>
                            <span className="int-count">{allInternships.length}</span>
                        </div>
                        <div className="int-sort">
                            <span>Sort by:</span>
                            <select name="sortBy" aria-label="Sort by">
                                <option value="">Last updated</option>
                                <option value="salary">Salary</option>
                            </select>
                        </div>
                    </div>

                    {error && <p className="int-error">{error}</p>}

                    <div className="int-grid">
                        {allInternships.map((intern, index) => (
                            <article className="int-card" key={intern.id}>
                                <div className={`int-card-top int-tone-${index % 3}`}>
                                    <div className="int-card-head">
                                        <div className="int-card-text">
                                            <span className="int-card-date">{intern.createdAt}</span>
                                            <h4 className="int-card-title">{intern.title}</h4>
                                            <h2 className="int-card-company">{intern.companyName}</h2>
                                        </div>
                                        <div className="int-card-side">
                                            <svg className="int-bookmark" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinejoin="round">
                                                <path d="M6 3h12v18l-6-4-6 4z" />
                                            </svg>
                                            <img
                                                className="int-card-logo"
                                                src={intern.imgUrl
                                                    ? `http://localhost:8080${intern.imgUrl}`
                                                    : "/default-logo.png"}
                                                alt={intern.companyName}
                                            />
                                        </div>
                                    </div>

                                    <div className="int-tags">
                                        {[intern.type, intern.working_schedule, intern.duration, intern.status]
                                            .filter(Boolean)
                                            .map((tag, i) => (
                                                <span className="int-tag" key={i}>{tag}</span>
                                            ))}
                                    </div>
                                </div>

                                <div className="int-card-bottom">
                                    <div>
                                        <h3 className="int-card-salary">${intern.salary}/month</h3>
                                        <h4 className="int-card-location">{intern.location}</h4>
                                    </div>
                                    <button
                                        type="button"
                                        className="int-btn"
                                        onClick={() => navigate("/infointernship")}
                                    >
                                        Details
                                    </button>
                                </div>
                            </article>
                        ))}
                    </div>
                </section>
            </div>
        </div>
    );
}

export default Internshipuri;