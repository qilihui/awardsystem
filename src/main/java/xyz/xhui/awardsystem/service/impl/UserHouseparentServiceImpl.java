package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.ApartmentDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.HouseparentAddVo;
import xyz.xhui.awardsystem.service.UserHouseparentService;
import xyz.xhui.awardsystem.service.UserService;

import java.util.Optional;

@Service
@Slf4j
public class UserHouseparentServiceImpl implements UserHouseparentService {
    @Autowired
    private UserHouseparentDao houseparentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentDao apartmentDao;

//    @Override
//    public List<SysUserHouseparent> findAll() {
//        return houseparentDao.findAll();
//    }

    @Override
    @Transactional
    public Integer save(UserInfoDto userInfoDto, SysUserDto sysUserDto) throws EntityFieldException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(sysUserDto.getUsername().trim());
        sysUser.setPassword(sysUserDto.getUsername());
        sysUser.setEmail(sysUserDto.getEmail().trim());
        sysUser.setRealName(sysUserDto.getRealName().trim());
        sysUser.setRole(RoleEnum.ROLE_HOUSEPARENT);
        apartmentDao.findById(userInfoDto.getApartmentId()).orElseThrow(
                () -> new EntityFieldException("公寓id:" + userInfoDto.getApartmentId() + "不存在")
        );
        if (houseparentDao.findByApartmentId(userInfoDto.getApartmentId()).isPresent()){
            throw new EntityFieldException("公寓id:" + userInfoDto.getApartmentId() + "已经添加过宿舍管理员");
        }
        if (userService.save(sysUser) <= 0) {
            return 0;
        }
        SysUserHouseparent userHouseparent = new SysUserHouseparent();
        userHouseparent.setUser(sysUser);
        userHouseparent.setApartmentId(userInfoDto.getApartmentId());
        return houseparentDao.save(userHouseparent);
    }

    @Override
    public Optional<SysUserHouseparent> findBySysUserId(Integer id) {
        return houseparentDao.findSysUserHouseparentByUser_Id(id);
    }

    @Override
    @Transactional
    public Integer updateEmailAndRealName(UserInfoDto userInfoDto, SysUserDto userDto) throws EntityFieldException {
        if (!userDto.getRole().equals(RoleEnum.ROLE_HOUSEPARENT.toString())) {
            throw new EntityFieldException("角色错误");
        }
        if (userInfoDto.getUserInfoId() == null) {
            throw new EntityFieldException("userInfoId字段缺失");
        }
        Integer integer = userService.updateEmailAndRealName(userDto);
        Integer integer1 = houseparentDao.updateInfo(userInfoDto);
        return integer + integer1;
    }

    @Override
    @Transactional
    public Integer saves(HouseparentAddVo[] addVos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < addVos.length; i++) {
            try {
                log.info(addVos[i].toString());
                SysApartment apartment = apartmentDao.findSysApartmentByName(addVos[i].getApartmentName()).orElseThrow(() -> {
                            return new EntityFieldException("公寓不存在");
                        }
                );
                SysUser sysUser = new SysUser();
                sysUser.setUsername(addVos[i].getUsername().trim());
                sysUser.setRole(RoleEnum.ROLE_HOUSEPARENT);
                sysUser.setEmail(addVos[i].getEmail().trim());
                sysUser.setRealName(addVos[i].getRealName().trim());
                if (userService.save(sysUser) <= 0) {
                    throw new EntityFieldException("未知错误");
                }
                SysUserHouseparent userHouseparent = new SysUserHouseparent();
                userHouseparent.setUser(sysUser);
                userHouseparent.setApartmentId(apartment.getId());
                j += houseparentDao.save(userHouseparent);
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
