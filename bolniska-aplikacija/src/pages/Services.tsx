import React from 'react';
import { Link } from 'react-router-dom';
import './Services.css';

const Services: React.FC = () => {
  return (
    <div className="services-page">
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
        <div className="services-container">
          <h1>What We Do</h1>
          <p>
            We bring healthcare to your convenience, offering a comprehensive range of on-demand medical services tailored to your needs. Our platform allows you to connect with experienced online doctors who provide expert medical advice, issue online prescriptions, and offer quick refills whenever you require them.
          </p>
          <div className="services-list">
            <div className="service-item">
              <div className="service-icon">
                <img src="../icon.jpg" alt="Icon 1" />
              </div>
              <h2>General Consultations</h2>
              <p>
                Book appointments for general consultations with ease. Our doctors are available to discuss any health concerns you may have and provide professional medical advice.
              </p>
            </div>
            <div className="service-item">
              <div className="service-icon">
                <img src="../icon.jpg" alt="Icon 2" />
              </div>
              <h2>Specialist Appointments</h2>
              <p>
                Need to see a specialist? Our platform allows you to book appointments with specialists in various fields, ensuring you get the expert care you need.
              </p>
            </div>
            <div className="service-item">
              <div className="service-icon">
                <img src="../icon.jpg" alt="Icon 3" />
              </div>
              <h2>Follow-Up Visits</h2>
              <p>
                Schedule follow-up visits with your doctors easily. Keep track of your health progress and get continuous care through our seamless booking system.
              </p>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Services;