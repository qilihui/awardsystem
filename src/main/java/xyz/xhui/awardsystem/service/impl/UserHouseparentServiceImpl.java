package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.PasswordUtils;
import xyz.xhui.awardsystem.dao.ApartmentDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.UserHouseparentService;
import xyz.xhui.awardsystem.service.UserService;

import java.util.Optional;

@Service
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
        sysUser.setUsername(sysUserDto.getUsername());
        sysUser.setPassword(PasswordUtils.encode(sysUserDto.getUsername()));
        sysUser.setEmail(sysUserDto.getEmail());
        sysUser.setRealName(sysUserDto.getRealName());
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

//    @Override
//    public Optional<SysUserHouseparent> findById(Integer id) {
//        return houseparentDao.findById(id);
//    }

//    @Override
//    @Transactional
//    public Boolean deleteBySysUserId(Integer id) throws EntityFieldException {
//        Optional<SysUserHouseparent> retUserHouseparent = this.findBySysUserId(id);
//        retUserHouseparent.orElseThrow(() -> {
//            return new EntityFieldException("用户不存在");
//        });
//        SysUserHouseparent sysUserHouseparent = retUserHouseparent.get();
//        houseparentDao.deleteById(sysUserHouseparent.getId());
////        houseparentDao.delete(sysUserHouseparent);
////        userDao.delete(sysUserHouseparent.getUser());
//        return true;
//    }

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
}
