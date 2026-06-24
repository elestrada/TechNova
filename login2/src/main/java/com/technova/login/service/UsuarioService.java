package com.technova.login.service;

import com.technova.login.model.Usuario;
import com.technova.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // ===== MÉTODOS DE AUTENTICACIÓN =====
    
    /**
     * Autentica un usuario verificando sus credenciales.
     */
    public Optional<Usuario> autenticar(String email, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getActivo() && usuario.getContrasenaHash().equals(contrasena)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }
    
    // ===== MÉTODOS CRUD PARA USUARIOS =====
    
    /**
     * Lista todos los usuarios registrados.
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Busca un usuario por su ID.
     */
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    /**
     * Busca un usuario por su email.
     */
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    /**
     * Guarda un usuario en la base de datos.
     */
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Verifica si existe un usuario por su ID.
     */
    public boolean existePorId(Long id) {
        return usuarioRepository.existsById(id);
    }
    
    /**
     * Verifica si existe un usuario por su email.
     */
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    /**
     * Elimina un usuario por su ID.
     */
    public void eliminarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    /**
     * Elimina un usuario por su email.
     */
    public void eliminarPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}