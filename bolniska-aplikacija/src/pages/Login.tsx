// src/pages/Login.tsx
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axiosInstance from '../services/api';  // Ažurirano importovanje
import './Login.css';

const Login: React.FC = () => {
  const [credentials, setCredentials] = useState({
    uporabniskoIme: '',
    geslo: ''
  });

  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axiosInstance.post('/auth/login', credentials);
      navigate('/'); // Preusmeravanje na glavnu stranicu nakon uspešnog logina
    } catch (error) {
      console.error('Login error', error);
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <input type="text" name="uporabniskoIme" placeholder="Uporabnisko Ime" onChange={handleChange} />
        <input type="password" name="geslo" placeholder="Geslo" onChange={handleChange} />
        <button type="submit">Login</button>
      </form>
      <div className="register-link">
        Don't have an account? <Link to="/register">Register here</Link>
      </div>
    </div>
  );
};

export default Login;
