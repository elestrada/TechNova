// src/components/Login.jsx
import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import './Login.css';

const Login = () => {
    const { login, error, loading } = useAuth();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [localError, setLocalError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLocalError('');
        
        if (!email || !password) {
            setLocalError('Complete todos los campos');
            return;
        }

        const result = await login(email, password);
        if (result.success) {
            window.location.href = '/';
        } else {
            setLocalError(result.error || 'Error al iniciar sesión');
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h1>Tech<span>Nova</span></h1>
                <p>Inicia sesión para continuar</p>
                
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Correo electrónico</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="tucorreo@ejemplo.com"
                            required
                        />
                    </div>
                    
                    <div className="form-group">
                        <label>Contraseña</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="••••••••"
                            required
                        />
                    </div>
                    
                    {(localError || error) && (
                        <div className="error-message">{localError || error}</div>
                    )}
                    
                    <button type="submit" className="login-btn" disabled={loading}>
                        {loading ? 'Cargando...' : 'Iniciar sesión'}
                    </button>
                </form>
                
                <div className="login-footer">
                    <p>¿No tienes cuenta? <a href="#">Regístrate</a></p>
                </div>
            </div>
        </div>
    );
};

export default Login;