package ru.bikert.test_task.database.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bikert.test_task.database.dto.MovieDTO;
import ru.bikert.test_task.database.dto.RatingDTO;
import ru.bikert.test_task.database.dto.RatingID;

@Configuration
public class DataBaseConfiguration {

    @Bean
    public SessionFactory sessionFactory() {

        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(MovieDTO.class);
        configuration.addAnnotatedClass(RatingDTO.class);
        configuration.addAnnotatedClass(RatingID.class);
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();

        return sessionFactory;
    }
}
