package ru.bikert.test_task.connections.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelpers {

    public static Calendar getDate (Calendar dateTime) {
        return new GregorianCalendar(dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DATE));
    }

    public static Calendar getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return getDate(calendar);
    }
}
