// src/pages/Profile.tsx
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';
import './Profile.css';

const Profile: React.FC = () => {
  const { currentUser } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!currentUser) {
      navigate('/login');
    }
  }, [currentUser, navigate]);

  if (!currentUser) {
    return null; 
  }

  return (
    <div className="profile-container">
      <h2>Profile</h2>
      <div className="profile-info">
        <p><strong>Username:</strong> {currentUser.uporabniskoIme}</p>
        <div className="profile-picture">
          <img src="../profile.webp" alt="Profile" />
        </div>
      </div>
    </div>
  );
};

export default Profile;
