package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.UserAdminDao;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;
import xyz.xhui.awardsystem.service.UserAdminService;
import xyz.xhui.awardsystem.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    private UserAdminDao userAdminDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserAdmin> findAll() {
        return userAdminDao.findAll();
    }

    @Override
    public Optional<SysUserAdmin> findById(Integer id) {
        return userAdminDao.findById(id);
    }

    @Override
    public Optional<SysUserAdmin> findBySysUserId(Integer id) {
        return userAdminDao.findSysUserAdminByUser_Id(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserAdmin save(SysUserAdmin userAdmin) throws EntityFieldException {
        userAdmin.getUser().setRole(RoleEnum.ROLE_ADMIN);
        userService.save(userAdmin.getUser());
        userAdmin.setId(null);
        return userAdminDao.save(userAdmin);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<SysUserAdmin> retUserAdmin = this.findById(id);
        retUserAdmin.ifPresent(userAdmin -> {
            userAdminDao.delete(userAdmin);
            userDao.delete(userAdmin.getUser());
        });
        return retUserAdmin.isPresent();
    }

    @Override
    public Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_ADMIN.toString())) {
            throw new EntityFieldException("角色错误");
        }
        return userService.updateEmailAndRealName(userDto);
    }
}