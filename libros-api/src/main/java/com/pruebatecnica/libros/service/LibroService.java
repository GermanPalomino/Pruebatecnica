package com.pruebatecnica.libros.service;

import com.pruebatecnica.libros.model.Libro;
import com.pruebatecnica.libros.repository.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public Page<Libro> listar(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Libro crear(Libro libro) {
        validarFecha(libro.getFechaPublicacion());
        return repo.save(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        Libro existente = repo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Libro no encontrado"));

        validarFecha(libro.getFechaPublicacion());

        existente.setNombre(libro.getNombre());
        existente.setAutor(libro.getAutor());
        existente.setDescripcion(libro.getDescripcion());
        existente.setFechaPublicacion(libro.getFechaPublicacion());
        existente.setNumeroEjemplares(libro.getNumeroEjemplares());
        existente.setCosto(libro.getCosto());

        return repo.save(existente);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalStateException("Libro no encontrado");
        }
        repo.deleteById(id);
    }

    private void validarFecha(LocalDate fecha) {
        if (fecha.isBefore(LocalDate.now().minusYears(10))) {
            throw new IllegalArgumentException(
                "No se permiten libros con más de 10 años de publicación"
            );
        }
    }
}