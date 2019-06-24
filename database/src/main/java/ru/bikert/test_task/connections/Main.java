package ru.bikert.test_task.connections;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bikert.test_task.abstractions.MovieLoader;
import ru.bikert.test_task.abstractions.MovieSaver;
import ru.bikert.test_task.abstractions.models.Rating;
import ru.bikert.test_task.connections.helpers.DateHelpers;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.bikert.test_task");

        context.refresh();
        MovieSaver ms = (MovieSaver) context.getBean("movieService1");
        MovieLoader mg = (MovieLoader) context.getBean("movieService1");


//        for (int i = 0; i<100; i++) {
//            Movie movie = new Movie();
//            movie.setId(i);
//            movie.setAmountVoid(212);
//            movie.setName("terytasr1333");
//            movie.setYear(2009);
//            ms.upsertMovie(movie);
//            double d = Math.random() * 1000;
//            ms.upsertRating(new Rating(DateHelpers.getCurrentDate(), (double) Math.round(d * 100) / 100, movie));
//        }
        List<Rating> ratings = mg.loadTop(10, DateHelpers.getCurrentDate());
        System.out.println(ratings);


        //close the spring context
        context.close();
    }
}
