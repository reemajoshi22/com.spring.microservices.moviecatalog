package com.spring.microservices.moviecatalog.com.spring.microservices.moviecatalog;

public class Movie {
    private String movieId;
    private String name;

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    // when we are using java to marsahll and unmarshall then first it
    // creates the instance and the does the work of marshal and unmaarshall,
    // so if empty constructor is not there how will it create the instance
    public Movie() {
    }


    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
