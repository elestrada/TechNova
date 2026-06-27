// src/services/api.js
const API_URL = 'http://localhost:9090';

// ==========================================
// ===== PRODUCTOS =====
// ==========================================

export const getProducts = async () => {
    try {
        const response = await fetch(`${API_URL}/productos`);
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error('Error al obtener productos:', error);
        return [];
    }
};

// ==========================================
// ===== CARRITO =====
// ==========================================

export const getCarrito = async (usuarioId) => {
    try {
        const response = await fetch(`${API_URL}/carrito/${usuarioId}`);
        if (!response.ok) {
            throw new Error('Error al obtener el carrito');
        }
        return await response.json();
    } catch (error) {
        console.error('Error al obtener el carrito:', error);
        throw error;
    }
};

export const agregarAlCarrito = async (usuarioId, productoId) => {
    try {
        const response = await fetch(`${API_URL}/carrito/agregar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ usuarioId, productoId })
        });
        if (!response.ok) {
            throw new Error('Error al agregar al carrito');
        }
        return await response.json();
    } catch (error) {
        console.error('Error al agregar al carrito:', error);
        throw error;
    }
};

export const eliminarDelCarrito = async (usuarioId, productoId) => {
    try {
        const response = await fetch(`${API_URL}/carrito/eliminar/${usuarioId}/${productoId}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Error al eliminar del carrito');
        }
        return await response.json();
    } catch (error) {
        console.error('Error al eliminar del carrito:', error);
        throw error;
    }
};

export const vaciarCarrito = async (usuarioId) => {
    try {
        const response = await fetch(`${API_URL}/carrito/vaciar/${usuarioId}`, {
            method: 'DELETE'
        });
        if (!response.ok) {
            throw new Error('Error al vaciar el carrito');
        }
        return await response.json();
    } catch (error) {
        console.error('Error al vaciar el carrito:', error);
        throw error;
    }
};

// ==========================================
// ===== AUTENTICACIÓN =====
// ==========================================

export const loginUser = async (email, password) => {
    try {
        const response = await fetch(`${API_URL}/api/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });
        
        const data = await response.json();
        
        if (!response.ok) {
            throw new Error(data.error || 'Error al iniciar sesión');
        }
        return data;
    } catch (error) {
        console.error('Error en login:', error);
        throw error;
    }
};