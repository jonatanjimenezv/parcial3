package com.actividad.actividad.controller;

import com.actividad.actividad.model.Producto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    // Crear producto
    @PostMapping
    public Mono<Producto> crearProducto(@RequestBody Producto producto) {
        productos.add(producto);
        return Mono.just(producto);
    }

    // Listar todos los productos (normal)
    @GetMapping
    public Flux<Producto> listarProductos() {
        return Flux.fromIterable(productos);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Mono<Producto> obtenerProducto(@PathVariable String id) {
        Optional<Producto> producto = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return producto.map(Mono::just).orElse(Mono.empty());
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public Mono<Producto> actualizarProducto(@PathVariable String id,
                                             @RequestBody Producto productoActualizado) {
        return obtenerProducto(id)
                .doOnNext(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setPrecio(productoActualizado.getPrecio());
                });
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public Mono<Void> eliminarProducto(@PathVariable String id) {
        return obtenerProducto(id)
                .doOnNext(productos::remove)
                .then(Mono.empty());
    }

    // Endpoint reactivo (ahora usa la lista dinámica)
    @GetMapping("/reactivo")
    public Flux<Producto> listarProductosReactivos() {
        return Flux.fromIterable(productos);
    }
}