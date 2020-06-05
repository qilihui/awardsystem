package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.SumScoreDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;
import xyz.xhui.awardsystem.service.SumScoreService;

import javax.annotation.security.RolesAllowed;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SumScoreServiceImpl implements SumScoreService {
    @Autowired
    private ExamScoreDao examScoreDao;

    @Autowired
    private UserTutorDao userTutorDao;

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private ApartmentScoreDao apartmentScoreDao;

    @Autowired
    private UnionScoreDao unionScoreDao;

    @Autowired
    private ExtraScoreDao extraScoreDao;

    @Autowired
    private TermDao termDao;

    @Override
    @RolesAllowed("TUTOR")
    @Transactional
    public List<SumScoreDto> getByTutor(SumScoreVo sumScoreVo) throws UnknownException {
        SysUserTutor tutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        //学生的考试成绩
        List<ExamScore> examScoreList = examScoreDao.findAllByTutor(sumScoreVo.getTermId(), tutor.getDeptId(), tutor.getGradeId());
        List<SumScoreDto> sumScoreDtos = new ArrayList<>();
        for (ExamScore e : examScoreList) {
            //学生个人信息
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            SumScoreDto sumScoreDto = new SumScoreDto();
            sumScoreDto.setUsername(stu.getUser().getUsername());
            sumScoreDto.setRealName(stu.getUser().getRealName());
            sumScoreDto.setExamScore(e.getScore());
            sumScoreDto.setCount(e.getCount());
            SysTerm term = termDao.findById(sumScoreVo.getTermId()).orElseThrow(
                    () -> new UnknownException("term id 不存在")
            );
            //宿舍分数
            BigDecimal apartmentSum = BigDecimal.valueOf(0);
            List<UnionScore> apartmentScoreList = apartmentScoreDao.findByStuId(stu, term);
            for (UnionScore u : apartmentScoreList) {
                apartmentSum = apartmentSum.add(BigDecimal.valueOf(u.getScore()));
            }
            sumScoreDto.setApartmentScore(apartmentSum.doubleValue());

            //学生会扣分
            BigDecimal unionSum = BigDecimal.valueOf(0);
            List<UnionScore> unionScoreList = unionScoreDao.findByStuId(stu.getId(), term);
            for (UnionScore s : unionScoreList) {
                unionSum = unionSum.add(BigDecimal.valueOf(s.getScore()));
            }
            //额外加分
            List<ExtraScore> extraScoreList = extraScoreDao.findListByStuAndStatusEqualsPass(stu.getId(), term.getId());
            BigDecimal extraSum = BigDecimal.valueOf(0);
            for (ExtraScore s : extraScoreList) {
                extraSum = extraSum.add(BigDecimal.valueOf(s.getScore()));
            }
            BigDecimal moralScoreDecimal = extraSum.subtract(unionSum);
            if (moralScoreDecimal.doubleValue() > sumScoreVo.getMoralMaxScore()) {
                moralScoreDecimal = BigDecimal.valueOf(sumScoreVo.getMoralMaxScore());
            }
            sumScoreDto.setMoralScore(moralScoreDecimal.doubleValue());
            BigDecimal examRate = BigDecimal.valueOf(e.getScore()).multiply(BigDecimal.valueOf(0.8));
            BigDecimal sumDecimal = examRate.add(apartmentSum).add(moralScoreDecimal);
            sumScoreDto.setSumScore(sumDecimal.doubleValue());
            sumScoreDtos.add(sumScoreDto);
        }
        sumScoreDtos.sort((o1, o2) -> o2.getSumScore().compareTo(o1.getSumScore()));
        int tmp = 1;
        if ("rate".equals(sumScoreVo.getAwardMethod().trim().toLowerCase())) {
            Integer peopleCount = sumScoreDtos.size();
            sumScoreVo.setOne((int) Math.ceil(peopleCount * sumScoreVo.getOne() / 100d));
            sumScoreVo.setTwo((int) Math.ceil(peopleCount * sumScoreVo.getTwo() / 100d));
            sumScoreVo.setThree((int) Math.ceil(peopleCount * sumScoreVo.getThree() / 100d));
        }
        int one = sumScoreVo.getOne();
        int two = sumScoreVo.getTwo();
        int three = sumScoreVo.getThree();
        int awardSum = one + two + three;
        for (SumScoreDto s : sumScoreDtos) {
            s.setRank(tmp++);
            if (awardSum > 0 && ("on".equals(sumScoreVo.getLessNo().trim().toLowerCase()) && s.getCount().equals(0))) {
                awardSum--;
                if (one > 0) {
                    one--;
                    s.setAwardRank("一等");
                } else if (two > 0) {
                    two--;
                    s.setAwardRank("二等");
                } else if (three > 0) {
                    three--;
                    s.setAwardRank("三等");
                }

            }
        }
        return sumScoreDtos;
    }
}
