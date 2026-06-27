package com.technova.login.controller;

import com.technova.login.model.Carrito;
import com.technova.login.model.Producto;
import com.technova.login.repository.CarritoRepository;
import com.technova.login.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:5173")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener el carrito de un usuario
    @GetMapping("/{usuarioId}")
    public List<Carrito> obtenerCarrito(@PathVariable Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    // Agregar producto al carrito
    @PostMapping("/agregar")
    public Map<String, Object> agregarAlCarrito(@RequestBody Map<String, Long> request) {
        Long usuarioId = request.get("usuarioId");
        Long productoId = request.get("productoId");

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        List<Carrito> items = carritoRepository.findByUsuarioId(usuarioId);
        for (Carrito item : items) {
            if (item.getProductoId().equals(productoId)) {
                item.setCantidad(item.getCantidad() + 1);
                carritoRepository.save(item);
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Cantidad actualizada");
                response.put("item", item);
                return response;
            }
        }

        Carrito nuevoItem = new Carrito(usuarioId, productoId, 1);
        Carrito guardado = carritoRepository.save(nuevoItem);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Producto agregado al carrito");
        response.put("item", guardado);
        return response;
    }

    // ELIMINAR PRODUCTO DEL CARRITO (SOLO UNO)
    @DeleteMapping("/eliminar/{usuarioId}/{productoId}")
    @Transactional
    public Map<String, String> eliminarDelCarrito(@PathVariable Long usuarioId, @PathVariable Long productoId) {
        carritoRepository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto eliminado del carrito");
        return response;
    }

    // Vaciar el carrito de un usuario
    @DeleteMapping("/vaciar/{usuarioId}")
    public Map<String, String> vaciarCarrito(@PathVariable Long usuarioId) {
        List<Carrito> items = carritoRepository.findByUsuarioId(usuarioId);
        carritoRepository.deleteAll(items);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Carrito vaciado");
        return response;
    }
}