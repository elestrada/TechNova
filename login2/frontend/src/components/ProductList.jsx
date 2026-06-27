// src/components/ProductList.jsx
import React from 'react';
import ProductCard from './ProductCard';
import './ProductList.css';

const ProductSection = ({ title, items }) => {
    if (!items || items.length === 0) return null;
    return (
        <section className="product-section">
            <h2 className="section-title">{title}</h2>
            <div className="products-grid">
                {items.map(product => (
                    <ProductCard key={product.id} product={product} />
                ))}
            </div>
        </section>
    );
};

const ProductList = ({ products, searchTerm }) => {
    // Si hay búsqueda, mostrar solo los productos filtrados
    if (searchTerm && searchTerm.length > 1) {
        return (
            <div className="product-list-container">
                <ProductSection title={`Resultados para "${searchTerm}"`} items={products} />
            </div>
        );
    }

    // Si no hay productos o hay menos de 4, mostrar todos en una sola sección
    if (products.length === 0) {
        return <div className="product-list-container"><p>No hay productos disponibles</p></div>;
    }

    if (products.length <= 4) {
        return (
            <div className="product-list-container">
                <ProductSection title="Productos destacados" items={products} />
            </div>
        );
    }

    // Agrupar productos en secciones fijas de 4 productos cada una
    const sections = [];
    const chunkSize = 4; // Productos por sección

    // Sección 1: Productos destacados (primeros 4)
    sections.push({
        title: 'Productos destacados',
        items: products.slice(0, 4)
    });

    // Sección 2: Ofertas del día (siguientes 4, si existen)
    const ofertasItems = products.slice(4, 8);
    if (ofertasItems.length > 0) {
        sections.push({
            title: 'Ofertas del día',
            items: ofertasItems
        });
    }

    // Sección 3: Promociones (restantes, máximo 4)
    const promocionesItems = products.slice(8, 12);
    if (promocionesItems.length > 0) {
        sections.push({
            title: 'Promociones',
            items: promocionesItems
        });
    }

    return (
        <div className="product-list-container">
            {sections.map((section, index) => (
                <ProductSection key={index} title={section.title} items={section.items} />
            ))}
        </div>
    );
};

export default ProductList;