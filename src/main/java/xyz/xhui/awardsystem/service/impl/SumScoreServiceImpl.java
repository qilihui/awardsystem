package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.SumScoreDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;
import xyz.xhui.awardsystem.service.SumScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public SumScoreDto getByTutor(SumScoreVo sumScoreVo) throws UnknownException {
        SysUserTutor tutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        List<ExamScore> examScoreList = examScoreDao.findAllByTutor(sumScoreVo.getTermId(), tutor.getDeptId(), tutor.getGradeId());
        ArrayList<SumScoreDto> sumScoreDtos = new ArrayList<>();
        for (ExamScore e : examScoreList) {
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            SumScoreDto sumScoreDto = new SumScoreDto();
            sumScoreDto.setUsername(stu.getUser().getUsername());
            sumScoreDto.setRealName(stu.getUser().getRealName());
            sumScoreDto.setExamScore(e);
            SysTerm term = termDao.findById(sumScoreVo.getTermId()).orElseThrow(
                    () -> new UnknownException("term id 不存在")
            );
            List<UnionScore> apartmentScoreList = apartmentScoreDao.findByStuId(stu, term);

        }
        return null;
    }
}
