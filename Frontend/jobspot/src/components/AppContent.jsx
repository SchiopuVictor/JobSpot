import { useLocation, Routes, Route } from 'react-router-dom';
import Internshipuri from '../pages/Internshipuri';
import Notificari from '../pages/Notificari';
import Cv from '../pages/Cv';
import Home from '../pages/Home';
import HeaderComponent from '../components/HeaderComponent';
import FooterComponent from '../components/FooterComponet';
import AuthPage from '../pages/AuthPage';
import CreateInternship from '../pages/CreateInternship';
import InternshipDetails from '../pages/InternshipDetails.jsx'

function AppContent() {
    const location = useLocation();

    const isAuthPage =
        location.pathname === "/login" ||
        location.pathname === "/register";
        const isHome = location.pathname === "/";

    return (
        <div className={`d-flex flex-column min-vh-100 position-relative ${isHome ? "is-home" : ""}`}>
            <HeaderComponent />

            <div className="flex-grow-1">
                {isAuthPage ? 
                (
                    <AuthPage />
                ) : (
                    <Routes>
                        <Route path='/infointernship/:id' element={<InternshipDetails />}/>
                        <Route path="/" element={<Home />} />
                        <Route path='/addintership' element = {<CreateInternship />} />
                        <Route path="/internship" element={<Internshipuri />} />
                        <Route path="/notificari" element={<Notificari />} />
                        <Route path="/cv" element={<Cv />} />
                    </Routes>
                )}
            </div>
            <FooterComponent />
        </div>
    );
}

export default AppContent;