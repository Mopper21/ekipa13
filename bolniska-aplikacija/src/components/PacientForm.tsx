import React, { useState, FormEvent } from 'react';
import { createPacient } from '../services/api';
import './PacientForm.css';

interface PacientFormProps {
  onPacientAdded: () => void;
}

const PacientForm: React.FC<PacientFormProps> = ({ onPacientAdded }) => {
  const [ime, setIme] = useState('');
  const [priimek, setPriimek] = useState('');
  const [datumRojstva, setDatumRojstva] = useState('');
  const [naslov, setNaslov] = useState('');
  const [telSt, setTelSt] = useState('');
  const [email, setEmail] = useState('');
  const [zzzs, setZzzs] = useState('');

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    const pacient = { ime, priimek, datumRojstva, naslov, telSt, email, zzzs };
    createPacient(pacient).then(() => {
      onPacientAdded();
      setIme('');
      setPriimek('');
      setDatumRojstva('');
      setNaslov('');
      setTelSt('');
      setEmail('');
      setZzzs('');
    });
  };

  return (
    <form onSubmit={handleSubmit} className="pacient-form">
      <div>
        <label>Ime</label>
        <input type="text" value={ime} onChange={(e) => setIme(e.target.value)} />
      </div>
      <div>
        <label>Priimek</label>
        <input type="text" value={priimek} onChange={(e) => setPriimek(e.target.value)} />
      </div>
      <div>
        <label>Datum rojstva</label>
        <input type="date" value={datumRojstva} onChange={(e) => setDatumRojstva(e.target.value)} />
      </div>
      <div>
        <label>Naslov</label>
        <input type="text" value={naslov} onChange={(e) => setNaslov(e.target.value)} />
      </div>
      <div>
        <label>Telefonska številka</label>
        <input type="text" value={telSt} onChange={(e) => setTelSt(e.target.value)} />
      </div>
      <div>
        <label>Email</label>
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>
      <div>
        <label>Številka ZZZS</label>
        <input type="text" value={zzzs} onChange={(e) => setZzzs(e.target.value)} />
      </div>
      <button type="submit">Dodaj pacienta</button>
    </form>
  );
};

export default PacientForm;
