package com.daniil.movieservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "imdb_movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(name = "poster_link")
    private String posterLink;

    @Column(name = "series_title", nullable = false)
    private String title;

    @Column(name = "released_year")
    private Integer releasedYear;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "genres")
    private String genres;

    @Column(name = "imdb_rating")
    private Double imdbRating;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "meta_score")
    private Integer metaScore;

    @Column(name = "director")
    private String director;

    @Column(name = "star1")
    private String star1;

    @Column(name = "star2")
    private String star2;

    @Column(name = "star3")
    private String star3;

    @Column(name = "star4")
    private String star4;

    @Column(name = "no_of_votes")
    private Long noOfVotes;
}
