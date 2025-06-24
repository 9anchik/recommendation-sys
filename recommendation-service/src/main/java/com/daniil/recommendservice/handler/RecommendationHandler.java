package com.daniil.recommendservice.handler;

import com.daniil.dto.MovieResponse;
import com.daniil.recommendservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationHandler {

    private final RecommendationService recommendationService;

    @GetMapping("/popular")
    public ResponseEntity<List<MovieResponse>> getPopularMovies(
            @RequestParam(defaultValue = "15") int limit) {
        return ResponseEntity.ok(recommendationService.recommendPopularMovies(limit));

    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<MovieResponse>> getRecommendations(@PathVariable Long movieId,
                                                                  @RequestParam(defaultValue = "15") int limit) {
        return ResponseEntity.ok(recommendationService.recommendSimilarMovies(movieId, limit));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred: " + e.getMessage());
    }
}

