package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;

import java.util.Optional;

public interface UserTutorDao extends JpaRepository<SysUserTutor, Integer> {
    Optional<SysUserTutor> findSysUserTutorByUser_Id(Integer sysUserId);
    Integer deleteSysUserTutorByUser_Id(Integer id);
}
