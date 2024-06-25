import React, { useState, useEffect } from 'react';
import axiosInstance from '../services/api';
import './BookAppointment.css';

interface Doctor {
  id: number;
  ime: string;
  priimek: string;
  specializacija: string;
}

interface Appointment {
  id: number;
  datum: string;
}

const BookAppointment: React.FC = () => {
  const [pacient, setPacient] = useState({
    ime: '',
    priimek: '',
    datumRojstva: '',
    naslov: '',
    telSt: '',
    email: '',
    zzzs: ''
  });
  const [doctors, setDoctors] = useState<Doctor[]>([]);
  const [selectedDoctorId, setSelectedDoctorId] = useState<number | null>(null);
  const [availableAppointments, setAvailableAppointments] = useState<Appointment[]>([]);
  const [selectedAppointmentId, setSelectedAppointmentId] = useState<number | null>(null);

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const response = await axiosInstance.get('/zdravniki');
        setDoctors(response.data);
      } catch (error) {
        console.error('Error fetching doctors:', error);
      }
    };

    fetchDoctors();
  }, []);

  useEffect(() => {
    const fetchAppointments = async () => {
      if (selectedDoctorId !== null) {
        try {
          console.log(`Fetching appointments for doctor ID: ${selectedDoctorId}`);
          const response = await axiosInstance.get(`/termin/zdravniki/${selectedDoctorId}/termin`);
          console.log('Appointments response:', response.data);
          setAvailableAppointments(response.data);
        } catch (error) {
          console.error('Error fetching appointments:', error);
        }
      }
    };

    fetchAppointments();
  }, [selectedDoctorId]);

  const handlePacientChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPacient({
      ...pacient,
      [e.target.name]: e.target.value
    });
  };

  const handleDoctorChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedDoctorId(Number(e.target.value));
  };

  const handleAppointmentChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedAppointmentId(Number(e.target.value));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
        // Create the patient
        const pacientResponse = await axiosInstance.post('/pacienti', pacient);
        const pacientId = pacientResponse.data.id;
  
        // Update the appointment
        if (selectedAppointmentId !== null) {
          await axiosInstance.put(`/termin/${selectedAppointmentId}`, { status: 'Booked', pacient: { id: pacientId } });
          alert('Appointment booked successfully');
        } else {
          alert('Please select an appointment slot');
        }
      } catch (error) {
        console.error('Error booking appointment:', error);
        alert('Failed to book the appointment. Please try again.');
      }

  };

  return (
    <div className="book-appointment-container">
      <h2>Book an Appointment</h2>
      <form onSubmit={handleSubmit} className="book-appointment-form">
        <input
          type="text"
          name="ime"
          placeholder="First Name"
          onChange={handlePacientChange}
          required
        />
        <input
          type="text"
          name="priimek"
          placeholder="Last Name"
          onChange={handlePacientChange}
          required
        />
        <input
          type="date"
          name="datumRojstva"
          placeholder="Date of Birth"
          onChange={handlePacientChange}
          required
        />
        <input
          type="text"
          name="naslov"
          placeholder="Address"
          onChange={handlePacientChange}
          required
        />
        <input
          type="tel"
          name="telSt"
          placeholder="Phone Number"
          onChange={handlePacientChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          onChange={handlePacientChange}
          required
        />
        <input
          type="text"
          name="zzzs"
          placeholder="ZZZS Number"
          onChange={handlePacientChange}
          required
        />
        <select onChange={handleDoctorChange} required>
          <option value="">Select a Doctor</option>
          {doctors.map(doctor => (
            <option key={doctor.id} value={doctor.id}>
              Dr. {doctor.ime} {doctor.priimek} - {doctor.specializacija}
            </option>
          ))}
        </select>
        <select onChange={handleAppointmentChange} required>
          <option value="">Select an Appointment Slot</option>
          {availableAppointments.map(appointment => (
            <option key={appointment.id} value={appointment.id}>
              {new Date(appointment.datum).toLocaleString()}
            </option>
          ))}
        </select>
        <button type="submit">Book Appointment</button>
      </form>
    </div>
  );
};

export default BookAppointment;
