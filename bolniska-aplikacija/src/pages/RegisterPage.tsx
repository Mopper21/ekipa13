import React, { useState, FormEvent } from 'react';
import { useAuth } from '../AuthContext';

const RegisterPage: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { register } = useAuth();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    try {
      await register(email, password);
    } catch (error) {
      console.error('Failed to register', error);
    }
  };

  return (
    <div>
      <h1>Registracija</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div>
          <label>Geslo</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <button type="submit">Registracija</button>
      </form>
    </div>
  );
};

export default RegisterPage;
