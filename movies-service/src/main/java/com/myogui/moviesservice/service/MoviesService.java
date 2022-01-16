package com.myogui.moviesservice.service;

import com.myogui.moviesservice.exceptions.MovieFound;
import com.myogui.moviesservice.exceptions.MovieNotFound;
import com.myogui.moviesservice.model.Movie;
import com.myogui.moviesservice.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    @Autowired
    private MoviesRepository repository;

    public List<Movie> findAll() {
        return this.repository.findAll();
    }

    public Movie findById(Long id) throws MovieNotFound {
        Optional<Movie> movie = this.repository.findById(id);

        if(movie.isPresent()) {
            Movie m = movie.get();
            return m;
        }

        throw new MovieNotFound("La pelicula buscada no fue encontrada");
    }

    public Movie create(Movie newMovie) throws MovieFound {
        Optional<Movie> m = this.repository.findByTitle(newMovie.getTitle());

        if(m.isPresent()) {
            throw new MovieFound("La pelicula que se intenta crear ya existe");
        }

        return this.repository.save(newMovie);
    }

    public Movie update(Long id, Movie modifiedMovie) throws MovieNotFound {
        Optional<Movie> m = this.repository.findByTitle(modifiedMovie.getTitle());

        if(m.isPresent()) {
            Movie movie = m.get();

            movie.setTitle(modifiedMovie.getTitle());
            movie.setDescription(modifiedMovie.getDescription());
            movie.setDuration(modifiedMovie.getDuration());
            return this.repository.save(movie);
        } else {
            throw new MovieNotFound("No se encontro la pelicula a actualizar");
        }
    }

    public Movie delete(Long id) throws MovieNotFound {
        Optional<Movie> e = this.repository.findById(id);

        if(e.isPresent()) {
            this.repository.deleteById(id);
            return e.get();
        }else{
            throw new MovieNotFound("La pelicula que se intenta eliminar no existe");
        }
    }
}
