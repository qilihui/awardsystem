package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.UserAdminDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;
import xyz.xhui.awardsystem.service.UserAdminService;
import xyz.xhui.awardsystem.service.UserService;

import java.util.Optional;

@Service
@Slf4j
public class UserAdminServiceImpl implements UserAdminService {
    @Autowired
    private UserAdminDao userAdminDao;

    @Autowired
    private UserService userService;

//    @Override
//    public List<SysUserAdmin> findAll() {
//        return userAdminDao.findAll();
//    }

//    @Override
//    public Optional<SysUserAdmin> findById(Integer id) {
//        return userAdminDao.findById(id);
//    }

    @Override
    public Optional<SysUserAdmin> findBySysUserId(Integer id) {
        return userAdminDao.findSysUserAdminByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer save(SysUserDto sysUserDto) throws EntityFieldException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername());
        sysUser.setPassword(PasswordUtils.encode(sysUserDto.getUsername()));
        sysUser.setEmail(sysUserDto.getEmail());
        sysUser.setRealName(sysUserDto.getRealName());
        sysUser.setRole(RoleEnum.ROLE_ADMIN);
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        log.info(sysUser.getId().toString());
        return userAdminDao.save(sysUser.getId());
    }

//    @Override
//    @Transactional
//    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
////        Optional<SysUserAdmin> retUserAdmin = this.findById(id);
//        Optional<SysUserAdmin> retUserAdmin = this.findBySysUserId(id);
//        retUserAdmin.orElseThrow(() -> {
//            return new EntityFieldException("用户不存在");
//        });
//        SysUserAdmin userAdmin = retUserAdmin.get();
//        if ("admin".equals(userAdmin.getUser().getUsername())) {
//            throw new EntityFieldException("不能删除admin用户");
//        }
//        userAdminDao.deleteById(userAdmin.getId());
////        userAdminDao.delete(userAdmin);
////        userDao.delete(userAdmin.getUser());
//        return true;
//    }

    @Override
    public Integer updateEmailAndRealName(SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_ADMIN.toString())) {
            throw new EntityFieldException("角色错误");
        }
        return userService.updateEmailAndRealName(userDto);
    }
}
