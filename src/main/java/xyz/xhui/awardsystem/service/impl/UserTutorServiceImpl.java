package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.dao.GradeDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.TutorAddVo;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserTutorService;

import java.util.Optional;

@Service
@Slf4j
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

    @Override
    @Transactional
    public Integer saves(TutorAddVo[] addVos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < addVos.length; i++) {
            try {
                log.info(addVos[i].toString());
                SysDept dept = deptDao.findSysDeptByName(addVos[i].getDeptName()).orElseThrow(() -> {
                            return new EntityFieldException("系部不存在");
                        }
                );
                SysGrade grade = gradeDao.findSysGradeByName(Integer.valueOf(addVos[i].getGradeName().trim())).orElseThrow(() -> {
                            return new EntityFieldException("年级不存在");
                        }
                );
                SysUser sysUser = new SysUser();
                sysUser.setUsername(addVos[i].getUsername().trim());
                sysUser.setRole(RoleEnum.ROLE_TUTOR);
                sysUser.setEmail(addVos[i].getEmail().trim());
                sysUser.setRealName(addVos[i].getRealName().trim());
                if (userService.save(sysUser) <= 0) {
                    throw new EntityFieldException("未知错误");
                }
                SysUserTutor userTutor = new SysUserTutor();
                userTutor.setUser(sysUser);
                userTutor.setDeptId(dept.getId());
                userTutor.setGradeId(grade.getId());
                j += userTutorDao.save(userTutor);
            } catch (EntityFieldException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " " + e.getMessage());
            } catch (NumberFormatException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " 格式错误" + e.getMessage());
            }
        }
        return j;
    }
}
