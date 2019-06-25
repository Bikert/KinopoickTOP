package ru.bikert.test_task.database.dto;

import ru.bikert.test_task.abstractions.models.Rating;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Rating")
public class RatingDTO {

    @EmbeddedId
    private RatingID id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "votes")
    private Integer votes;

    public RatingDTO() {
    }

    public RatingDTO(Rating rating) {
        MovieDTO movieDTO = new MovieDTO(rating.getMovie());
        RatingID ratingID = new RatingID(movieDTO, rating.getDate());
        this.votes = rating.getVotes();
        this.id = ratingID;
        this.rating = rating.getRating();
    }

    public RatingID getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getVotes() {
        return votes;
    }
}
