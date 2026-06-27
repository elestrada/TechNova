package com.technova.login.repository;

import com.technova.login.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByUsuarioId(Long usuarioId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Carrito c WHERE c.usuarioId = :usuarioId AND c.productoId = :productoId")
    void deleteByUsuarioIdAndProductoId(@Param("usuarioId") Long usuarioId, @Param("productoId") Long productoId);
}