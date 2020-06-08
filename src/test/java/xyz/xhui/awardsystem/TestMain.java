package xyz.xhui.awardsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test5() throws JsonProcessingException {
        class A {
            private Integer a;

            public A() {
            }

            public A(Integer a) {
                this.a = a;
            }

            public Integer getA() {
                return a;
            }

            public void setA(Integer a) {
                this.a = a;
            }

            @Override
            public String toString() {
                return "a{" +
                        "a=" + a +
                        '}';
            }
        }
        List<A> aList = new ArrayList<>();
        for (int k = 1; k <= 6; k++) {
            for (int i = 1; i < 40; i++) {
                    aList.add(new A(k * 100 + i));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(aList);
        System.out.println(s);
    }
}
