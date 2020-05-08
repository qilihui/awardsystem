package xyz.xhui.awardsystem.config.utils;

public class MyTimeUtils {
    public static Integer getTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
