package com.example.defmess;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd h:m:s");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat timeFormat = new SimpleDateFormat("H:m:s");

    public DateUtils(String dateFormat) {
        this.dateTimeFormat = new SimpleDateFormat(dateFormat);
    }

    public DateUtils() {

    }

    public Date parseDate(String date) throws ParseException {
        return dateTimeFormat.parse(date);
    }

    @NotNull
    public String toDateTimeString(Date date) {
        return dateTimeFormat.format(date);
    }

    public String toDateString(Date date){
        return dateFormat.format(date);
    }
    public String toTimeString(Date date){
        return timeFormat.format(date);
    }

    public Boolean beforeNow(Date date) {
        return date.before(new Date());
    }

    public Boolean afterNow(Date date) {
        return date.after(new Date());
    }

    public String howManyHours(Date date){
        if (afterNow(date)){
            return (date.getTime() - new Date().getTime()) + "hours ago";
        } else {
            return (new Date().getTime() - date.getTime()) + "hours left";
        }
    }
}
