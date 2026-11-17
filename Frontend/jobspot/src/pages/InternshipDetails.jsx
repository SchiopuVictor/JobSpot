import { Link } from 'react-router-dom';
import '../styles/InternshipDetails.css'

function InternshipDetails(){
    return (
        <div className='pag'>
            <div className='info-pag'>
                <div className='page-title'>
                    <h2>Frontend Engineer</h2>
                </div>
                <div className='job-type'>
                    <div className='job-container'>
                        <div className='job-image'>
                            <img src="src/assets/Endava.png" alt="company" />
                        </div>
                        <div className='job-subtitle'>
                            <h3>Vertex Labs</h3>
                            <h4>Remote ⋅ Full-time ⋅ Mid-level</h4>
                        </div>
                    </div>
                    <div className='jobs-buttons'>
                        <Link className='apply-btn'>
                            Apply for this internship
                        </Link>

                        <Link className='save-btn'>
                            Save internship
                        </Link>
                    </div>
                </div>
            </div>
            <div className='more-info'>
                <div className='left-side'>
                    <h2>About the role</h2>
                    <h3>
                        Frontend development work in a non-professional employer.
                        I Freelance web designers, UI/UX designers, frontend developers, 
                        recruitment businesses, frontend fronttimg developens, 
                        starti or asfoundity, and-huntries, and agencies.
                    </h3>
                    <hr />
                    <h2>
                        What you'll do
                    </h2>

                    <h3>
                        Build accessible interfaces for newslonter consensus, sense, and creativity.
                        Collaborate with designers for annpiating friends and designers environmuses.
                        Improve frontend performance to improve and performance ability.
                    </h3>
                    <hr />

                    <h2>
                        What we're looking for
                    </h2>
                    <h3>
                        JavaScript experience for conjectured JavaScript.
                        Responsive design knowledge for responsive design development.
                        Familiarity with modern frontend frameworks to prease and mocir development.
                    </h3>
                </div>
                <aside className='right-aside'>
                    <div className='salary-range'>
                        <h3>$85,000 - $110,000 /year</h3>
                        <label>Estimated range salary</label>
                    </div>

                    <div className='benefits-part'>
                        <h3>Benefits</h3>
                        <h4>Flexible hours</h4>
                        <h4>Learning buget</h4>
                        <h4>Learning buget</h4>
                        <h4>Remote-frendly</h4>
                    </div>

                    <div className='company-info'>
                        <img src="src/assets/Endava.png" alt="company-name" />
                        <h3>Vertex Labs</h3>                        
                        <p>The company is a fictional company description and fictional company.
                            Industry...
                        </p>
                        <label>Industry</label>
                        <a href="#" className="company-profile">
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