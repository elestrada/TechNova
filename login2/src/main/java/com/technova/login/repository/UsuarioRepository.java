package com.technova.login.repository;

import com.technova.login.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Busca un usuario por su correo electrónico.
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Verifica si existe un usuario con el correo electrónico dado.
     */
    boolean existsByEmail(String email);
    
    /**
     * Verifica si existe un usuario por su ID.
     */
    boolean existsById(Long id);
    
    /**
     * Elimina un usuario por su correo electrónico.
     */
    void deleteByEmail(String email);
}