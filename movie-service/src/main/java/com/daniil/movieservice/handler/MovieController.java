package com.daniil.movieservice.handler;

import com.daniil.movieservice.dto.MovieRequest;
import com.daniil.dto.MovieResponse;
import com.daniil.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<MovieResponse>> getMoviesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }

    @GetMapping("/search/genre")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(movieService.getMovieByGenre(genre));
    }


    @GetMapping("/search/year")
    public ResponseEntity<List<MovieResponse>> getMoviesByYear(@RequestParam int year) {
        return ResponseEntity.ok(movieService.getMovieByYear(year));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
