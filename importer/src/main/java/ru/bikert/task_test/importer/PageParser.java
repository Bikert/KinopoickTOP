package ru.bikert.task_test.importer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.bikert.test_task.abstractions.models.Movie;
import ru.bikert.test_task.abstractions.models.Rating;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PageParser {

    private final static Pattern nameAndYearPattern = Pattern.compile("(.*) \\((.*?)\\)$");
    private final static Pattern amountVotePattern = Pattern.compile("\\((.*?)\\)");
    private final static Pattern idPattern = Pattern.compile("([^\\/]*)\\/?$");

    Rating getRating() {
        Document document = new PageDownloader().getDocument(Calendar.getInstance());
        return getRating(document, 1);
    }

    List<Rating> getRatings(Calendar calendar, int count) {
        Document document = new PageDownloader().getDocument(calendar);
        return IntStream.rangeClosed(1, count).mapToObj(i -> getRating(document, i)).collect(Collectors.toList());
    }

    private Rating getRating(Document document, int position){

        String name = null;
        Integer year = null;
        Integer votes = null;
        Integer id = null;
        Double currentRating = null;

        Element row = document.selectFirst("#top250_place_" + position);
        Element detailsLink = row.selectFirst("a.all");

        //kinopoisk ID
        String link = detailsLink.attr("href");
        Matcher matcher = idPattern.matcher(link);
        if (matcher.find()) {
            id = Integer.parseInt(matcher.group(1));
        }

        //Movie Name and Year
        String nameAndYear = detailsLink.text();
        matcher = nameAndYearPattern.matcher(nameAndYear);
        if (matcher.find()) {
            name = matcher.group(1);
            year = Integer.parseInt(matcher.group(2));
        }

        Element voteLink = row.selectFirst("a.continue");
        String ratingString = voteLink.text();
        currentRating = Double.valueOf(ratingString);
        Element voteSpan = row.selectFirst("span:nth-child(2)");
        matcher = amountVotePattern.matcher(voteSpan.text());
        if (matcher.find()){
            votes = Integer.parseInt(matcher.group(1).replaceAll(" ", ""));
        }

        Movie movie = new Movie(id, name, year);

        Rating rating = new Rating(Calendar.getInstance(), currentRating, votes, movie);
        return rating;
    }
}
