import React, { createContext, useState, useContext, ReactNode } from 'react';
import axiosInstance from './services/api';
import { useNavigate } from 'react-router-dom';

interface AuthContextType {
  currentUser: any;
  register: (userData: any) => Promise<void>;
  login: (userData: any) => Promise<any>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [currentUser, setCurrentUser] = useState<any>(null);
  const navigate = useNavigate();

  const register = async (userData: any) => {
    const response = await axiosInstance.post('/auth/register', userData);
    setCurrentUser(response.data);
  };

  const login = async (userData: any): Promise<any> => {
    try {
      const response = await axiosInstance.post('/auth/login', userData);
      const userDataWithToken = {
        ...response.data,
        token: "some-generated-token" // Zamijenite stvarnom logikom generiranja tokena
      };
      setCurrentUser(userDataWithToken);
      localStorage.setItem('token', userDataWithToken.token);
      return userDataWithToken;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  };

  const logout = () => {
    setCurrentUser(null);
    localStorage.removeItem('token');
    navigate('/login');
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
