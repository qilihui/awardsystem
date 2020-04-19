package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;

import java.util.List;
import java.util.Optional;

public interface UserHouseparentService {
    List<SysUserHouseparent> findAll();

    SysUserHouseparent save(SysUserHouseparent userHouseparent) throws EntityFieldException;

    Optional<SysUserHouseparent> findById(Integer id);

    Boolean deleteById(Integer id);

    Optional<SysUserHouseparent> findBySysUserId(Integer id);
}
