package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.dao.ApartmentDao;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.entity.SysApartment;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.UserHouseparentService;
import xyz.xhui.awardsystem.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserHouseparentServiceImpl implements UserHouseparentService {
    @Autowired
    private UserHouseparentDao houseparentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentDao apartmentDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<SysUserHouseparent> findAll() {
        return houseparentDao.findAll();
    }

    @Override
    public SysUserHouseparent save(SysUserHouseparent userHouseparent) throws EntityFieldException {
        userHouseparent.getUser().setRole(RoleEnum.ROLE_UNION);
        userService.save(userHouseparent.getUser());
        userHouseparent.setId(null);
        SysApartment sysApartment = apartmentDao.findById(userHouseparent.getApartment().getId()).orElseThrow(
                () -> new EntityFieldException("公寓id:" + userHouseparent.getApartment().getId() + "不存在")
        );
        userHouseparent.setApartment(sysApartment);
        return houseparentDao.save(userHouseparent);
    }

    @Override
    public Optional<SysUserHouseparent> findById(Integer id) {
        return houseparentDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<SysUserHouseparent> retUserHouseparent = this.findById(id);
        retUserHouseparent.ifPresent(sysUserUnion -> {
            houseparentDao.delete(sysUserUnion);
            userDao.delete(sysUserUnion.getUser());
        });
        return retUserHouseparent.isPresent();
    }

    @Override
    public Optional<SysUserHouseparent> findBySysUserId(Integer id) {
        return houseparentDao.findSysUserHouseparentByUser_Id(id);
    }
}
