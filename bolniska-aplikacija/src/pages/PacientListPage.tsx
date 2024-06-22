import React from 'react';
import PacientList from '../components/PacientList';
import PacientForm from '../components/PacientForm';

const PacientListPage: React.FC = () => {
  const handlePacientAdded = () => {
    // Reload the pacient list
  };

  return (
    <div>
      <PacientForm onPacientAdded={handlePacientAdded} />
      <PacientList />
    </div>
  );
};

export default PacientListPage;
