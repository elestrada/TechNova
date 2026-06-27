// src/context/CartContext.jsx
import React, { createContext, useState, useContext, useEffect } from 'react';
import { getCarrito, agregarAlCarrito, eliminarDelCarrito } from '../services/api';

const CartContext = createContext();

export const CartProvider = ({ children, productos }) => {
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const usuarioId = 1; 

    useEffect(() => {
        const fetchCarrito = async () => {
            try {
                const data = await getCarrito(usuarioId);
                setCartItems(data);
            } catch (error) {
                console.error('Error al cargar el carrito:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchCarrito();
    }, [usuarioId]);

    const addToCart = async (productoId) => {
        try {
            await agregarAlCarrito(usuarioId, productoId);
            const updatedCart = await getCarrito(usuarioId);
            setCartItems(updatedCart);
        } catch (error) {
            console.error('Error al agregar al carrito:', error);
        }
    };

    const removeFromCart = async (productoId) => {
        try {
            await eliminarDelCarrito(usuarioId, productoId);
            const updatedCart = await getCarrito(usuarioId);
            setCartItems(updatedCart);
        } catch (error) {
            console.error('Error al eliminar del carrito:', error);
        }
    };

    // ===== AGREGAR RESTAR CANTIDAD =====
    const updateQuantity = async (productoId, nuevaCantidad) => {
        if (nuevaCantidad < 0) return;

        try {
            const currentItem = cartItems.find(item => item.productoId === productoId);
            
            if (!currentItem && nuevaCantidad === 0) return;

            if (!currentItem && nuevaCantidad > 0) {
                for (let i = 0; i < nuevaCantidad; i++) {
                    await agregarAlCarrito(usuarioId, productoId);
                }
                const updatedCart = await getCarrito(usuarioId);
                setCartItems(updatedCart);
                return;
            }

            const diferencia = nuevaCantidad - currentItem.cantidad;

            if (diferencia > 0) {
                for (let i = 0; i < diferencia; i++) {
                    await agregarAlCarrito(usuarioId, productoId);
                }
            } else if (diferencia < 0) {
                await eliminarDelCarrito(usuarioId, productoId);
                if (nuevaCantidad > 0) {
                    for (let i = 0; i < nuevaCantidad; i++) {
                        await agregarAlCarrito(usuarioId, productoId);
                    }
                }
            }

            const updatedCart = await getCarrito(usuarioId);
            setCartItems(updatedCart);
        } catch (error) {
            console.error('Error al actualizar cantidad:', error);
        }
    };

    const getTotalItems = () => {
        return cartItems.reduce((total, item) => total + item.cantidad, 0);
    };

    const getTotalPrice = () => {
        if (!productos || productos.length === 0) return 0;
        return cartItems.reduce((total, item) => {
            const product = productos.find(p => p.id === item.productoId);
            const price = product ? (product.precio || product.price || 0) : 0;
            return total + (price * item.cantidad);
        }, 0);
    };

    const getProductName = (productoId) => {
        if (!productos || productos.length === 0) return `Producto ${productoId}`;
        const product = productos.find(p => p.id === productoId);
        return product ? (product.nombre || product.name || `Producto ${productoId}`) : `Producto ${productoId}`;
    };

    const clearCart = async () => {
        for (const item of cartItems) {
            await eliminarDelCarrito(usuarioId, item.productoId);
        }
        setCartItems([]);
    };

    return (
        <CartContext.Provider value={{
            cartItems,
            loading,
            addToCart,
            removeFromCart,
            updateQuantity,
            getTotalItems,
            getTotalPrice,
            getProductName,
            clearCart
        }}>
            {children}
        </CartContext.Provider>
    );
};

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context) {
        throw new Error('useCart must be used within a CartProvider');
    }
    return context;
};