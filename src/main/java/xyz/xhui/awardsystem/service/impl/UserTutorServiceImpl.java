package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.dao.GradeDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserTutorService;

import java.util.Optional;

@Service
public class UserTutorServiceImpl implements UserTutorService {
    @Autowired
    private UserTutorDao userTutorDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

//    @Override
//    public List<SysUserTutor> findAll() {
//        return userTutorDao.findAll();
//    }

    @Override
    @Transactional
    public Integer save(UserInfoDto userInfoDto, SysUserDto sysUserDto) throws EntityFieldException {

        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername());
        sysUser.setPassword(PasswordUtils.encode(sysUserDto.getUsername()));
        sysUser.setEmail(sysUserDto.getEmail());
        sysUser.setRealName(sysUserDto.getRealName());
        sysUser.setRole(RoleEnum.ROLE_TUTOR);

        SysUserTutor sysUserTutor = new SysUserTutor();
        deptDao.findById(userInfoDto.getDeptId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userInfoDto.getDeptId() + "不存在")
        );
        sysUserTutor.setDeptId(userInfoDto.getDeptId());
        gradeDao.findById(userInfoDto.getGradeId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userInfoDto.getGradeId() + "不存在")
        );
        sysUserTutor.setGradeId(userInfoDto.getGradeId());
        sysUserTutor.setUser(sysUser);
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        return userTutorDao.save(sysUserTutor);
    }

//    @Override
//    public Optional<SysUserTutor> findById(Integer id) {
//        return userTutorDao.findById(id);
//    }

//    @Override
//    @Transactional
//    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
//        Optional<SysUserTutor> retUserTutor = this.findBySysUserId(id);
//        retUserTutor.orElseThrow(() -> {
//            return new EntityFieldException("用户不存在");
//        });
//        SysUserTutor userTutor = retUserTutor.get();
//        userTutorDao.deleteById(userTutor.getId());
////        userTutorDao.delete(userTutor);
////        userDao.delete(userTutor.getUser());
//        return true;
//    }

    @Override
    public Optional<SysUserTutor> findBySysUserId(Integer id) {
        return userTutorDao.findSysUserTutorByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_TUTOR.toString())) {
            throw new EntityFieldException("角色错误");
        }
        if (userInfoDto.getUserInfoId() == null) {
            throw new EntityFieldException("userInfoId字段缺失");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = userTutorDao.updateInfo(userInfoDto);
        return integer + integer1;
    }
}
