import Home from './pages/Home';
import Internshipuri from './pages/Internshipuri';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponet';
import Notificari from './pages/Notificari';
import Cv from './pages/Cv';
import AuthPage from "./pages/AuthPage"; 
import { AuthProvider } from './context/AuthContext';

function App() {
  return (
    <AuthProvider>
    <Router>
      <div className="d-flex flex-column min-vh-100">
        <HeaderComponent />
        <div className="flex-grow-1">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/internship" element={<Internshipuri />} />
            <Route path="/login" element={<AuthPage key="login" initialMode="login" />} />
            <Route path="/register" element={<AuthPage key="register" initialMode="register" />} />
            <Route path="/notificari" element={<Notificari />} />
            <Route path="/cv" element={<Cv />} />
          </Routes>
        </div>

        <FooterComponent />
      </div>
    </Router>
    </AuthProvider>
  );
}

export default App;