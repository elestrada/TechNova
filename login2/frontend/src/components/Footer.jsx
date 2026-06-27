// src/components/Footer.jsx
import React from 'react';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-inner">
                {/* Columna 1: Logo y descripción */}
                <div className="footer-section">
                    <h2 className="footer-logo">Tech<span>Nova</span></h2>
                    <p className="footer-description">
                        Tecnología que transforma. Encuentra los mejores productos tecnológicos al mejor precio.
                    </p>
                    <div className="footer-social">
                        {/* Facebook */}
                        <a href="#" className="social-link" aria-label="Facebook">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                                <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z" />
                            </svg>
                        </a>
                        {/* Instagram */}
                        <a href="#" className="social-link" aria-label="Instagram">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                                <rect x="2" y="2" width="20" height="20" rx="5" ry="5" />
                                <circle cx="12" cy="12" r="5" />
                                <circle cx="17.5" cy="6.5" r="1.5" />
                            </svg>
                        </a>
                        {/* X (antes Twitter) */}
                        <a href="#" className="social-link" aria-label="X">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                                <path d="M4 4l11.733 16h4.267l-11.733 -16z" />
                                <path d="M4 20l6.768 -6.768" />
                                <path d="M19.5 4l-6.768 6.768" />
                            </svg>
                        </a>
                        {/* YouTube */}
                        <a href="#" className="social-link" aria-label="YouTube">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                                <path d="M22.54 6.42a2.78 2.78 0 0 0-1.94-2C18.88 4 12 4 12 4s-6.88 0-8.6.46a2.78 2.78 0 0 0-1.94 2A29 29 0 0 0 1 11.75a29 29 0 0 0 .46 5.33A2.78 2.78 0 0 0 3.4 19.1c1.72.46 8.6.46 8.6.46s6.88 0 8.6-.46a2.78 2.78 0 0 0 1.94-2 29 29 0 0 0 .46-5.25 29 29 0 0 0-.46-5.33z" />
                                <polygon points="9.75 15.02 15.5 11.75 9.75 8.48 9.75 15.02" />
                            </svg>
                        </a>
                    </div>
                </div>

                {/* Columna 2: Enlaces rápidos */}
                <div className="footer-section">
                    <h4 className="footer-title">Enlaces rápidos</h4>
                    <ul className="footer-links">
                        <li><a href="#">Inicio</a></li>
                        <li><a href="#">Productos</a></li>
                        <li><a href="#">Ofertas</a></li>
                        <li><a href="#">Más vendidos</a></li>
                    </ul>
                </div>

                {/* Columna 3: Soporte */}
                <div className="footer-section">
                    <h4 className="footer-title">Soporte</h4>
                    <ul className="footer-links">
                        <li><a href="#">Contacto</a></li>
                        <li><a href="#">Preguntas frecuentes</a></li>
                        <li><a href="#">Devoluciones</a></li>
                        <li><a href="#">Términos y condiciones</a></li>
                    </ul>
                </div>

                {/* Columna 4: Contacto */}
                <div className="footer-section">
                    <h4 className="footer-title">Contacto</h4>
                    <ul className="footer-links footer-contact">
                        <li><a href="mailto:info@technova.com">info@technova.com</a></li>
                        <li><a href="tel:+573001234567">+57 300 123 4567</a></li>
                        <li><span>Bogotá, Colombia</span></li>
                    </ul>
                </div>
            </div>

            {/* Barra inferior de copyright */}
            <div className="footer-bottom">
                <p>&copy; {new Date().getFullYear()} TechNova. Todos los derechos reservados.</p>
            </div>
        </footer>
    );
};

export default Footer;