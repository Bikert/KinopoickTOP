package ru.bikert.test_task.database.dto;

import com.sun.istack.NotNull;
import ru.bikert.test_task.abstractions.models.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class MovieDTO {

    @Id
    @Column(name = "movie_ID")
    @NotNull
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(getId(), movieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public MovieDTO() {
    }

    public MovieDTO(Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.year = movie.getYear();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
