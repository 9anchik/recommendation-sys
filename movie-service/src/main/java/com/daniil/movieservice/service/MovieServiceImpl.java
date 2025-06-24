package com.daniil.movieservice.service;

import com.daniil.movieservice.entity.MovieEntity;
import com.daniil.movieservice.dto.MovieRequest;
import com.daniil.dto.MovieResponse;
import com.daniil.movieservice.mapper.MovieMapper;
import com.daniil.movieservice.repository.MovieRepository;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse createMovie(MovieRequest request) {
        MovieEntity movieEntity = MovieMapper.toEntity(request);
        return MovieMapper.toResponse(movieRepository.save(movieEntity));
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MovieMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public List<MovieResponse> getMovieByTitle(String title) {
        List<MovieEntity> movies = movieRepository.findMovieByTitle(title);

        if (movies == null || movies.isEmpty()) {
            throw new EntityNotFoundException("Фильм " + title + " не найден");
        }

        return movies.stream()
                .map(MovieMapper::toResponse)
                .toList();
    }

    @Override
    public List<MovieResponse> getMovieByGenre(String genre) {
        return movieRepository.findMovieByGenresContainingIgnoreCase(genre)
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }


    @Override
    public List<MovieResponse> getMovieByYear(int year) {
        return movieRepository.findMovieByReleasedYear(year)
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
