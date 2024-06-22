import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header: React.FC = () => {
  return (
    <header className="header">
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/login">Login</Link></li>
          <li><Link to="/register">Register</Link></li>
          <li><Link to="/pacienti">Pacienti</Link></li>
          <li><Link to="/termini">Termini</Link></li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
