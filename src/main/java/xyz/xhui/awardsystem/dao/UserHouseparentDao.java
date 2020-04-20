package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;

import java.util.Optional;

public interface UserHouseparentDao extends JpaRepository<SysUserHouseparent, Integer> {
//    SysUserHouseparent findSysUserHouseparentByUser_Id(Integer id);
    Optional<SysUserHouseparent> findSysUserHouseparentByUser_Id(Integer sysUserId);
    Integer deleteSysUserHouseparentByUser_Id(Integer id);
}
