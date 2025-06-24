package com.daniil.movieservice.repository;

import com.daniil.movieservice.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findMovieByTitle(String title);

    List<MovieEntity> findMovieByGenresContainingIgnoreCase(String genre);

    List<MovieEntity> findMovieByReleasedYear(int year);

}
