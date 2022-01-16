package com.myogui.moviesservice.controller;

import com.myogui.moviesservice.exceptions.MovieFound;
import com.myogui.moviesservice.exceptions.MovieNotFound;
import com.myogui.moviesservice.model.Movie;
import com.myogui.moviesservice.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path="/api/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Movie>> getAll() {
        return new ResponseEntity<>(this.moviesService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Movie> create(@RequestBody @Validated Movie newMovie) {
        try {
            return new ResponseEntity<>(this.moviesService.create(newMovie), HttpStatus.OK);
        } catch (MovieFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody @Validated Movie newMovie) {
        try {
            return new ResponseEntity<>(this.moviesService.update(id, newMovie), HttpStatus.OK);
        } catch (MovieNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Movie> delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(this.moviesService.delete(id), HttpStatus.OK);
        } catch (MovieNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
