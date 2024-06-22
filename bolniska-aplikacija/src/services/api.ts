import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const fetchZdravniki = () => api.get('/zdravniki');
export const fetchTermini = () => api.get('/termini');
export const createTermin = (data: any) => api.post('/termini', data);
export const updateTermin = (id: number, data: any) => api.put(`/termini/${id}`, data);
export const deleteTermin = (id: number) => api.delete(`/termini/${id}`);

export const registerUser = (data: any) => api.post('/auth/register', data);
export const loginUser = (data: any) => api.post('/auth/login', data);
