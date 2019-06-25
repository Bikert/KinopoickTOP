import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bikert.test_task.abstractions.MovieSaver;
import ru.bikert.test_task.abstractions.models.Rating;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    private final static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();;
    public static void main(String[] args) {

        initContext();

        MovieSaver ms = (MovieSaver) context.getBean("movieService");

        PageParser pageParser = new PageParser();
        List<Rating> ratings = pageParser.getRatings(Calendar.getInstance(), 200);

        ms.upsertRatings(ratings);
        ratings.forEach(System.out::println);


        closeContext();
    }

    private static void initContext(){
        context.scan("ru.bikert.test_task");
        context.refresh();
    }

    private static void closeContext(){
        context.close();
    }


}
