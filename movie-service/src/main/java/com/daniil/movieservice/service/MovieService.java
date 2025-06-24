package com.daniil.movieservice.service;

import com.daniil.movieservice.dto.MovieRequest;
import com.daniil.dto.MovieResponse;

import java.util.List;

public interface MovieService {

    MovieResponse createMovie(MovieRequest request);

    MovieResponse getMovieById(Long id);

    List<MovieResponse> getMovieByTitle(String title);

    List<MovieResponse> getMovieByGenre(String genre);

    List<MovieResponse> getMovieByYear(int year);

    List<MovieResponse> getAllMovies();

    void deleteMovie(Long id);
}
