import React, { useState, useEffect } from 'react';
import axiosInstance from '../services/api';

interface Doctor {
    id: number;
    ime: string;
    priimek: string;
    specializacija: string;
    telefon: string;
    email: string;
}

const DoctorList: React.FC = () => {
    const [doctors, setDoctors] = useState<Doctor[]>([]);

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const response = await axiosInstance.get('/zdravniki');
                setDoctors(response.data);
            } catch (error) {
                console.error('There was an error fetching the doctors!', error);
            }
        };

        fetchDoctors();
    }, []);

    return (
        <div className="doctor-list">
            <h1>Our Doctors</h1>
            <ul>
                {doctors.map(doctor => (
                    <li key={doctor.id}>
                        <h2>{doctor.ime} {doctor.priimek}</h2>
                        <p>Specialization: {doctor.specializacija}</p>
                        <p>Phone: {doctor.telefon}</p>
                        <p>Email: {doctor.email}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default DoctorList;
