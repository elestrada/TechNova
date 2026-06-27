// src/components/ProductCard.jsx
import React from 'react';
import { useCart } from '../context/CartContext';
import './ProductCard.css';

const ProductCard = ({ product }) => {
    const { addToCart } = useCart();
    const productName = product.nombre || product.name || 'Producto';
    const productPrice = product.precio || product.price || 0;
    const imageUrl = product.imagen || product.image || 'https://via.placeholder.com/400x400?text=Sin+imagen';

    const handleAddToCart = () => {
        addToCart(product.id);
    };

    return (
        <div className="product-card">
            <img src={imageUrl} alt={productName} className="product-image" loading="lazy" />
            <h3 className="product-name">{productName}</h3>
            <p className="product-price">${productPrice.toLocaleString()}</p>
            <button className="add-btn" onClick={handleAddToCart}>Agregar al carrito</button>
        </div>
    );
};

export default ProductCard;