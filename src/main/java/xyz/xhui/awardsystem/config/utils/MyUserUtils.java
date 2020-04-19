package xyz.xhui.awardsystem.config.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.model.dto.LoginUser;
import xyz.xhui.awardsystem.model.entity.SysUser;

public class MyUserUtils {

    public static SysUser getSysUser() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getSysUser();
    }

    public static Integer getId() {
        return getSysUser().getId();
    }

    public static String getUsername() {
        return getSysUser().getUsername();
    }

    public static RoleEnum getRoleEnum() {
        return getSysUser().getRole();
    }
}
