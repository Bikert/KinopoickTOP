package ru.bikert.test_task.abstractions;

import ru.bikert.test_task.abstractions.models.Movie;
import ru.bikert.test_task.abstractions.models.Rating;

import java.util.List;

public interface MovieSaver {

    void upsertMovie(Movie movie);
    void upsertRating(Rating rating);
    void upsertRatings(List<Rating> ratings);

}
