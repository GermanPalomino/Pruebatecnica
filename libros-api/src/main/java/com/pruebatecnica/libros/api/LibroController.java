package com.pruebatecnica.libros.api;

import com.pruebatecnica.libros.model.Libro;
import com.pruebatecnica.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Libro> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "ASC") String direction
    ) {
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.Direction.fromString(direction), sort
        );
        return service.listar(pageable);
    }

    @PostMapping
    public Libro crear(@Valid @RequestBody Libro libro) {
        return service.crear(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Libro libro
    ) {
        return service.actualizar(id, libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}