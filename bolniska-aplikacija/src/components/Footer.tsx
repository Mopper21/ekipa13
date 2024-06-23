// src/components/Footer.tsx
import React from 'react';
import './Footer.css';

const Footer: React.FC = () => {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-logo">Health+</div>
        <div className="footer-links">
          <a href="/about">About us</a>
        </div>
        
      </div>
      <p>© 2024 Bolniska aplikacija</p>
    </footer>
  );
};

export default Footer;
