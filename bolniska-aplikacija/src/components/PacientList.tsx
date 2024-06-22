import React, { useEffect, useState } from 'react';
import { fetchPacienti } from '../services/api';

interface Pacient {
  id: number;
  ime: string;
  priimek: string;
}

const PacientList: React.FC = () => {
  const [pacienti, setPacienti] = useState<Pacient[]>([]);

  useEffect(() => {
    loadPacienti();
  }, []);

  const loadPacienti = () => {
    fetchPacienti()
      .then(response => setPacienti(response.data))
      .catch(error => console.error('Napaka pri pridobivanju pacientov:', error));
  };

  return (
    <div>
      <h1>Seznam pacientov</h1>
      <ul className="pacient-list">
        {pacienti.map(pacient => (
          <li key={pacient.id}>{pacient.ime} {pacient.priimek}</li>
        ))}
      </ul>
    </div>
  );
};

export default PacientList;
