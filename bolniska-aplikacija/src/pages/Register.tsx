// src/pages/Register.tsx
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../services/api';  // Importovanje axios instance
import './Register.css';

const Register: React.FC = () => {
  const [formData, setFormData] = useState({
    uporabniskoIme: '',
    geslo: '',
    vloga: 'USER'
  });

  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await axiosInstance.post('/auth/register', formData);
      navigate('/login'); // Preusmeravanje na login stranicu nakon uspešne registracije
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
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
