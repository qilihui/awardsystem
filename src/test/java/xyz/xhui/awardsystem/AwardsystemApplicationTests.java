package xyz.xhui.awardsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class AwardsystemApplicationTests {

    @Test
    void contextLoads() {
        SecurityContextHolder.getContext();
    }

    @Test
    public void test1() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try {
            map = objectMapper.readValue(this.getClass().getResourceAsStream("/permision/ROLE_ADMIN.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
