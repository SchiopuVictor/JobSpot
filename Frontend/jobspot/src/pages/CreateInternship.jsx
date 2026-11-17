import { useState } from "react";
import '../styles/CreateInternship.css';
import { postInternship } from '../config/internshipConfig.jsx';
import { Link, useNavigate } from "react-router-dom";

function CreateInternship() {
    const [error, setError] = useState("");
    const [submitting, setSubmitting] = useState(false);
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: "",
        description: "",
        requirements: "",
        responsibilities: "",
        location: "",
        type: "",
        duration: "",
        working_schedule: "",
        salary: "",
        applicationDeadline: "",
        status: "",
        imgUrl: ""
    });

    // un singur handler pentru toate campurile (citeste name-ul din input)
    function handleChange(e) {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    }

    function handleFile(e) {
        const file = e.target.files[0];
        setFormData((prev) => ({ ...prev, imgUrl: file || "" }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError("");
        setSubmitting(true);
        try {
            await postInternship(formData);
            navigate('/internship');
        } catch (err) {
            setError(
                err.response?.data?.message ||
                "A apărut o problemă la conectarea cu backend-ul"
            );
        } finally {
            setSubmitting(false);
        }
    }

    return (
        <div className="ci-page">
            <div className="ci-title-block">
                <h2 className="ci-title">Add Your <span>Internship</span></h2>
                <p className="ci-subtitle">
                    Share your internship and connect with future professionals
                </p>
            </div>

            <div className="ci-layout">

                {/* ---------- Card decorativ ---------- */}
                <aside className="ci-aside">
                    <h2>
                        Let's have the future <span>TODAY</span>
                    </h2>

                    <div className="ci-ticket">
                        <h3 className="ci-ticket-top">Your Future</h3>
                        <h3 className="ci-ticket-mid">JobSpot</h3>
                        <h3 className="ci-ticket-bottom">//FUTURE</h3>
                    </div>

                    <h3 className="ci-aside-label">Internship</h3>
                </aside>

                {/* ---------- Formular ---------- */}
                <div className="ci-form-card">
                    <form className="ci-form" onSubmit={handleSubmit}>

                        <div className="ci-field">
                            <label htmlFor="title">Title</label>
                            <input
                                id="title"
                                name="title"
                                type="text"
                                placeholder="e.g. Frontend Developer Intern"
                                value={formData.title}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="location">Location</label>
                            <input
                                id="location"
                                name="location"
                                type="text"
                                placeholder="e.g. Chișinău"
                                value={formData.location}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="type">Internship type</label>
                            <select id="type" name="type" value={formData.type} onChange={handleChange}>
                                <option value="">Select type</option>
                                <option value="Remote">Remote</option>
                                <option value="Hybrid">Hybrid</option>
                                <option value="On_Site">On-site</option>
                            </select>
                        </div>

                        <div className="ci-field">
                            <label htmlFor="status">Internship status</label>
                            <select id="status" name="status" value={formData.status} onChange={handleChange}>
                                <option value="">Select status</option>
                                <option value="Active">Active</option>
                                <option value="Closed">Closed</option>
                                <option value="Expired">Expired</option>
                            </select>
                        </div>

                        <div className="ci-field">
                            <label htmlFor="duration">Duration</label>
                            <input
                                id="duration"
                                name="duration"
                                type="text"
                                placeholder="e.g. 3 months"
                                value={formData.duration}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="working_schedule">Working schedule</label>
                            <input
                                id="working_schedule"
                                name="working_schedule"
                                type="text"
                                placeholder="e.g. Part time"
                                value={formData.working_schedule}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="salary">Salary ($ / month)</label>
                            <input
                                id="salary"
                                name="salary"
                                type="number"
                                min="0"
                                placeholder="e.g. 500"
                                value={formData.salary}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="applicationDeadline">Application deadline</label>
                            <input
                                id="applicationDeadline"
                                name="applicationDeadline"
                                type="date"
                                value={formData.applicationDeadline}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field ci-full">
                            <label htmlFor="description">Description</label>
                            <textarea
                                id="description"
                                name="description"
                                rows="3"
                                placeholder="Tell students what this internship is about"
                                value={formData.description}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="requirements">Requirements</label>
                            <textarea
                                id="requirements"
                                name="requirements"
                                rows="4"
                                placeholder="Write each requirement on a new line"
                                value={formData.requirements}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field">
                            <label htmlFor="responsibilities">Responsibilities</label>
                            <textarea
                                id="responsibilities"
                                name="responsibilities"
                                rows="4"
                                placeholder="Write each responsibility on a new line"
                                value={formData.responsibilities}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="ci-field ci-full">
                            <span className="ci-label">Company image</span>
                            <label htmlFor="imgUrl" className="ci-upload">
                                <span className="ci-upload-btn">Choose file</span>
                                <span className="ci-upload-name">
                                    {formData.imgUrl?.name || "No file selected"}
                                </span>
                            </label>
                            <input
                                id="imgUrl"
                                type="file"
                                accept="image/*"
                                className="ci-upload-input"
                                onChange={handleFile}
                            />
                        </div>

                        {error && <p className="ci-error ci-full" role="alert">{error}</p>}

                        <div className="ci-actions ci-full">
                            <Link to="/internship" className="ci-cancel">Cancel</Link>
                            <button className="ci-submit" type="submit" disabled={submitting}>
                                {submitting ? "Submitting..." : "Submit"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default CreateInternship;