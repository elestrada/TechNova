// src/components/Carousel.jsx
import React, { useState, useEffect } from 'react';
import './Carousel.css';

const Carousel = ({ products }) => {
    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) =>
                prevIndex === products.length - 1 ? 0 : prevIndex + 1
            );
        }, 4000);
        return () => clearInterval(interval);
    }, [products.length]);

    const goToSlide = (index) => {
        setCurrentIndex(index);
    };

    const nextSlide = () => {
        setCurrentIndex((prevIndex) =>
            prevIndex === products.length - 1 ? 0 : prevIndex + 1
        );
    };

    const prevSlide = () => {
        setCurrentIndex((prevIndex) =>
            prevIndex === 0 ? products.length - 1 : prevIndex - 1
        );
    };

    if (!products || products.length === 0) return null;

    return (
        <div className="carousel-container">
            <div className="carousel-slide" style={{ transform: `translateX(-${currentIndex * 100}%)` }}>
                {products.map((product) => (
                    <div key={product.id} className="carousel-item">
                        <img 
                            src={product.imagen || product.image} 
                            alt={product.nombre || product.name} 
                            className="carousel-image" 
                        />
                        <div className="carousel-content">
                            <h3>{product.nombre || product.name}</h3>
                            <p className="carousel-price">${(product.precio || product.price || 0).toLocaleString()}</p>
                            <button className="carousel-btn">Ver producto</button>
                        </div>
                    </div>
                ))}
            </div>

            <button className="carousel-btn-prev" onClick={prevSlide}>❮</button>
            <button className="carousel-btn-next" onClick={nextSlide}>❯</button>

            <div className="carousel-indicators">
                {products.map((_, index) => (
                    <span
                        key={index}
                        className={`dot ${index === currentIndex ? 'active' : ''}`}
                        onClick={() => goToSlide(index)}
                    />
                ))}
            </div>
        </div>
    );
};

export default Carousel;