package xyz.xhui.awardsystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.PasswordErrorException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    /**
     * 分页查询所有用户
     *
     * @return
     */
    List<SysUser> findAll(Integer pagenum, Integer pagesize);

//    List<SysUser> findAll();

    Optional<SysUser> findById(Integer id);

    Optional<SysUser> findByUsernameEquals(String username);

    Integer save(SysUser sysUser) throws EntityFieldException;

//    Boolean deleteById(Integer id);

    void changePassword(Integer userId) throws EntityFieldException;

    void changePassword(String oldPassword, String newPassword) throws PasswordErrorException;

    Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException;

    Integer deleteUsers(Integer[] ids) throws EntityFieldException;

    Integer getCount(RoleEnum roleEnum);

    Integer getCount();
}