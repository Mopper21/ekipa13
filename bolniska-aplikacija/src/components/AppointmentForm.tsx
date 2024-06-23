import React, { useState } from 'react';
import axiosInstance from '../services/api';

interface Appointment {
    pacientId: number;
    zdravnikId: number;
    datum: string;
    status: string;
}

const AppointmentForm: React.FC = () => {
    const [appointment, setAppointment] = useState<Appointment>({
        pacientId: 0,
        zdravnikId: 0,
        datum: '',
        status: 'pending'
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        setAppointment(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await axiosInstance.post('/termini', appointment);
            alert('Appointment scheduled successfully!');
        } catch (error) {
            console.error('There was an error scheduling the appointment!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Schedule an Appointment</h2>
            <div>
                <label>Pacient ID:</label>
                <input
                    type="number"
                    name="pacientId"
                    value={appointment.pacientId}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label>Doctor ID:</label>
                <input
                    type="number"
                    name="zdravnikId"
                    value={appointment.zdravnikId}
                    onChange={handleChange}
                />
            </div>
            <div>
                <label>Date:</label>
                <input
                    type="date"
                    name="datum"
                    value={appointment.datum}
                    onChange={handleChange}
                />
            </div>
            <button type="submit">Schedule</button>
        </form>
    );
};

export default AppointmentForm;
