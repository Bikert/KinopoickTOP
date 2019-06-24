import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class PageDownloader{

    final Logger logger = Logger.getLogger(PageDownloader.class);
    final String url = "https://www.kinopoisk.ru/top/day/";
    final String userAgent = "Chrome/4.0.249.0 Safari/532.5";
    final String referrer = "http://www.google.com";
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Document getDocument(Calendar calendar) {
        logger.trace("Get Page Start");
        try {
            Document document = Jsoup.connect(url + dateFormat.format(calendar.getTime())+ "/")
                    .userAgent(userAgent)
                    .referrer(referrer)
                    .get();
            logger.trace(document);
            return document;
        } catch (IOException e) {
            logger.trace(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
