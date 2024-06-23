// src/pages/Register.tsx
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './Register.css';

const Register: React.FC = () => {
  const [userData, setUserData] = useState({
    uporabniskoIme: '',
    geslo: '',
    vloga: 'USER' 
  });
  const { register } = useAuth();
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setUserData({
      ...userData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await register(userData);
      navigate('/login');
    } catch (error) {
      console.error('Registration error', error);
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit} className="register-form">
        <input type="text" name="uporabniskoIme" placeholder="Uporabnisko Ime" onChange={handleChange} />
        <input type="password" name="geslo" placeholder="Geslo" onChange={handleChange} />
        <select className="vloga" name="vloga" onChange={handleChange} value={userData.vloga}>
          <option value="USER">User</option>
          <option value="DOCTOR">Doctor</option>
        </select>
        <button type="submit">Register</button>
      </form>
      <div className="login-link">
        Already have an account? <Link to="/login">Login here</Link>
      </div>
    </div>
  );
};

export default Register;
