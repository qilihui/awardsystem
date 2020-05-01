package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.PasswordErrorException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.LoginUser;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.service.UserService;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUser> findAll(Integer pagenum, Integer pagesize) {
        return userDao.findAllByPagenumAndPagesize(pagenum, pagesize);
    }

//    @Override
//    public List<SysUser> findAll() {
//        return userMybatisDao.findAll();
//    }

    @Override
    public Optional<SysUser> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<SysUser> findByUsernameEquals(String username) {
        return userDao.findSysUserByUsernameEquals(username);
    }

    @Override
    @Transactional
    public Integer save(SysUser sysUser) throws EntityFieldException {
        sysUser.setId(null);
        if (this.findByUsernameEquals(sysUser.getUsername()).isPresent()) {
            throw new EntityFieldException("用户名已经存在");
        }
        sysUser.setPassword(PasswordUtils.encode(sysUser.getUsername()));
        return userDao.save(sysUser);
    }

//    @Override
//    public Boolean deleteById(Integer id) {
//        if (userMybatisDao.deleteById(id) > 0) {
//            return true;
//        }
//        return false;
//    }

    @Override
    public void changePassword(Integer userId) throws EntityFieldException {
        Optional<SysUser> optionalSysUser = this.findById(userId);
        optionalSysUser.orElseThrow(() -> {
            return new EntityFieldException("用户不存在");
        });
        userDao.updatePassword(userId, PasswordUtils.encode(optionalSysUser.get().getUsername()));
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) throws PasswordErrorException {
        Integer userId = MyUserUtils.getId();
        String password = userDao.selectPassword(userId);
        log.info(password);
        if (!PasswordUtils.matches(oldPassword, password)) {
            throw new PasswordErrorException("旧密码错误");
        }
        userDao.updatePassword(userId, PasswordUtils.encode(newPassword));
    }

    @Override
    public Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException {
        if (userDto.getId() == null) {
            throw new EntityFieldException("缺少id字段");
        }
        return userDao.updateEmailAndRealName(userDto);
    }

    @Override
    @Transactional
    public Integer deleteUsers(Integer[] ids) throws EntityFieldException {
        Integer retCount = 0;
        for (Integer id : ids) {
            log.info(id.toString());
            Optional<SysUser> sysUserOptional = this.findById(id);
            sysUserOptional.orElseThrow(() -> {
                return new EntityFieldException("id: " + id + " 不存在");
            });
            SysUser sysUser = sysUserOptional.get();
            if ("admin".equals(sysUser.getUsername())) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("id: " + id + " 不能删除admin用户");
            }
            if (MyUserUtils.getId().equals(sysUser.getId())) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("id: " + id + " 不能删除当前登录用户");
            }
            retCount += userDao.deleteById(id);
        }
        log.info(retCount.toString());
        return retCount;
    }

    @Override
    public Integer getCount(RoleEnum roleEnum) {
        return userDao.countAllByRole(roleEnum);
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(userDao.count());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> sysUserOptional = this.findByUsernameEquals(username);
        sysUserOptional.orElseThrow(() -> {
            return new UsernameNotFoundException(username);
        });
        return new LoginUser(sysUserOptional.get());
    }
}
