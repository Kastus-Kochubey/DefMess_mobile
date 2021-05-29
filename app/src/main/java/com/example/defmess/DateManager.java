package com.example.defmess;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:m:s");

    public DateManager(String dateFormat) {
        this.dateFormat = new SimpleDateFormat(dateFormat);
    }

    public Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    @NotNull
    public String toString(Date date) {
        return dateFormat.format(date);
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
