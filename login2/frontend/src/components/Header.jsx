// src/components/Header.jsx
import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import CartSummary from './CartSummary';
import './Header.css';

const Header = ({ usuario = 'Invitado', onSearch }) => {
    const { logout } = useAuth();
    const [searchTerm, setSearchTerm] = useState('');

    const handleSearch = (e) => {
        e.preventDefault();
        if (onSearch) onSearch(searchTerm);
    };

    const handleLogout = () => {
        logout();
        window.location.href = '/'; // Redirige al login después de cerrar sesión
    };

    return (
        <header className="header">
            <div className="header-inner">
                {/* Fila superior */}
                <div className="header-top">
                    <div className="logo">
                        <a href="/" className="logo-link">Tech<span>Nova</span></a>
                    </div>

                    <form className="search-form" onSubmit={handleSearch}>
                        <input
                            type="text"
                            className="search-input"
                            placeholder="Buscar productos..."
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                        <button type="submit" className="search-btn">
                            <svg className="search-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                <circle cx="11" cy="11" r="8" />
                                <line x1="21" y1="21" x2="16.65" y2="16.65" />
                            </svg>
                            Buscar
                        </button>
                    </form>

                    <div className="header-actions">
                        <span className="user-greeting">Hola, {usuario}</span>
                        <CartSummary />
                        <button className="logout-btn" onClick={handleLogout}>
                            Cerrar Sesión
                        </button>
                    </div>
                </div>
            </div>

            {/* Menú de navegación */}
            <nav className="nav-menu">
                <div className="nav-menu-inner">
                    <ul className="nav-list">
                        <li><a href="#" className="nav-link active">Inicio</a></li>
                        <li><a href="#" className="nav-link">Productos</a></li>
                        <li><a href="#" className="nav-link">Ofertas</a></li>
                        <li><a href="#" className="nav-link">Más vendidos</a></li>
                        <li><a href="#" className="nav-link">Soporte</a></li>
                    </ul>
                </div>
            </nav>
        </header>
    );
};

export default Header;