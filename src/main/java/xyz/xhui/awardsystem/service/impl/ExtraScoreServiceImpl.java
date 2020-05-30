package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyTimeUtils;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ExtraScoreDao;
import xyz.xhui.awardsystem.dao.ExtraTimeDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.ExtraScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@Service
public class ExtraScoreServiceImpl implements ExtraScoreService {
    @Autowired
    private ExtraScoreDao extraScoreDao;

    @Autowired
    private ExtraTimeDao extraTimeDao;

    @Autowired
    private UserStuDao userStuDao;

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
}
