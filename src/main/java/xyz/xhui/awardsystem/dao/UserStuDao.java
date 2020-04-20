package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.Optional;

public interface UserStuDao extends JpaRepository<SysUserStu, Integer> {
    Optional<SysUserStu> findSysUserStuByUser_Id(Integer user_id);
    Integer deleteSysUserStuByUser_Id(Integer id);
}
