import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import axiosInstance from '../services/api';
import './ManageAppointments.css';

interface Appointment {
  id: number;
  datum: string;
  status: string;
  pacientIme?: string;
  pacientPriimek?: string;
}

const ManageAppointments: React.FC = () => {
  const { currentUser } = useAuth();
  const navigate = useNavigate();
  const [appointment, setAppointment] = useState({
    datum: '',
    status: 'Available'
  });
  const [appointments, setAppointments] = useState<Appointment[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (!currentUser) {
      navigate('/login');
    } else {
      fetchBookedAppointments();
    }
  }, [currentUser, navigate]);

  const fetchBookedAppointments = async () => {
    try {
      const response = await axiosInstance.get(`/termin/zdravniki/${currentUser.id}/termin?status=Booked`);
      setAppointments(response.data);
    } catch (error) {
      console.error('Error fetching appointments:', error);
      setError('Failed to fetch appointments. Please try again.');
    }
  };

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
      const response = await axiosInstance.post('/termin', {
        ...appointment,
        zdravnik: { id: currentUser.id }
      });
      console.log('Response:', response);
      alert('Appointment slot saved successfully');
      fetchBookedAppointments(); // Refresh the list after adding
    } catch (error) {
      console.error('There was an error saving the appointment slot!', error);
      alert('Failed to save the appointment slot. Please try again.');
    }
  };

  const handleDelete = async (id: number) => {
    try {
      await axiosInstance.delete(`/termin/${id}/cancel`);
      alert('Appointment cancelled successfully');
      fetchBookedAppointments(); // Refresh the list after deletion
    } catch (error) {
      console.error('There was an error cancelling the appointment!', error);
      alert('Failed to cancel the appointment. Please try again.');
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
      <h3>Existing Appointments</h3>
      {error && <div className="error-message">{error}</div>}
      <div className="appointments-list">
        {appointments.map((appointment) => (
          <div key={appointment.id} className="appointment-card">
            <h4>{new Date(appointment.datum).toLocaleString()}</h4>
            <p>Patient: {appointment.pacientIme} {appointment.pacientPriimek}</p>
            <p>Status: {appointment.status}</p>
            <button onClick={() => handleDelete(appointment.id)}>Cancel Appointment</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ManageAppointments;