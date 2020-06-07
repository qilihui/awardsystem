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
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.UnionAddVo;
import xyz.xhui.awardsystem.service.UserService;
import xyz.xhui.awardsystem.service.UserUnionService;

import java.util.Optional;

@Service
@Slf4j
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
        sysUser.setUsername(sysUserDto.getUsername().trim());
        sysUser.setPassword(sysUserDto.getUsername());
        sysUser.setEmail(sysUserDto.getEmail().trim());
        sysUser.setRealName(sysUserDto.getRealName().trim());
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

    @Override
    @Transactional
    public Integer saves(UnionAddVo[] addVos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < addVos.length; i++) {
            try {
                log.info(addVos[i].toString());
                SysDept dept = deptDao.findSysDeptByName(addVos[i].getDeptName()).orElseThrow(() -> {
                            return new EntityFieldException("系部不存在");
                        }
                );
                SysUser sysUser = new SysUser();
                sysUser.setUsername(addVos[i].getUsername().trim());
                sysUser.setRole(RoleEnum.ROLE_UNION);
                sysUser.setEmail(addVos[i].getEmail().trim());
                sysUser.setRealName(addVos[i].getRealName().trim());
                if (userService.save(sysUser) <= 0) {
                    throw new EntityFieldException("未知错误");
                }
                SysUserUnion userUnion = new SysUserUnion();
                userUnion.setUser(sysUser);
                userUnion.setDeptId(dept.getId());
                j += userUnionDao.save(userUnion);
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
