package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ExtraScoreDao;
import xyz.xhui.awardsystem.dao.ExtraTimeDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.vo.ExtraScoreVo;
import xyz.xhui.awardsystem.service.ExtraScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtraScoreServiceImpl implements ExtraScoreService {
    @Autowired
    private ExtraScoreDao extraScoreDao;

    @Autowired
    private ExtraTimeDao extraTimeDao;

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private UserTutorDao userTutorDao;

    @Override
    @RolesAllowed("STU")
    public Integer save(ExtraScore extraScore) throws UnknownException {
        ExtraTime extraTime = extraTimeDao.findById(extraScore.getTimeId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        Long currentTimeMillis = MyTimeUtils.currentTimeMillis();
        if (extraTime.getBeginTime() > currentTimeMillis) {
            throw new UnknownException("未开始");
        }
        if (extraTime.getEndTime() < currentTimeMillis) {
            throw new UnknownException("已结束");
        }
        Optional<SysUserStu> stuOptional = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId());
        SysUserStu stu = stuOptional.orElseThrow(
                () -> new UnknownException("未知错误")
        );
        extraScore.setDeptId(stu.getDeptId());
        extraScore.setGradeId(stu.getGradeId());
        extraScore.setStuId(stu.getId());
        return extraScoreDao.save(extraScore);
    }

    @Override
    public ExtraScore findByStu(Integer termId, Integer timeId) throws UnknownException {
        SysUserStu stu = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return extraScoreDao.findByStu(stu.getId(), termId, timeId).orElseThrow(
                () -> new UnknownException("不存在")
        );
    }

    @Override
    @RolesAllowed("TUTOR")
    @Transactional
    public List<ExtraScoreVo> findByTutor(Integer termId, Integer timeId) throws UnknownException {
        SysUserTutor userTutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        List<ExtraScore> scoreList = extraScoreDao.findByTutor(userTutor.getDeptId(), userTutor.getGradeId(), termId, timeId);
        List<ExtraScoreVo> scoreVoList = new ArrayList<>();
        for (ExtraScore e : scoreList) {
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            scoreVoList.add(new ExtraScoreVo(e.getId(), stu.getUser().getUsername(), stu.getUser().getRealName(), e.getScore(), e.getRemark(), e.getStatus(), e.getCreateTime(), e.getPath()));
        }
        return scoreVoList;
    }

    @Override
    public ExtraScoreVo findById(Integer id, Integer timeId) throws UnknownException {
        SysUserTutor userTutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        ExtraScore extraScore = extraScoreDao.findById(id, userTutor.getDeptId(), userTutor.getGradeId(), timeId).orElseThrow(
                () -> new UnknownException("id不存在")
        );
        SysUserStu stu = userStuDao.findById(extraScore.getStuId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return new ExtraScoreVo(extraScore.getId(), stu.getUser().getUsername(), stu.getUser().getRealName(), extraScore.getScore(), extraScore.getRemark(), extraScore.getStatus(), extraScore.getCreateTime(), extraScore.getPath());
    }

    @Override
    @Transactional
    @RolesAllowed("TUTOR")
    public Integer deleteById(Integer id, Integer timeId) throws UnknownException {
        SysUserTutor userTutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return extraScoreDao.deleteById(id, userTutor.getDeptId(), userTutor.getGradeId(), timeId);
    }

    @Override
    public Integer passById(Integer id, Integer timeId, Integer pass) throws UnknownException {
        SysUserTutor userTutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return extraScoreDao.passById(id, userTutor.getDeptId(), userTutor.getGradeId(), timeId, pass);
    }
}
