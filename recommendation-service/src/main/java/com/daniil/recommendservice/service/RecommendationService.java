package com.daniil.recommendservice.service;

import com.daniil.dto.MovieResponse;

import java.util.List;

public interface RecommendationService {
    List<MovieResponse> recommendSimilarMovies(Long movieId, int limit);
    List<MovieResponse> recommendPopularMovies(int limit);
}

