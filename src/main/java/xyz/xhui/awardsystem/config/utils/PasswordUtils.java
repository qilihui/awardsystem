package xyz.xhui.awardsystem.config.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.xhui.awardsystem.model.entity.*;

public class PasswordUtils {
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static Boolean matches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
    public static void hiddenPassword(SysUser user) {
        user.setPassword("******");
    }

    public static void hiddenPassword(SysUserAdmin userAdmin) {
        userAdmin.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserStu userStu) {
        userStu.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserTutor userTutor) {
        userTutor.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserUnion userUnion) {
        userUnion.getUser().setPassword("******");
    }

    public static void hiddenPassword(SysUserHouseparent userHouseparent) {
        userHouseparent.getUser().setPassword("******");
    }
}
