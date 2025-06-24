package com.daniil.recommendservice.client;

import com.daniil.dto.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service", url = "${movie-service.url}")
public interface MovieClient {

    @GetMapping("/api/movie/all")
    List<MovieResponse> getAllMovies();

    @GetMapping("/api/movie/{id}")
    MovieResponse getMovieById(@PathVariable("id") Long id);
}
