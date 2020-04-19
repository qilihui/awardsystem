package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.dao.mybatis.UserUnionMybatisDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserUnionService;

import java.util.List;
import java.util.Optional;

@Service
public class UserUnionServiceImpl implements UserUnionService {
    @Autowired
    private UserUnionDao userUnionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserUnionMybatisDao userUnionMybatisDao;

    @Override
    public List<SysUserUnion> findAll() {
        return userUnionDao.findAll();
    }

    @Override
    public SysUserUnion save(SysUserUnion userUnion) throws EntityFieldException {
        userUnion.getUser().setRole(RoleEnum.ROLE_UNION);
        userService.save(userUnion.getUser());
        userUnion.setId(null);
        SysDept sysDept = deptDao.findById(userUnion.getDept().getId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userUnion.getDept().getId() + "不存在")
        );
        userUnion.setDept(sysDept);
        return userUnionDao.save(userUnion);
    }

    @Override
    public Optional<SysUserUnion> findById(Integer id) {
        return userUnionDao.findById(id);
    }

    @Override
    @Transactional
    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
        Optional<SysUserUnion> retUserUnion = this.findBySysUserId(id);
        if (retUserUnion.isEmpty()) {
            throw new EntityFieldException("用户不存在");
        }
        SysUserUnion sysUserUnion = retUserUnion.get();
        userUnionDao.delete(sysUserUnion);
        userDao.delete(sysUserUnion.getUser());
        return true;
    }

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
        if (userInfoDto.getUserInfoId() == null || "".equals(userInfoDto.getUserInfoId())) {
            throw new EntityFieldException("userInfoId字段缺失");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userUnionMybatisDao.updateInfo(userInfoDto);
        return integer + integer1;
    }
}
