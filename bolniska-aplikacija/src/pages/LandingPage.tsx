import React from 'react';
import { Link } from 'react-router-dom';
import './LandingPage.css';

const LandingPage: React.FC = () => {
  return (
    <div className="landing-page">
      <header className="header">
        <div className="logo">Health+</div>
        <nav>
          <Link to="/">Home</Link>
          <Link to="/about">About</Link>
          <Link to="/doctors">Doctors</Link>
          <Link to="/login">Login</Link>

        </nav>
      </header>

      <section className="hero-section">
        <div className="hero-text">
          <h1>Find your Doctor and make an Appointment</h1>
          <p>
            Talk to online doctors and get medical advice, online prescriptions,
            refills and medical notes within minutes. On-demand healthcare services at your fingertips.
          </p>
          <button className="appointment-button">Book Appointment</button>
        </div>
        <div className="hero-image">
          <img src="path/to/your/doctor-image.png" alt="Doctor" />
        </div>
      </section>

      <section className="stats-section">
        <div className="stat">
          <h2>145k+</h2>
          <p>Receive Patients</p>
        </div>
        <div className="stat">
          <h2>50+</h2>
          <p>Expert Doctors</p>
        </div>
        <div className="stat">
          <h2>10+</h2>
          <p>Years of Experience</p>
        </div>
      </section>
    </div>
  );
};

export default LandingPage;
