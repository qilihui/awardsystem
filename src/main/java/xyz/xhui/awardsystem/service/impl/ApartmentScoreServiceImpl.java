package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ApartmentScoreDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.ApartmentScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentScoreServiceImpl implements ApartmentScoreService {
    @Autowired
    private ApartmentScoreDao apartmentScoreDao;

    @Autowired
    private UserHouseparentDao userHouseparentDao;

    @Override
    public List<ApartmentScore> findAll() {
        return apartmentScoreDao.findAll();
    }

    @Override
    public Optional<ApartmentScore> findById(Integer id) {
        return apartmentScoreDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try{
            apartmentScoreDao.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    @RolesAllowed("HOUSEPARENT")
    public ApartmentScore save(ApartmentScore apartmentScore) {
        Optional<SysUserHouseparent> houseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        apartmentScore.setApartment(houseparentOptional.get().getApartment());
        return apartmentScoreDao.save(apartmentScore);
    }
}
