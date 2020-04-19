package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;

import java.util.Optional;

public interface UserAdminDao  extends JpaRepository<SysUserAdmin, Integer> {
    Optional<SysUserAdmin> findSysUserAdminByUser_Id(Integer sysUserId);
}
