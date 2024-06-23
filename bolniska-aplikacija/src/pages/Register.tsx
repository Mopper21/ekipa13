import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './Register.css';

const Register: React.FC = () => {
  const [formData, setFormData] = useState({
    uporabniskoIme: '',
    geslo: ''
  });
  const { register } = useAuth();
  const [error, setError] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await register(formData);
    } catch (error) {
      setError('Registration failed');
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit} className="register-form">
        <input type="text" name="uporabniskoIme" placeholder="Uporabnisko Ime" onChange={handleChange} />
        <input type="password" name="geslo" placeholder="Geslo" onChange={handleChange} />
        <button type="submit">Register</button>
      </form>
      <div className="login-link">
        Already have an account? <Link to="/login">Login here</Link>
      </div>
    </div>
  );
};

export default Register;
