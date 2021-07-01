package com.example.lms.util;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    public static Long calendarToLong(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTimeInMillis();

        //todo reverse
//        calendar.timeInMillis = longTYpeVariable
//        val year1 = calendar.get(Calendar.YEAR)
//        val month1 = calendar.get(Calendar.MONTH)
//        val day1 = calendar.get(Calendar.DAY_OF_MONTH)
    }


}