package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.model.entity.SysUser;

public interface UserDao extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {
    SysUser findSysUserByUsernameEquals(String username);
    SysUser findSysUserByUsernameEqualsAndRoleEquals(String username, RoleEnum role);
}
