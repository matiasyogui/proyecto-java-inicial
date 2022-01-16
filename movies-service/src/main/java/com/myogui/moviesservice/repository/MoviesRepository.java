package com.myogui.moviesservice.repository;

import com.myogui.moviesservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
