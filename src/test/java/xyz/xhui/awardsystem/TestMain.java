package xyz.xhui.awardsystem;

import org.junit.jupiter.api.Test;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;

import java.math.BigDecimal;

public class TestMain {
    //    @Test
//    public void test1() {
//        Integer integer = Integer.valueOf(" 123 ");
//        System.out.println(integer);
//    }
//
//    @Test
//    public void test2() throws EntityFieldException {
//        Long timeMillis = MyTimeUtils.getTimeMillis("2019-03-13 13:54:00");
//        System.out.println(timeMillis);
//    }
    @Test
    public void test3() {
        System.out.println(BigDecimal.valueOf(0.99).doubleValue());
    }

    @Test
    public void test4() {
        System.out.println(0.999 / 100);
    }
}
