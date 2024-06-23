// src/pages/LandingPage.tsx
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './LandingPage.css';

const LandingPage: React.FC = () => {
  const { currentUser, logout } = useAuth();
  const navigate = useNavigate();

  const handleBookAppointment = () => {
    if (currentUser) {
      navigate('/book-appointment');
    } else {
      navigate('/login');
    }
  };

  return (
    <div className="landing-page">
      <header>
        <div className="logo">Health+</div>
        <nav>
          <Link to="/">Home</Link>
          <Link to="/services">Services</Link>
          <Link to="/about">About</Link>
          <Link to="/doctors">Doctors</Link>
          {currentUser ? (
            <>
              <Link to="/profile">Profile</Link>
              <button className="logout-button" onClick={logout}>Logout</button>
            </>
          ) : (
            <Link to="/login">Login</Link>
          )}
        </nav>
      </header>
      <main>
        <div className="main-content">
          <div className="text-content">
            <h1>Find your Doctor and make an Appointment</h1>
            <p>
              Talk to online doctors and get medical advice, online prescriptions, refills and medical notes within minutes. On-demand healthcare services at your fingertips.
            </p>
            <button className="book-appointment" onClick={handleBookAppointment}>Book Appointment</button>
          </div>
          <div className="image-content">
            <img src="../slika1.jpg" alt="Healthcare" />
          </div>
        </div>
        <section className="extra-info">
          <div className="info-block">
            <h2>Our Services</h2>
            <p>We offer a wide range of healthcare services to meet your needs.</p>
          </div>
          <div className="info-block">
            <h2>Why Choose Us</h2>
            <p>With over 50 expert doctors and 145k+ patients, we provide the best healthcare services.</p>
          </div>
        </section>
      </main>
    </div>
  );
};

export default LandingPage;
