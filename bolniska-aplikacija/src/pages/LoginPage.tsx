import React, { useState, FormEvent } from 'react';
import { loginUser } from '../services/api';
import { useAuth } from '../AuthContext';

const LoginPage: React.FC = () => {
  const [uporabniskoIme, setUporabniskoIme] = useState('');
  const [geslo, setGeslo] = useState('');
  const { setCurrentUser } = useAuth();

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    const user = { uporabniskoIme, geslo };
    try {
      const response = await loginUser(user);
      setCurrentUser(response.data);
      // Redirect to home or other page
    } catch (error) {
      console.error('Napaka pri prijavi:', error);
    }
  };

  return (
    <div>
      <h1>Prijava</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Uporabniško ime</label>
          <input type="text" value={uporabniskoIme} onChange={(e) => setUporabniskoIme(e.target.value)} />
        </div>
        <div>
          <label>Geslo</label>
          <input type="password" value={geslo} onChange={(e) => setGeslo(e.target.value)} />
        </div>
        <button type="submit">Prijava</button>
      </form>
    </div>
  );
};

export default LoginPage;
