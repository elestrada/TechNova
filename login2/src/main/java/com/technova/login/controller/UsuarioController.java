package com.technova.login.controller;

import com.technova.login.model.Usuario;
import com.technova.login.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.buscarPorId(id)
                .map(u -> {
                    u.setNombreUsuario(usuario.getNombreUsuario());
                    u.setEmail(usuario.getEmail());
                    u.setContrasenaHash(usuario.getContrasenaHash());
                    u.setActivo(usuario.getActivo());
                    return usuarioService.guardar(u);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        if (!usuarioService.existePorId(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioService.eliminarPorId(id);
    }
}