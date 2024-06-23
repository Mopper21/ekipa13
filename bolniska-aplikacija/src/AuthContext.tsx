// src/AuthContext.tsx
import React, { createContext, useState, useContext, ReactNode } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from './services/api';

interface AuthContextType {
  currentUser: any;
  register: (userData: any) => Promise<void>;
  login: (userData: any) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [currentUser, setCurrentUser] = useState<any>(null);
  const navigate = useNavigate();

  const register = async (userData: any) => {
    try {
      const response = await axiosInstance.post('/auth/register', userData);
      setCurrentUser(response.data);
      navigate('/login'); // Preusmeravanje na login stranicu nakon uspešne registracije
    } catch (error) {
      console.error('Registration error', error);
      throw error;
    }
  };

  const login = async (userData: any) => {
    try {
      const response = await axiosInstance.post('/auth/login', userData);
      setCurrentUser(response.data);
      navigate('/'); // Preusmeravanje na glavnu stranicu nakon uspešnog logina
    } catch (error) {
      console.error('Login error', error);
      throw error;
    }
  };

  const logout = () => {
    setCurrentUser(null);
    navigate('/login'); // Preusmeravanje na login stranicu nakon logouta
  };

  return (
    <AuthContext.Provider value={{ currentUser, register, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
