package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
import xyz.xhui.awardsystem.model.vo.UnionAddVo;

import java.util.List;
import java.util.Optional;

public interface UserUnionService {
//    List<SysUserUnion> findAll();

    Integer save(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

//    Optional<SysUserUnion> findById(Integer id);

//    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

    Optional<SysUserUnion> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

    Integer saves(UnionAddVo[] addVos) throws EntityFieldException;
}
