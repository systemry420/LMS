package com.example.lms.util;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    public static Long calendarToLong(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }

    public static Date longToCalendar(long dateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        return calendar.getTime();
    }

    public static String getCurrentDate() {
        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM, dd, yyyy HH:mm",
                Locale.getDefault());
        return df.format(now);
    }


}