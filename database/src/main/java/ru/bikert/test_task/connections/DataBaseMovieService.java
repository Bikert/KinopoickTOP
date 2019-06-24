package ru.bikert.test_task.connections;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.bikert.test_task.abstractions.MovieLoader;
import ru.bikert.test_task.abstractions.MovieSaver;
import ru.bikert.test_task.abstractions.models.Movie;
import ru.bikert.test_task.abstractions.models.Rating;
import ru.bikert.test_task.connections.dto.MovieDTO;
import ru.bikert.test_task.connections.dto.RatingDTO;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static ru.bikert.test_task.connections.helpers.DateHelpers.getDate;

@Service("movieService1")
@Transactional
public class DataBaseMovieService implements MovieSaver, MovieLoader {

    protected static Logger logger = Logger.getLogger(DataBaseMovieService.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    public void upsertMovie(Movie movie){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            MovieDTO movieDTO = movieToMovieDTO(movie);
            transaction = session.beginTransaction();
            session.saveOrUpdate(movieDTO);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void upsertRatings(List<Rating> ratings){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Rating rating : ratings) {
                RatingDTO ratingDTO = ratingToRatingDTO(rating);
                // Have no idea why cascade is not working
                MovieDTO movieDTO = ratingDTO.getId().getMovie();
                session.saveOrUpdate(movieDTO);
                session.saveOrUpdate(ratingDTO);
            }
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void upsertRating(Rating rating){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            RatingDTO ratingDTO = ratingToRatingDTO(rating);
            transaction = session.beginTransaction();
            session.saveOrUpdate(ratingDTO);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Rating> loadTop(Integer countTOP, Calendar date) {
        List<Rating> result = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            //transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RatingDTO> cr = cb.createQuery(RatingDTO.class);
            Root<RatingDTO> root = cr.from(RatingDTO.class);

            cr.select(root).where(
                    cb.equal(root.get("id").get("date"), date));
            cr.orderBy(cb.desc(root.get("rating")));
            Query<RatingDTO> query = session.createQuery(cr);
            query.setMaxResults(countTOP);
            List<RatingDTO> ratingDTOs = query.getResultList();
            return query.getResultStream().map(this::ratingDTOToRating).collect(Collectors.toList());
            //transaction.commit();
        } catch (Exception ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
            if (transaction != null)
                transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    private MovieDTO movieToMovieDTO(Movie movie){
        return new MovieDTO(movie);
    }
    private Movie movieDTOToMovie(MovieDTO movieDTO){
        return new Movie(
                movieDTO.getId(),
                movieDTO.getName(),
                movieDTO.getYear());
    }
    private RatingDTO ratingToRatingDTO(Rating rating) {
        rating.setDate(getDate(rating.getDate()));
        return new RatingDTO(rating);
    }
    private Rating ratingDTOToRating(RatingDTO ratingDTO){
        return new Rating(
                ratingDTO.getId().getDate(),
                ratingDTO.getRating(),
                ratingDTO.getVotes(),
                movieDTOToMovie(ratingDTO.getId().getMovie()));
    }
}

