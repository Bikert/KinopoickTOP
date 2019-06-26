package ru.bikert.test_task.abstractions.models;

public class Movie {

    private Integer id;
    private String name;
    private Integer year;

    public void setName(String name) {
        this.name = name;
    }

    public Movie(Integer id, String name, Integer year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}