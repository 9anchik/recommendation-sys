package com.daniil.recommendservice.service;


import com.daniil.recommendservice.client.MovieClient;
import com.daniil.dto.MovieResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final MovieClient movieClient;

    @Override
    public List<MovieResponse> recommendPopularMovies(int limit) {
        List<MovieResponse> movieResponses = movieClient.getAllMovies();
        return movieResponses.stream()
                .filter(movieClient -> movieClient.getImdbRating() != null && movieClient.getMetaScore() != null)
                .sorted(Comparator.comparingDouble(MovieResponse::getImdbRating).reversed()
                .thenComparing(MovieResponse::getMetaScore).reversed()
                .thenComparing(MovieResponse::getNoOfVotes).reversed()).limit(limit).toList();

    }

    @Override
    public List<MovieResponse> recommendSimilarMovies(Long movieId, int limit) {
        List<MovieResponse> allMovies = movieClient.getAllMovies();
        MovieResponse target = movieClient.getMovieById(movieId);

        Map<MovieResponse, Double> similarityMap = new HashMap<>();

        for (MovieResponse movieResponse : allMovies) {
            if (!movieResponse.getMovieId().equals(movieId)) {
                double similarity = calculateSimilarity(target, movieResponse);
                similarityMap.put(movieResponse, similarity);
            }
        }

        return similarityMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 0.5)
                .sorted(Map.Entry.<MovieResponse, Double>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .toList();
    }

    private double calculateSimilarity(MovieResponse m1, MovieResponse m2) {
        String text1 = buildCombinedText(m1);
        String text2 = buildCombinedText(m2);
        double textSim = cosineSimilarity(text1, text2);

        double ratingSim = normalizeDoubleSimilarity(m1.getImdbRating(), m2.getImdbRating(), 10.0);
        double metaSim = normalizeIntegerSimilarity(m1.getMetaScore(), m2.getMetaScore(), 100);

        return 0.6 * textSim + 0.2 * ratingSim + 0.2 * metaSim;
    }

    private String buildCombinedText(MovieResponse movieResponse) {
        List<String> tokens = new ArrayList<>();

        if (movieResponse.getGenres() != null) tokens.add(movieResponse.getGenres());
        if (movieResponse.getStar1() != null) tokens.add(movieResponse.getStar1());
        if (movieResponse.getStar2() != null) tokens.add(movieResponse.getStar2());
        if (movieResponse.getStar3() != null) tokens.add(movieResponse.getStar3());
        if (movieResponse.getStar4() != null) tokens.add(movieResponse.getStar4());
        if (movieResponse.getDirector() != null) tokens.add(movieResponse.getDirector());
        if (movieResponse.getOverview() != null) tokens.add(movieResponse.getOverview());

        return String.join(" ", tokens).toLowerCase();
    }

    private double normalizeDoubleSimilarity(Double d1, Double d2, double max) {
        if (d1 == null || d2 == null) return 0.0;
        return 1.0 - Math.abs(d1 - d2) / max;
    }

    private double normalizeIntegerSimilarity(Integer i1, Integer i2, int max) {
        if (i1 == null || i2 == null) return 0.0;
        return 1.0 - Math.abs(i1 - i2) / (double) max;
    }

    private double cosineSimilarity(String s1, String s2) {
        Map<String, Integer> freq1 = termFrequency(s1);
        Map<String, Integer> freq2 = termFrequency(s2);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(freq1.keySet());
        allWords.addAll(freq2.keySet());

        int dotProduct = 0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (String word : allWords) {
            int f1 = freq1.getOrDefault(word, 0);
            int f2 = freq2.getOrDefault(word, 0);
            dotProduct += f1 * f2;
            norm1 += f1 * f1;
            norm2 += f2 * f2;
        }

        return (norm1 == 0 || norm2 == 0) ? 0.0 : dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    private Map<String, Integer> termFrequency(String text) {
        Map<String, Integer> freq = new HashMap<>();
        String[] tokens = text.split("\\W+");
        for (String token : tokens) {
            if (!token.isBlank()) {
                freq.put(token, freq.getOrDefault(token, 0) + 1);
            }
        }
        return freq;
    }
}
