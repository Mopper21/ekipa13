import React from 'react';
import { Link } from 'react-router-dom';
import './About.css';

const About: React.FC = () => {
  return (
    <div className="about-page">
      <header>
        <div className="logo">Health+</div>
        <nav>
          <Link to="/">Home</Link>
          <Link to="/services">Services</Link>
          <Link to="/about">About</Link>
          <Link to="/doctors">Doctors</Link>
          <Link to="/profile">Profile</Link>
        </nav>
      </header>
      <main>
        <div className="about-container">
          <div className="about-image">
            <img src="../about.jpg" alt="Doctors" />
          </div>
          <div className="about-content">
            <h1>About Us</h1>
            <p>
              Welcome to Health Plus, your trusted partner for accessible and personalized healthcare. Our expert doctors offer online consultations and specialized services, prioritizing your well-being. Join us on this journey towards a healthier you.
            </p>
            <h2>Your Solutions</h2>
            <ul>
              <li>
                <h3>Choose a Specialist</h3>
                <p>
                  Find your perfect specialist and book with ease at Health Plus. Expert doctors prioritize your health, offering tailored care.
                </p>
              </li>
              <li>
                <h3>Make a Schedule</h3>
                <p>
                  Choose the date and time that suits you best, and let our dedicated team of medical professionals ensure your well-being with personalized care.
                </p>
              </li>
              <li>
                <h3>Get Your Solutions</h3>
                <p>
                  Our experienced doctors and specialists are here to provide expert advice and personalized treatment plans, helping you achieve your best possible health.
                </p>
              </li>
            </ul>
          </div>
        </div>
      </main>
    </div>
  );
};

export default About;