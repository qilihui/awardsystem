package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.*;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.List;
import java.util.Optional;

public interface UserStuService {
//    List<SysUserStu> findAll();

//    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

//    Optional<SysUserStu> findById(Integer id);

    Integer save(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

    Integer saves(StuDto[] stuDtos) throws EntityFieldException;

    Optional<SysUserStu> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

    SysUserStu findSysUserStuByUsername(String username) throws EntityFieldException;

    SysUserStu findSysUserStuByUsernameToAdmin(String username) throws EntityFieldException;

    PageDto<List<StuDto>> findByHouseparent(Integer pageNum, Integer pageSize) throws UnknownException;

    List<ApartmentScoreDto> findByHouseparentToExcel() throws UnknownException;

    PageDto<List<StuDto>> findByTutor(Integer pageNum, Integer pageSize) throws UnknownException;
}
