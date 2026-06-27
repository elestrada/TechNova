// src/components/CartSummary.jsx
import React, { useState } from 'react';
import { useCart } from '../context/CartContext';
import './CartSummary.css';

const CartSummary = () => {
    const { cartItems, removeFromCart, updateQuantity, getTotalItems, getTotalPrice, getProductName } = useCart();
    const [isOpen, setIsOpen] = useState(false);

    const totalItems = getTotalItems();
    const totalPrice = getTotalPrice();

    return (
        <div className="cart-summary">
            <button className="cart-toggle" onClick={() => setIsOpen(!isOpen)}>
                <svg className="cart-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                    <circle cx="9" cy="21" r="1" />
                    <circle cx="20" cy="21" r="1" />
                    <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6" />
                </svg>
                {totalItems > 0 && <span className="cart-badge">{totalItems}</span>}
            </button>

            {isOpen && (
                <div className="cart-dropdown">
                    <div className="cart-header">
                        <h4>Mi Carrito</h4>
                        <button className="cart-close" onClick={() => setIsOpen(false)}>✕</button>
                    </div>
                    {cartItems.length === 0 ? (
                        <p className="cart-empty">El carrito está vacío</p>
                    ) : (
                        <>
                            <ul className="cart-items">
                                {cartItems.map(item => (
                                    <li key={item.id} className="cart-item">
                                        <div className="cart-item-info">
                                            <span className="cart-item-name">{getProductName(item.productoId)}</span>
                                            <div className="cart-item-quantity">
                                                <button 
                                                    className="qty-btn"
                                                    onClick={() => updateQuantity(item.productoId, item.cantidad - 1)}
                                                    disabled={item.cantidad <= 1}
                                                >−</button>
                                                <span>{item.cantidad}</span>
                                                <button 
                                                    className="qty-btn"
                                                    onClick={() => updateQuantity(item.productoId, item.cantidad + 1)}
                                                >+</button>
                                            </div>
                                        </div>
                                        <button className="cart-item-remove" onClick={() => removeFromCart(item.productoId)}>
                                            <svg className="trash-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                                <polyline points="3 6 5 6 21 6" />
                                                <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2" />
                                                <line x1="10" y1="11" x2="10" y2="17" />
                                                <line x1="14" y1="11" x2="14" y2="17" />
                                            </svg>
                                        </button>
                                    </li>
                                ))}
                            </ul>
                            <div className="cart-footer">
                                <span className="cart-total">Total: ${totalPrice.toLocaleString()}</span>
                                <button className="cart-checkout">Proceder al pago</button>
                            </div>
                        </>
                    )}
                </div>
            )}
        </div>
    );
};

export default CartSummary;