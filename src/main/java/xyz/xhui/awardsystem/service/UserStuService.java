package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.List;
import java.util.Optional;

public interface UserStuService {
    /**
     * 查询所有学生详细信息
     * @return
     */
    List<SysUserStu> findAll();

    /**
     * 根据id删除学生
     * @param id
     * @return
     */
    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

    Optional<SysUserStu> findById(Integer id);

    SysUserStu save(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;

    Optional<SysUserStu> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;
}
