// src/services/api.ts
import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api', // Podesite baseURL prema potrebi
});

export default axiosInstance;