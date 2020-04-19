package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;

import java.util.List;
import java.util.Optional;

public interface UserAdminService {
    List<SysUserAdmin> findAll();

    Optional<SysUserAdmin> findById(Integer id);

    Optional<SysUserAdmin> findBySysUserId(Integer id);

    SysUserAdmin save(SysUserAdmin userAdmin) throws EntityFieldException;

    Boolean deleteById(Integer id);

    Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException;
}
