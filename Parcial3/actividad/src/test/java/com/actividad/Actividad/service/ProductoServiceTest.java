package com.actividad.actividad.service;

import com.actividad.actividad.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void testObtenerProductos() {
        Flux<Producto> productos = productoService.obtenerTodos();

        StepVerifier.create(productos)
                .expectNextCount(3)
                .verifyComplete();
    }
}