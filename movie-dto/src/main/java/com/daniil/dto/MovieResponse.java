package com.daniil.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private Long movieId;
    private String posterLink;
    private String title;
    private Integer releaseYear;
    private String certificate;
    private String runtime;
    private String genres;
    private Double imdbRating;
    private String overview;
    private Integer metaScore;
    private String director;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private Long noOfVotes;
}
