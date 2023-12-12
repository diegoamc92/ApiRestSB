package com.example.ApiRestSB.controller;

import com.example.ApiRestSB.models.Pelicula;
import com.example.ApiRestSB.repository.PeliculaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculaController {

    PeliculaRepository repository;

    PeliculaController(PeliculaRepository repository){
        this.repository = repository;

    }

    @GetMapping("/api/crearPeliculas")
    public void crearPeliculas(){
        Pelicula pelicula1 = new Pelicula("Titanic", "James Cameron", "Drama");
        Pelicula pelicula2 = new Pelicula("Forrest Gump", "Robert Zemekis", "Drama");
        Pelicula pelicula3 = new Pelicula("Star Wars", "George Lucas", "Accion");

        repository.save(pelicula1);
        repository.save(pelicula2);
        repository.save(pelicula3);
    }

    @GetMapping("/api/peliculas")
    public List<Pelicula> obtenerPeliculas(){
        return repository.findAll();
    }

    @GetMapping("/api/pelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPelicula(@PathVariable Long id){
        Optional<Pelicula> opt = repository.findById(id);
        if (opt.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(opt.get());
        }
    }
}
