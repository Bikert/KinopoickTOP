package ru.bikert.test_task.abstractions;

import ru.bikert.test_task.abstractions.models.Rating;

import java.util.Calendar;
import java.util.List;

public interface MovieLoader {
    List<Rating> loadTop(Calendar date, Integer limit);
}
