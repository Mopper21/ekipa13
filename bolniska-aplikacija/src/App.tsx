// src/App.tsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import Register from './pages/Register';
import Login from './pages/Login';
import Profile from './pages/Profile';
import DoctorPage from './pages/DoctorPage';
import { AuthProvider } from './AuthContext';
import Footer from './components/Footer';
import ManageAppointments from './pages/ManageAppointments';
import Doctors from './pages/Doctors';
import BookAppointment from './pages/BookAppointment';
import About from './pages/About';
import './App.css';

const App: React.FC = () => {
  return (
    <Router>
      <AuthProvider>
        <div className="app-container">
          <div className="content">
            <Routes>
              <Route path="/" element={<LandingPage />} />
              <Route path="/register" element={<Register />} />
              <Route path="/login" element={<Login />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/doctor" element={<DoctorPage />} />
              <Route path="/manage-appointments" element={<ManageAppointments />} />
              <Route path="/doctors" element={<Doctors />} />
              <Route path="/book-appointment" element={<BookAppointment />} />
              <Route path="/about" element={<About />} />

            </Routes>
          </div>
          <Footer />
        </div>
      </AuthProvider>
    </Router>
  );
};

export default App;