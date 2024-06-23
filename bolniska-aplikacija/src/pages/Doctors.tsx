// src/pages/Doctors.tsx
import React, { useEffect, useState } from 'react';
import axiosInstance from '../services/api';
import './Doctors.css';

interface Zdravnik {
  id: number;
  ime: string;
  priimek: string;
  specializacija: string;
  telefon: string;
  email: string;
}

const Doctors: React.FC = () => {
  const [zdravniki, setZdravniki] = useState<Zdravnik[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const response = await axiosInstance.get('/zdravniki');
        setZdravniki(response.data);
      } catch (error) {
        console.error('Error fetching doctors:', error);
        setError('Failed to fetch doctors. Please try again.');
      }
    };

    fetchDoctors();
  }, []);

  return (
    <div className="doctors-container">
      <h1>Doctors</h1>
      {error && <div className="error-message">{error}</div>}
      <div className="doctors-list">
        {zdravniki.map((zdravnik) => (
          <div key={zdravnik.id} className="doctor-card">
            <h2>{zdravnik.ime} {zdravnik.priimek}</h2>
            <p>Specialization: {zdravnik.specializacija}</p>
            <p>Phone: {zdravnik.telefon}</p>
            <p>Email: {zdravnik.email}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Doctors;