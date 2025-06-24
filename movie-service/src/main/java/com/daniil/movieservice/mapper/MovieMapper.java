package com.daniil.movieservice.mapper;

import com.daniil.movieservice.entity.MovieEntity;
import com.daniil.movieservice.dto.MovieRequest;
import com.daniil.dto.MovieResponse;

public class MovieMapper {
    public static MovieEntity toEntity(MovieRequest request) {
        return MovieEntity.builder()
                .posterLink(request.getPosterLink())
                .title(request.getTitle())
                .releasedYear(request.getReleaseYear())
                .certificate(request.getCertificate())
                .runtime(request.getRuntime())
                .genres(request.getGenres())
                .imdbRating(request.getImdbRating())
                .overview(request.getOverview())
                .metaScore(request.getMetaScore())
                .director(request.getDirector())
                .star1(request.getStar1())
                .star2(request.getStar2())
                .star3(request.getStar3())
                .star4(request.getStar4())
                .noOfVotes(request.getNoOfVotes())
                .build();
    }


    public static MovieResponse toResponse(MovieEntity movieEntity) {
        return MovieResponse.builder()
                .movieId(movieEntity.getMovieId())
                .posterLink(movieEntity.getPosterLink())
                .title(movieEntity.getTitle())
                .releaseYear(movieEntity.getReleasedYear())
                .certificate(movieEntity.getCertificate())
                .runtime(movieEntity.getRuntime())
                .genres(movieEntity.getGenres())
                .imdbRating(movieEntity.getImdbRating())
                .overview(movieEntity.getOverview())
                .metaScore(movieEntity.getMetaScore())
                .director(movieEntity.getDirector())
                .star1(movieEntity.getStar1())
                .star2(movieEntity.getStar2())
                .star3(movieEntity.getStar3())
                .star4(movieEntity.getStar4())
                .noOfVotes(movieEntity.getNoOfVotes())
                .build();
    }
}
