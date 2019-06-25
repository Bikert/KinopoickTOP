package ru.bikert.task_test.webapp.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.bikert.test_task.abstractions.MovieLoader;
import ru.bikert.test_task.abstractions.models.Rating;
import ru.bikert.test_task.database.helpers.DateHelpers;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class CachedMovieLoader implements MovieLoader {

    private static final int defaultCount = 50;
    private final MovieLoader originalMovieLoader;

    public CachedMovieLoader(MovieLoader originalMovieLoader) {
        this.originalMovieLoader = originalMovieLoader;
    }

    @Cacheable("movies")
    @Override
    public List<Rating> loadTop(Calendar normalizedDate, Integer limit) {
        return originalMovieLoader.loadTop(normalizedDate, limit);
    }

//    @Override
//    public List<Rating> loadTop(Calendar date, Integer limit) {
//        Calendar normalizedDate = DateHelpers.getDate(date);
//
//        if (limit > defaultCount) {
//            return loadTopInternal(normalizedDate, limit);
//        }
//
//        List<Rating> defaultCountResult = loadTopInternal(normalizedDate, defaultCount);
//
//        if (defaultCountResult == null) return null;
//
//        return defaultCountResult.stream().limit(limit).collect(Collectors.toList());
//    }
//
//    @Cacheable("movies")
//    public List<Rating> loadTopInternal(Calendar normalizedDate, Integer limit) {
//        return originalMovieLoader.loadTop(normalizedDate, limit);
//    }
}
