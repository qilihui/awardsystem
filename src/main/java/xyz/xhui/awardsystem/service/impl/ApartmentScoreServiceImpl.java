package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ApartmentScoreDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.ApartmentScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApartmentScoreServiceImpl implements ApartmentScoreService {
    @Autowired
    private ApartmentScoreDao apartmentScoreDao;

    @Autowired
    private UserHouseparentDao userHouseparentDao;

    @Override
    public PageDto<List<ApartmentScore>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        userHouseparentOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员"));
        PageDto<List<ApartmentScore>> pageDto = new PageDto<>();
        Integer apartmentId = userHouseparentOptional.get().getApartmentId();
        pageDto.setObj(apartmentScoreDao.findAllByPagenumAndPagesize(apartmentId, pageNum, pageSize));
        pageDto.setCount(apartmentScoreDao.findCountAllApartmentId(apartmentId));
        return pageDto;
    }

    @Override
    @RolesAllowed("HOUSEPARENT")
    @Transactional
    public Integer save(ApartmentScore apartmentScore) throws UnknownException {
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        userHouseparentOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员"));
        apartmentScore.setApartmentId(userHouseparentOptional.get().getApartmentId());
        return apartmentScoreDao.save(apartmentScore);
    }

    @Override
    @Transactional
    public Integer deletes(Integer[] ids) throws EntityFieldException {
        Integer retCount = 0;
        for (Integer id : ids) {
            log.info(id.toString());
            Optional<ApartmentScore> apartmentScoreOptional = apartmentScoreDao.findById(id);
            apartmentScoreOptional.orElseThrow(() -> {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new EntityFieldException("id: " + id + " 不存在");
                    }
            );
            retCount += apartmentScoreDao.deleteById(id);
        }
        log.info(retCount.toString());
        return retCount;
    }

    @Override
    @RolesAllowed("HOUSEPARENT")
    @Transactional
    public Integer saves(ApartmentScoreDto[] apartmentScoreDtos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < apartmentScoreDtos.length; i++) {
            try {
                ApartmentScore apartmentScore = new ApartmentScore();
                apartmentScore.setRoom(Integer.valueOf(apartmentScoreDtos[i].getRoom()));
                apartmentScore.setBed(Integer.valueOf(apartmentScoreDtos[i].getBed()));
                apartmentScore.setScore(Integer.valueOf(apartmentScoreDtos[i].getScore()));
                apartmentScore.setRemark(apartmentScoreDtos[i].getRemark());
                j += this.save(apartmentScore);
            } catch (NumberFormatException | UnknownException e) {
                throw new EntityFieldException("序号" + i + " " + e.getMessage());
            }
        }
        return j;
    }
}
