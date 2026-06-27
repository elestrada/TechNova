// src/components/CategoryFilter.jsx
import React from 'react';
import './CategoryFilter.css';

const CategoryFilter = ({ categories, selectedCategory, onSelectCategory }) => {
    return (
        <div className="category-filter">
            <button
                className={`category-btn ${selectedCategory === 'todas' ? 'active' : ''}`}
                onClick={() => onSelectCategory('todas')}
            >
                Todas
            </button>
            {categories.map((category) => (
                <button
                    key={category}
                    className={`category-btn ${selectedCategory === category ? 'active' : ''}`}
                    onClick={() => onSelectCategory(category)}
                >
                    {category}
                </button>
            ))}
        </div>
    );
};

export default CategoryFilter;