package ru.bikert.task_test.importer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class PageDownloader{

    final String url = "https://www.kinopoisk.ru/top/day/";
    final String userAgent = "Chrome/4.0.249.0 Safari/532.5";
    final String referrer = "http://www.google.com";
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Document getDocument(Calendar calendar) {
        try {
            Document document = Jsoup.connect(url + dateFormat.format(calendar.getTime())+ "/")
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
