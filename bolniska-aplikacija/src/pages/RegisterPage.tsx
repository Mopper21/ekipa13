import React, { useState, FormEvent } from 'react';
import { registerUser } from '../services/api';

const RegisterPage: React.FC = () => {
  const [uporabniskoIme, setUporabniskoIme] = useState('');
  const [geslo, setGeslo] = useState('');
  const [vloga, setVloga] = useState('ROLE_USER');

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    const user = { uporabniskoIme, geslo, vloga };
    try {
      const response = await registerUser(user);
      console.log('Registracija uspešna:', response.data);
    } catch (error) {
      console.error('Napaka pri registraciji:', error);
    }
  };

  return (
    <div>
      <h1>Registracija</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Uporabniško ime</label>
          <input type="text" value={uporabniskoIme} onChange={(e) => setUporabniskoIme(e.target.value)} />
        </div>
        <div>
          <label>Geslo</label>
          <input type="password" value={geslo} onChange={(e) => setGeslo(e.target.value)} />
        </div>
        <button type="submit">Registracija</button>
      </form>
    </div>
  );
};

export default RegisterPage;
