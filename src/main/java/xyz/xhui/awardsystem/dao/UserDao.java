package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.model.entity.SysUser;

import java.util.Optional;

public interface UserDao extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {
    Optional<SysUser> findSysUserByUsernameEquals(String username);
    SysUser findSysUserByUsernameEqualsAndRoleEquals(String username, RoleEnum role);
    Integer countAllByRole(RoleEnum role);
}
