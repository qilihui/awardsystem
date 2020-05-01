package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserStuService;

import java.util.Optional;

@Service
public class UserStuServiceImpl implements UserStuService {
    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserService userService;


//    @Override
//    public List<SysUserStu> findAll() {
//        return userStuDao.findAll();
//    }

//    @Override
//    public Optional<SysUserStu> findById(Integer id) {
//        return userStuDao.findById(id);
//    }

    @Override
    @Transactional
    public Integer save(UserInfoDto userInfoDto, SysUserDto sysUserDto) throws EntityFieldException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername());
        sysUser.setPassword(PasswordUtils.encode(sysUserDto.getUsername()));
        sysUser.setEmail(sysUserDto.getEmail());
        sysUser.setRealName(sysUserDto.getRealName());
        sysUser.setRole(RoleEnum.ROLE_STU);

        SysUserStu userStu = new SysUserStu();
        deptDao.findById(userInfoDto.getDeptId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userInfoDto.getDeptId() + "不存在")
        );
        userStu.setDeptId(userInfoDto.getDeptId());
        gradeDao.findById(userInfoDto.getGradeId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userInfoDto.getGradeId() + "不存在")
        );
        userStu.setGradeId(userInfoDto.getGradeId());
        apartmentDao.findById(userInfoDto.getApartmentId()).orElseThrow(
                () -> new EntityFieldException("公寓id:" + userInfoDto.getApartmentId() + "不存在")
        );
        userStu.setApartmentId(userInfoDto.getApartmentId());
        userStu.setRoom(userInfoDto.getRoom());
        userStu.setBed(userInfoDto.getBed());
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        userStu.setUser(sysUser);
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
        if (userInfoDto.getUserInfoId() == null) {
            throw new EntityFieldException("缺少userInfoId字段");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userStuDao.updateInfo(userInfoDto);
        return integer + integer1;
    }

//    @Override
//    @Transactional
//    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
//        Optional<SysUserStu> retUserStu = this.findBySysUserId(id);
//        retUserStu.orElseThrow(() -> {
//            return new EntityFieldException("用户不存在");
//        });
//        SysUserStu userStu = retUserStu.get();
//        userStuDao.deleteById(userStu.getId());
////        userStuDao.delete(userStu);
////        userDao.delete(userStu.getUser());
//        return true;
//    }
}
