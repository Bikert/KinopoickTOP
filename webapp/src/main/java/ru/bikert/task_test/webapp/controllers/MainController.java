package ru.bikert.task_test.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import ru.bikert.test_task.abstractions.MovieLoader;
import ru.bikert.test_task.abstractions.models.Movie;
import ru.bikert.test_task.abstractions.models.Rating;
import ru.bikert.test_task.database.helpers.DateHelpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RestController
public class MainController {

    private static final Integer DEFAULT_LIMIT = 25;
    private final MovieLoader movieLoader;

    public MainController(MovieLoader movieLoader) {
        this.movieLoader = movieLoader;
    }

    @RequestMapping(
            value = "/top/{stringDate}",
            method = RequestMethod.GET
    )
    public List<Rating> fetchUsers(@PathVariable String stringDate, @RequestParam(name = "limit", required = false) Integer limit) {
        if (limit == null) limit = DEFAULT_LIMIT;
        Calendar date = parseDate(stringDate);
        if (date == null)
            date = DateHelpers.getCurrentDate();

        List<Rating> res = movieLoader.loadTop(date, limit);
        return res;
    }

    @RequestMapping(
            value = "/top",
            method = RequestMethod.GET
    )
    public List<Rating> fetchUsers(@RequestParam(name = "limit", required = false) Integer limit) {
        return fetchUsers(null, limit);
    }


//    @RequestMapping("/")
//    public ModelAndView index () {
//        ModelAndView modelAndView = new ModelAndView("main");
//        modelAndView.addObject("ratings", fetchUsers(null, 10));
//        return modelAndView;
//    }

    private static Calendar parseDate(String stringDate) {
        if (stringDate == null) return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = df.parse(stringDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            return null;
        }
    }

}
