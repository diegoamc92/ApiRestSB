package com.example.ApiRestSB.controller;

import com.example.ApiRestSB.models.Pelicula;
import com.example.ApiRestSB.repository.PeliculaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculaController {

    PeliculaRepository repository;

    PeliculaController(PeliculaRepository repository){
        this.repository = repository;

    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/api/crearPeliculas")
    public void crearPeliculas(){
        Pelicula pelicula1 = new Pelicula("Titanic", "James Cameron", "Drama");
        Pelicula pelicula2 = new Pelicula("Forrest Gump", "Robert Zemekis", "Drama");
        Pelicula pelicula3 = new Pelicula("Star Wars", "George Lucas", "Accion");

        repository.save(pelicula1);
        repository.save(pelicula2);
        repository.save(pelicula3);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @GetMapping("/api/peliculas")
    public List<Pelicula> obtenerPeliculas(){
        return repository.findAll();
    }

    @CrossOrigin("http://127.0.0.1:5500")
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

    @CrossOrigin("http://127.0.0.1:5500")
    @PostMapping("/api/peliculas")
    public ResponseEntity<Pelicula> guardarPelicula(@RequestBody Pelicula pelicula){
        if (pelicula.getId()!= null){
            return ResponseEntity.badRequest().build();
        }
        repository.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @PutMapping("/api/peliculas")
    public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula){
        if (pelicula.getId()==null || !repository.existsById(pelicula.getId())){
            return ResponseEntity.badRequest().build();
        }
        repository.save(pelicula);
        return ResponseEntity.ok(pelicula);
    }

    @CrossOrigin("http://127.0.0.1:5500")
    @DeleteMapping("/api/peliculas/{id}")
    public ResponseEntity<Pelicula> eliminarPelicula(@PathVariable Long id){
        if (id == null || !repository.existsById(id)){
            return ResponseEntity.badRequest().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
