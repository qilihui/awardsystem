package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.dao.mybatis.UserStuMybatisDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysApartment;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.model.entity.SysGrade;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserStuService;

import java.util.List;
import java.util.Optional;

@Service
public class UserStuServiceImpl implements UserStuService {
    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserStuMybatisDao userStuMybatisDao;

    @Override
    public List<SysUserStu> findAll() {
        return userStuDao.findAll();
    }

    @Override
    public Optional<SysUserStu> findById(Integer id) {
        return userStuDao.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserStu save(SysUserStu userStu) throws EntityFieldException {
        userStu.getUser().setRole(RoleEnum.ROLE_STU);
        userService.save(userStu.getUser());
        userStu.setId(null);
        SysDept sysDept = deptDao.findById(userStu.getDept().getId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userStu.getDept().getId() + "不存在")
        );
        userStu.setDept(sysDept);
        SysGrade sysGrade = gradeDao.findById(userStu.getGrade().getId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userStu.getDept().getId() + "不存在")
        );
        userStu.setGrade(sysGrade);
        SysApartment sysApartment = apartmentDao.findById(userStu.getApartment().getId()).orElseThrow(
                () -> new EntityFieldException("公寓id:" + userStu.getDept().getId() + "不存在")
        );
        userStu.setApartment(sysApartment);
        return userStuDao.save(userStu);
    }

    @Override
    public Optional<SysUserStu> findBySysUserId(Integer id) {
        return userStuDao.findSysUserStuByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_STU.toString())) {
            throw new EntityFieldException("角色错误");
        }
        if (userInfoDto.getUserInfoId() == null || "".equals(userInfoDto.getUserInfoId())) {
            throw new EntityFieldException("缺少userInfoId字段");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userStuMybatisDao.updateInfo(userInfoDto);
        return integer + integer1;
    }

    @Override
    @Transactional
    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
        Optional<SysUserStu> retUserStu = this.findBySysUserId(id);
        retUserStu.orElseThrow(() -> {
            return new EntityFieldException("用户不存在");
        });
        SysUserStu userStu = retUserStu.get();
        userStuDao.deleteById(userStu.getId());
//        userStuDao.delete(userStu);
//        userDao.delete(userStu.getUser());
        return true;
    }
}
