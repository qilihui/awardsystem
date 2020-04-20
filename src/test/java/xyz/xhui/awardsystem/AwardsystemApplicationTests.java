package xyz.xhui.awardsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.xhui.awardsystem.dao.UserDao;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class AwardsystemApplicationTests {

    @Autowired
    private UserDao userDao;

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

    @Test
    public void test2() {
        userDao.deleteById(10);
    }
}
