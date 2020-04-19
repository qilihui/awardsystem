package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;

import java.util.List;
import java.util.Optional;

public interface UserUnionService {
    /**
     * 查询所有辅导员详细信息
     * @return
     */
    List<SysUserUnion> findAll();

    /**
     * 添加辅导员
     * @param userUnion
     * @return
     */
    SysUserUnion save(SysUserUnion userUnion) throws EntityFieldException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysUserUnion> findById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Boolean deleteBySysUserId(Integer id) throws EntityFieldException;

    Optional<SysUserUnion> findBySysUserId(Integer id);

    Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException;
}
