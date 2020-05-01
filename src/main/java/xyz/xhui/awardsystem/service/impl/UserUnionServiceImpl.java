package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserUnionService;

import java.util.Optional;

@Service
public class UserUnionServiceImpl implements UserUnionService {
    @Autowired
    private UserUnionDao userUnionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptDao deptDao;

//    @Override
//    public List<SysUserUnion> findAll() {
//        return userUnionDao.findAll();
//    }

    @Override
    @Transactional
    public Integer save(UserInfoDto userInfoDto, SysUserDto sysUserDto) throws EntityFieldException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername());
        sysUser.setPassword(PasswordUtils.encode(sysUserDto.getUsername()));
        sysUser.setEmail(sysUserDto.getEmail());
        sysUser.setRealName(sysUserDto.getRealName());
        sysUser.setRole(RoleEnum.ROLE_UNION);

        SysUserUnion userUnion = new SysUserUnion();
        deptDao.findById(userInfoDto.getDeptId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userInfoDto.getDeptId() + "不存在")
        );
        userUnion.setDeptId(userInfoDto.getDeptId());
        userUnion.setUser(sysUser);
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        return userUnionDao.save(userUnion);
    }

//    @Override
//    public Optional<SysUserUnion> findById(Integer id) {
//        return userUnionDao.findById(id);
//    }

//    @Override
//    @Transactional
//    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
//        Optional<SysUserUnion> retUserUnion = this.findBySysUserId(id);
//        retUserUnion.orElseThrow(() -> {
//            return new EntityFieldException("用户不存在");
//        });
//        SysUserUnion sysUserUnion = retUserUnion.get();
//        userUnionDao.deleteById(sysUserUnion.getId());
//        //        userUnionDao.delete(sysUserUnion);
////        userDao.delete(sysUserUnion.getUser());
//        return true;
//    }

    @Override
    public Optional<SysUserUnion> findBySysUserId(Integer id) {
        return userUnionDao.findSysUserUnionByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_UNION.toString())) {
            throw new EntityFieldException("角色错误");
        }
        if (userInfoDto.getUserInfoId() == null) {
            throw new EntityFieldException("userInfoId字段缺失");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userUnionDao.updateInfo(userInfoDto);
        return integer + integer1;
    }
}
