package com.technova.login.service;

import com.technova.login.model.Usuario;
import com.technova.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
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
}