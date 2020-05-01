package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;

import java.util.List;
import java.util.Optional;

public interface UserHouseparentService {
//    List<SysUserHouseparent> findAll();

    Integer save(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

//    Optional<SysUserHouseparent> findById(Integer id);

//    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

    Optional<SysUserHouseparent> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;
}
