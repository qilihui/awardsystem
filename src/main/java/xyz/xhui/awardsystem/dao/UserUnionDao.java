package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;

import java.util.Optional;

public interface UserUnionDao extends JpaRepository<SysUserUnion, Integer> {
    Optional<SysUserUnion> findSysUserUnionByUser_Id(Integer sysUserId);
}
