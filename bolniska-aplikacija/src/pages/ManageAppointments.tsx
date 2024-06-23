import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import axiosInstance from '../services/api';
import './ManageAppointments.css';

const ManageAppointments: React.FC = () => {
  const { currentUser } = useAuth();
  const navigate = useNavigate();
  const [appointment, setAppointment] = useState({
    datum: '',
    status: 'Available'
  });

  useEffect(() => {
    if (!currentUser) {
      navigate('/login');
    }
  }, [currentUser, navigate]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setAppointment({
      ...appointment,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!currentUser || !currentUser.id) {
      alert('User not logged in');
      return;
    }

    try {
      await axiosInstance.post('/termin', {
        ...appointment,
        zdravnik: { id: currentUser.id } // Ensure this matches your backend field
      });
      alert('Appointment slot saved successfully');
    } catch (error) {
      console.error('There was an error saving the appointment slot!', error);
      alert('Failed to save the appointment slot. Please try again.');
    }
  };

  return (
    <div className="manage-appointments-container">
      <h2>Manage Appointments</h2>
      <form onSubmit={handleSubmit} className="manage-appointments-form">
        <input
          type="datetime-local"
          name="datum"
          onChange={handleChange}
          required
        />
        <button type="submit">Save Appointment Slot</button>
      </form>
    </div>
  );
};

export default ManageAppointments;
