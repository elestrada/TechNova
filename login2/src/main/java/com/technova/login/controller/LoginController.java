package com.technova.login.controller;

import com.technova.login.model.Usuario;
import com.technova.login.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.util.List;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    // ===== ENDPOINTS DE AUTENTICACIÓN (VISTAS) =====
    
    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String email,
                                @RequestParam String password,
                                Model model,
                                HttpSession session) {
        
        if (email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "❌ Complete todos los campos");
            return "login";
        }
        
        var usuarioOpt = usuarioService.autenticar(email, password);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            session.setAttribute("nombreUsuario", usuario.getNombreUsuario());
            session.setAttribute("email", email);
            session.setAttribute("logueado", true);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "❌ Correo o contraseña incorrectos");
            return "login";
        }
    }
    
    @GetMapping("/home")
    public String mostrarHome(HttpSession session, Model model) {
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }
        model.addAttribute("nombreUsuario", session.getAttribute("nombreUsuario"));
        model.addAttribute("email", session.getAttribute("email"));
        return "home";
    }
    
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
// ===== ENDPOINT DE LOGIN PARA REACT =====
@PostMapping("/api/login")
@ResponseBody
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");
    
    if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
        return ResponseEntity.badRequest().body(Map.of("error", "Complete todos los campos"));
    }
    
    try {
        Optional<Usuario> usuarioOpt = usuarioService.autenticar(email, password);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", usuario.getId());
            response.put("nombreUsuario", usuario.getNombreUsuario());
            response.put("email", usuario.getEmail());
            response.put("activo", usuario.getActivo());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Error en el servidor"));
    }
}

}