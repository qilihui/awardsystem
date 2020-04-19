package xyz.xhui.awardsystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.PasswordErrorException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.dao.mybatis.UserMybatisDao;
import xyz.xhui.awardsystem.model.dto.LoginUser;
import xyz.xhui.awardsystem.model.dto.SysPermision;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMybatisDao userMybatisDao;

    @Override
    public Page<SysUser> findAll(Integer pagenum, Integer pagesize) {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        return userDao.findAll(pageable);
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<SysUser> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public SysUser findByUsernameEquals(String username) {
        return userDao.findSysUserByUsernameEquals(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser save(SysUser sysUser) throws EntityFieldException {
        sysUser.setId(null);
        if (this.findByUsernameEquals(sysUser.getUsername()) != null) {
            throw new EntityFieldException("用户名已经存在");
        }
        sysUser.setPassword(PasswordUtils.encode(sysUser.getPassword()));
        return userDao.save(sysUser);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            userDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public void changePassword(Integer userId) throws EntityFieldException {
        Optional<SysUser> optionalSysUser = this.findById(userId);
        SysUser sysUser;
        if (optionalSysUser.isPresent()) {
            sysUser = optionalSysUser.get();
        } else {
            throw new EntityFieldException("用户不存在");
        }
        sysUser.setPassword(PasswordUtils.encode(sysUser.getUsername()));
        userDao.save(sysUser);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws PasswordErrorException {
        Integer userId = MyUserUtils.getId();
        String password = userMybatisDao.selectPassword(userId);
        log.info(password);
        if (!PasswordUtils.matches(oldPassword, password)) {
            throw new PasswordErrorException("旧密码错误");
        }
        userMybatisDao.updatePassword(userId, PasswordUtils.encode(newPassword));
    }

    @Override
    public Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException {
        if (userDto.getId() == null || "".equals(userDto.getId())) {
            throw new EntityFieldException("缺少id字段");
        }
        return userMybatisDao.updateEmailAndRealName(userDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.findByUsernameEquals(username);
        if (sysUser == null)
            throw new UsernameNotFoundException(username);
        LoginUser loginUser = new LoginUser(sysUser);
        return loginUser;
    }

}
