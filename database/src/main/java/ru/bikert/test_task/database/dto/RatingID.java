package ru.bikert.test_task.database.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class RatingID implements Serializable {

    private static final long serialVersionUID = -5525007858144751009L;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_ID")
    private MovieDTO movie;

    @Column(name = "date")
    private Calendar date;

    public RatingID() { }

    public RatingID(MovieDTO movie, Calendar date) {
        this.movie = movie;
        this.date = date;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof RatingID)) return false;
        RatingID that = (RatingID) o;
        return Objects.equals(getMovie(), that.getMovie()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getDate(), getMovie());
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public Calendar getDate() {
        return date;
    }

}
