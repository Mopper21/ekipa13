import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // prilagodi URL glede na nastavitev zaledja
});

export const fetchPacienti = () => api.get('/pacienti');
export const fetchZdravniki = () => api.get('/zdravniki');
export const createPacient = (data: any) => api.post('/pacienti', data);
export const updatePacient = (id: number, data: any) => api.put(`/pacienti/${id}`, data);
export const deletePacient = (id: number) => api.delete(`/pacienti/${id}`);

export const registerUser = (data: any) => api.post('/users/register', data);

// Dodaj ostale funkcije za komunikacijo z zaledjem
