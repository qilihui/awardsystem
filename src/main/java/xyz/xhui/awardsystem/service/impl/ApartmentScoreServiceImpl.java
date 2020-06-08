package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.service.ApartmentScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApartmentScoreServiceImpl implements ApartmentScoreService {
    @Autowired
    private ApartmentScoreDao apartmentScoreDao;

    @Autowired
    private UserHouseparentDao userHouseparentDao;

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private UserTutorDao userTutorDao;

    @Autowired
    private TermDao termDao;

    @Override
    public PageDto<List<ScoreDto>> findAll(Integer pageNum, Integer pageSize, Integer termId, Integer week) throws UnknownException {
        SysTerm term = termDao.findById(termId).orElseThrow(
                () -> new UnknownException("学期id" + termId + "不存在")
        );
        Optional<SysUserHouseparent> userHouseparentOptional = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId());
        userHouseparentOptional.orElseThrow(() -> new UnknownException("未知错误 请联系管理员"));
        PageDto<List<ScoreDto>> pageDto = new PageDto<>();
        Integer apartmentId = userHouseparentOptional.get().getApartmentId();

        long beginTime = term.getBeginTime();
        term.setBeginTime(beginTime + 604800000L * (week - 1));
        term.setEndTime(beginTime + 604800000L * week);

        List<ApartmentScore> apartmentScoreList = apartmentScoreDao.findAllByPagenumAndPagesize(apartmentId, pageNum, pageSize, term);

        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (ApartmentScore apartmentScore : apartmentScoreList) {
//            Integer week = Math.toIntExact((apartmentScore.getCreateTime() - term.getBeginTime()) / 604800000 + 1);
            ScoreDto scoreDto = new ScoreDto(apartmentScore.getId(), apartmentScore.getScore().toString(), apartmentScore.getRemark(), apartmentScore.getCreateTime(), week, apartmentScore.getRoom(), apartmentScore.getBed());
            scoreDtoList.add(scoreDto);
        }
        pageDto.setObj(scoreDtoList);
        pageDto.setCount(apartmentScoreDao.findCountAllApartmentId(apartmentId, term));
        return pageDto;
    }

    @Override
    @RolesAllowed("HOUSEPARENT")
    @Transactional
    public Integer save(ApartmentScore apartmentScore) throws UnknownException {
        if (apartmentScore.getScore() > 2) {
            throw new UnknownException("分数最高为2分");
        }
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
            for (int k = 0; k < 6; k++) {
                System.out.println(i + " " + k);
                try {
                    String score = apartmentScoreDtos[i].getS(k + 1);
                    if (score == null || "".equals(score)) {
                        continue;
                    }
                    ApartmentScore apartmentScore = new ApartmentScore();
                    apartmentScore.setRoom(Integer.valueOf(apartmentScoreDtos[i].getRoom()));
                    apartmentScore.setBed(k + 1);
                    apartmentScore.setScore(Double.valueOf(score));
                    apartmentScore.setRemark(apartmentScoreDtos[i].getRemark());
                    j += this.save(apartmentScore);
                } catch (NumberFormatException | UnknownException e) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new EntityFieldException("序号" + i + " " + e.getMessage());
                }
            }
        }
        return j;
    }

    @Override
    @Transactional
    @RolesAllowed({"STU"})
    public List<ScoreDto> findByStuId(Integer termId) throws EntityFieldException, UnknownException {
        Optional<SysUserStu> userStuOptional = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId());
        SysUserStu userStu = userStuOptional.orElseThrow(() -> {
            return new EntityFieldException("未知错误 请联系管理员");
        });
        Optional<SysTerm> termOptional = termDao.findById(termId);
        SysTerm term = termOptional.orElseThrow(
                () -> new UnknownException("学期id" + termId + "不存在")
        );
        List<UnionScore> unionScoreList = apartmentScoreDao.findByStuId(userStu, term);
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (UnionScore unionScore : unionScoreList) {
            Integer week = Math.toIntExact((unionScore.getCreateTime() - term.getBeginTime()) / 604800000 + 1);
            ScoreDto scoreDto = new ScoreDto(unionScore.getId(), null, null, unionScore.getScore().toString(), unionScore.getRemark(), null, unionScore.getCreateTime(), week);
            scoreDtoList.add(scoreDto);
        }
        return scoreDtoList;
    }

    @Override
    @Transactional
    @RolesAllowed({"STU"})
    public List<ScoreDto> findByNowWeek() throws UnknownException {
        SysUserStu stu = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误 请联系管理员")
        );
        SysTerm term = termDao.findOne().orElseThrow(
                () -> new UnknownException("未开始"));
        int week = Math.toIntExact((MyTimeUtils.currentTimeMillis() - term.getBeginTime()) / 604800000 + 1);
        long beginTime = term.getBeginTime();
        term.setBeginTime(beginTime + 604800000L * (week - 1));
        term.setEndTime(beginTime + 604800000L * week);
        List<UnionScore> scoreList = apartmentScoreDao.findByStuId(stu, term);
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (UnionScore unionScore : scoreList) {
            ScoreDto scoreDto = new ScoreDto(unionScore.getId(), null, null, unionScore.getScore().toString(), unionScore.getRemark(), null, unionScore.getCreateTime(), week);
            scoreDtoList.add(scoreDto);
        }
        return scoreDtoList;
    }

    @Override
    @Transactional
    @RolesAllowed({"TUTOR"})
    public PageDto<List<ScoreDto>> findByStuIdByTutor(Integer pageNum, Integer pageSize, Integer termId, Integer week) throws EntityFieldException, UnknownException {
        Optional<SysUserTutor> tutorOptional = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId());
        SysUserTutor tutor = tutorOptional.orElseThrow(() -> {
            return new EntityFieldException("未知错误 请联系管理员");
        });
        Optional<SysTerm> termOptional = termDao.findById(termId);
        SysTerm term = termOptional.orElseThrow(
                () -> new UnknownException("学期id" + termId + "不存在")
        );

        long beginTime = term.getBeginTime();
        term.setBeginTime(beginTime + 604800000L * (week - 1));
        term.setEndTime(beginTime + 604800000L * week);

        List<UnionScore> unionScoreList = apartmentScoreDao.findByTutor(pageNum, pageSize, tutor, term);
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (UnionScore unionScore : unionScoreList) {
            Optional<SysUserStu> userStuOptional = userStuDao.findSysUserStuByUser_Id(unionScore.getStuId());
            SysUserStu stu = userStuOptional.orElseThrow(() -> {
                return new EntityFieldException("未知错误 请联系管理员");
            });
            ScoreDto scoreDto = new ScoreDto(unionScore.getId(), stu.getUser().getUsername(), stu.getUser().getRealName(), unionScore.getScore().toString(), unionScore.getRemark(), unionScore.getCreateTime(), week);
            scoreDtoList.add(scoreDto);
        }
        PageDto<List<ScoreDto>> pageDto = new PageDto<>();
        pageDto.setObj(scoreDtoList);
        pageDto.setCount(apartmentScoreDao.findCountByTutor(tutor, term));
        return pageDto;
    }
}
