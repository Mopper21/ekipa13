import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link, useLocation } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import PacientListPage from './pages/PacientListPage';
import TerminListPage from './pages/TerminListPage';
import ProtectedRoute from './ProtectedRoute';
import { AuthProvider, useAuth } from './AuthContext';

const NavBar: React.FC = () => {
  const { currentUser } = useAuth();
  const location = useLocation();

  const shouldHideNavBar = location.pathname === '/login' || location.pathname === '/register';

  if (shouldHideNavBar) return null;

  return (
    <nav>
      <Link to="/">Home</Link>
      <Link to="/pacienti">Pacienti</Link>
      <Link to="/termini">Termini</Link>
      {currentUser ? (
        <>
          <Link to="/profile">Profile</Link>
          <Link to="/logout">Logout</Link>
        </>
      ) : (
        <Link to="/login">Login</Link>
      )}
    </nav>
  );
};

const App: React.FC = () => {
  return (
    <AuthProvider>
      <Router>
        <NavBar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/pacienti" element={<ProtectedRoute component={PacientListPage} />} />
          <Route path="/termini" element={<ProtectedRoute component={TerminListPage} />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
