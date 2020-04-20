package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.dao.GradeDao;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.dao.mybatis.UserTutorMybatisDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.model.entity.SysGrade;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserTutorService;

import java.util.List;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserTutorMybatisDao userTutorMybatisDao;

    @Override
    public List<SysUserTutor> findAll() {
        return userTutorDao.findAll();
    }

    @Override
    @Transactional
    public SysUserTutor save(SysUserTutor userTutor) throws EntityFieldException {
        userTutor.getUser().setRole(RoleEnum.ROLE_TUTOR);
        userService.save(userTutor.getUser());
        userTutor.setId(null);
        SysDept sysDept = deptDao.findById(userTutor.getDept().getId()).orElseThrow(
                () -> new EntityFieldException("系部id:" + userTutor.getDept().getId() + "不存在")
        );
        userTutor.setDept(sysDept);
        SysGrade sysGrade = gradeDao.findById(userTutor.getGrade().getId()).orElseThrow(
                () -> new EntityFieldException("年级id:" + userTutor.getDept().getId() + "不存在")
        );
        userTutor.setGrade(sysGrade);
        return userTutorDao.save(userTutor);
    }

    @Override
    public Optional<SysUserTutor> findById(Integer id) {
        return userTutorDao.findById(id);
    }

    @Override
    @Transactional
    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
        Optional<SysUserTutor> retUserTutor = this.findBySysUserId(id);
        retUserTutor.orElseThrow(() -> {
            return new EntityFieldException("用户不存在");
        });
        SysUserTutor userTutor = retUserTutor.get();
        userTutorDao.deleteById(userTutor.getId());
//        userTutorDao.delete(userTutor);
//        userDao.delete(userTutor.getUser());
        return true;
    }

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
        Integer integer1 = userTutorMybatisDao.updateInfo(userInfoDto);
        return integer + integer1;
    }
}
