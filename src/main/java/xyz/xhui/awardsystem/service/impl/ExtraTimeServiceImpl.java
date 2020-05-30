package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.result.ResultFactory;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ExtraScoreDao;
import xyz.xhui.awardsystem.dao.ExtraTimeDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.vo.StuExtraTime;
import xyz.xhui.awardsystem.service.ExtraTimeService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtraTimeServiceImpl implements ExtraTimeService {
    @Autowired
    private ExtraTimeDao extraTimeDao;

    @Autowired
    private UserTutorDao userTutorDao;
    @Autowired
    private UserStuDao userStuDao;
    @Autowired
    private ExtraScoreDao extraScoreDao;

    @Override
    @RolesAllowed("TUTOR")
    public List<ExtraTime> findAll(Integer termId) throws UnknownException {
        Optional<SysUserTutor> tutorOptional = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId());
        SysUserTutor tutor = tutorOptional.orElseThrow(
                () -> new UnknownException("未知错误，请联系管理员")
        );
        return extraTimeDao.findAll(tutor.getId(), termId);
    }

    @Override
    @RolesAllowed("TUTOR")
    public Integer save(ExtraTime extraTime) throws UnknownException {
        Optional<SysUserTutor> tutorOptional = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId());
        SysUserTutor tutor = tutorOptional.orElseThrow(
                () -> new UnknownException("未知错误，请联系管理员")
        );
        extraTime.setDeptId(tutor.getDeptId());
        extraTime.setGradeId(tutor.getGradeId());
        extraTime.setTutorId(tutor.getId());
        return extraTimeDao.save(extraTime);
    }

    @Override
    public Integer deleteById(Integer id) {
        return extraTimeDao.deleteById(id);
    }

    @Override
    @RolesAllowed("STU")
    @Transactional
    public List<StuExtraTime> findByStu(Integer termId) throws UnknownException {
        Optional<SysUserStu> stuOptional = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId());
        SysUserStu stu = stuOptional.orElseThrow(
                () -> new UnknownException("未知错误，请联系管理员")
        );
        List<ExtraTime> extraTimeList = extraTimeDao.findByStu(stu.getDeptId(), stu.getGradeId(), termId);
        List<StuExtraTime> stuextraTimes = new ArrayList<StuExtraTime>();
        for (ExtraTime e : extraTimeList) {
            StuExtraTime stuExtraTime = new StuExtraTime();
            stuExtraTime.setId(e.getId());
            stuExtraTime.setName(e.getName());
            stuExtraTime.setBeginTime(e.getBeginTime());
            stuExtraTime.setEndTime(e.getEndTime());
            stuExtraTime.setStatus(extraScoreDao.getStuStatus(stu.getId(), termId, e.getId()));
            stuextraTimes.add(stuExtraTime);
        }
        return stuextraTimes;
    }
}
