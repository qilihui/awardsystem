package xyz.xhui.awardsystem.config.utils;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeUtils {
    public static Integer getTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Long getTimeMillis(String time) throws EntityFieldException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            throw new EntityFieldException(e.getMessage());
        }
        return date.getTime();
    }
}
