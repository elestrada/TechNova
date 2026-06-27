// src/App.jsx
import React, { useState, useEffect } from 'react';
import Header from './components/Header';
import ProductList from './components/ProductList';
import Carousel from './components/Carousel';
import CategoryFilter from './components/CategoryFilter';
import Footer from './components/Footer';
import Login from './components/Login';
import { CartProvider } from './context/CartContext';
import { AuthProvider, useAuth } from './context/AuthContext';
import { getProducts } from './services/api';
import './index.css';

function AppContent() {
    const { user } = useAuth();
    const [products, setProducts] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('todas');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const usuario = user?.nombreUsuario || 'Invitado';

    // Siempre ejecutar el useEffect, independientemente del estado de autenticación
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const data = await getProducts();
                setProducts(data);
            } catch (err) {
                setError('Error al cargar los productos');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };
        fetchProducts();
    }, []);

    // Si no está autenticado, mostrar login (después de todos los hooks)
    if (!user) {
        return <Login />;
    }

    const categories = [...new Set(products.map(p => p.categoria || 'Sin categoría'))];

    const filteredProducts = products.filter(product => {
        const matchSearch = (product.nombre || product.name || '')
            .toLowerCase()
            .includes(searchTerm.toLowerCase());
        const matchCategory = selectedCategory === 'todas' || 
            (product.categoria || 'Sin categoría') === selectedCategory;
        return matchSearch && matchCategory;
    });

    return (
        <CartProvider productos={filteredProducts}>
            <div className="app">
                <Header usuario={usuario} onSearch={setSearchTerm} />
                <main>
                    {loading ? (
                        <div className="loading-message">Cargando productos...</div>
                    ) : error ? (
                        <div className="error-message">{error}</div>
                    ) : (
                        <>
                            <CategoryFilter
                                categories={categories}
                                selectedCategory={selectedCategory}
                                onSelectCategory={setSelectedCategory}
                            />
                            <Carousel products={filteredProducts.slice(0, 3)} />
                            <ProductList products={filteredProducts} searchTerm={searchTerm} />
                        </>
                    )}
                </main>
                <Footer />
            </div>
        </CartProvider>
    );
}

function App() {
    return (
        <AuthProvider>
            <AppContent />
        </AuthProvider>
    );
}

export default App;