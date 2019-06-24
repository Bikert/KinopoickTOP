package ru.bikert.test_task.abstractions.models;

import java.util.Calendar;

public class Rating {

    private Calendar date;
    private Double rating;
    private Integer votes;
    private Movie movie;


    public Rating(Calendar date, Double rating, Integer votes, Movie movie) {
        this.date = date;
        this.rating = rating;
        this.votes = votes;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "date=" + date +
                ", rating=" + rating +
                ", votes=" + votes +
                ", movie=" + movie +
                '}';
    }
}
