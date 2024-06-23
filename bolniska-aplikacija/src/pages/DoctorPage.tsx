// src/pages/DoctorPage.tsx
import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './DoctorPage.css';

const DoctorPage: React.FC = () => {
  const { currentUser, logout } = useAuth();

  return (
    <div className="doctor-page">
      <header>
        <div className="logo">Health+</div>
        <nav>
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
            <h1>Welcome, Dr. {currentUser.uporabniskoIme}</h1>
            <p>Manage your appointments.</p>
            <button className="manage-appointments">
              <Link to="/manage-appointments">Manage Appointments</Link>
            </button>          </div>
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

export default DoctorPage;
