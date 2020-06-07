package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.vo.TutorAddVo;

import java.util.List;
import java.util.Optional;

public interface UserTutorService {
//    List<SysUserTutor> findAll();

    Integer save(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

//    Optional<SysUserTutor> findById(Integer id);

//    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

    Optional<SysUserTutor> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

    Integer saves(TutorAddVo[] addVos) throws EntityFieldException;
}
