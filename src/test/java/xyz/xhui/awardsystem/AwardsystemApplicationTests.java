package xyz.xhui.awardsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.entity.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class AwardsystemApplicationTests {

    @Autowired
    private UserDao userMybatisDao;
    @Autowired
    private UserAdminDao userAdminMybatisDao;
    @Autowired
    private UserHouseparentDao userHouseparentMybatisDao;
    @Autowired
    private UnionScoreDao unionScoreMybatisDao;

//    @Test
//    void contextLoads() {
//        SecurityContextHolder.getContext();
//    }
//
//    @Test
//    public void test1() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map map = null;
//        try {
//            map = objectMapper.readValue(this.getClass().getResourceAsStream("/permision/ROLE_ADMIN.json"), Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(map);
//    }
//
//    @Test
//    public void test2() {
//        List<SysUser> all = userMybatisDao.findAllByPagenumAndPagesize(1, 3);
//        for (SysUser user : all) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void test3() {
//        List<SysUser> all = userMybatisDao.findAll();
//        for (SysUser user : all) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void test5() {
//        Optional<SysUser> byId = userMybatisDao.findById(6);
//        System.out.println(byId.get());
//    }
//
//    @Test
//    public void test6() {
//        Optional<SysUser> byId = userMybatisDao.findSysUserByUsernameEquals("admin");
//        System.out.println(byId.get());
//    }
//
//    @Test
//    public void test7() {
//        System.out.println(userMybatisDao.deleteById(18));
//    }
//
//
//    @Test
//    public void test8() {
//        System.out.println(userMybatisDao.findAllCount());
//        System.out.println(userMybatisDao.findAllCountByRole(null));
//        System.out.println(userMybatisDao.findAllCountByRole(RoleEnum.ROLE_STU));
//    }
//
//    @Test
//    public void test4() {
//        List<SysUserAdmin> all = userAdminMybatisDao.findAll();
//        for (SysUserAdmin userAdmin : all) {
//            System.out.println(userAdmin.toString());
//        }
//    }
//
//    @Test
//    public void test9() {
//        Optional<SysUserAdmin> all = userAdminMybatisDao.findById(3);
//        System.out.println(all.get());
//    }
//
//    @Test
//    public void test10() {
//        Optional<SysUserAdmin> all = userAdminMybatisDao.findSysUserAdminByUser_Id(7);
//        System.out.println(all.get());
//    }
//
//    @Test
//    public void test13() {
//        List<SysUserHouseparent> all = userHouseparentMybatisDao.findAll();
//        for (SysUserHouseparent userAdmin : all) {
//            System.out.println(userAdmin.toString());
//        }
//    }
//
//    @Test
//    public void test11() {
//        Optional<SysUserHouseparent> all = userHouseparentMybatisDao.findById(3);
//        System.out.println(all.get());
//    }
//
//    @Test
//    public void test12() {
//        Optional<SysUserHouseparent> all = userHouseparentMybatisDao.findSysUserHouseparentByUser_Id(14);
//        System.out.println(all.get());
//    }
//
//    @Test
//    public void test15() {
//        List<UnionScore> allByPagenumAndPagesize = unionScoreMybatisDao.findAllByPagenumAndPagesize(0, 3);
//        System.out.println(allByPagenumAndPagesize.toString());
//    }
//
//    @Test
//    @Transactional
//    public void test16() {
//        SysUser user = new SysUser();
//        user.setUsername("zzzz");
//        user.setPassword("aaaaa");
//        user.setRole(RoleEnum.ROLE_ADMIN);
//        user.setRealName("aaaa");
//        user.setEmail("aaaa");
//        Integer save = userMybatisDao.save(user);
//        System.out.println(user.getId());
//        System.out.println(save);
//        throw new RuntimeException();
//    }
//
//    @Autowired
//    private UserStuDao userStuDao;
//
//    @Test
//    public void test17() {
//        Optional<SysUser> sysUserByStuId = userStuDao.findSysUserByStuId(1);
//        System.out.println(sysUserByStuId.get());
//    }
//
//    @Test
//    public void test18() {
//        Optional<SysUserStu> sysUserByStuId = userStuDao.findStuByUsername("1730090712");
//        System.out.println(sysUserByStuId.get());
//    }
//
//    @Autowired
//    private ApartmentScoreDao apartmentScoreDao;
//
//    @Test
//    public void test19() {
//        List<ApartmentScore> allByPagenumAndPagesize = apartmentScoreDao.findAllByPagenumAndPagesize(1, 0, 2, null);
//        System.out.println(allByPagenumAndPagesize);
//    }
}
