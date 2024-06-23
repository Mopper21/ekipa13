// src/AuthContext.tsx
import React, { createContext, useState, useContext, ReactNode } from 'react';
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
  
    const register = async (userData: any) => {
      const response = await axiosInstance.post('/auth/register', userData);
      setCurrentUser(response.data);
    };
  
    const login = async (userData: any) => {
      const response = await axiosInstance.post('/auth/login', userData);
      setCurrentUser(response.data);
    };
  
    const logout = () => {
      setCurrentUser(null);
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