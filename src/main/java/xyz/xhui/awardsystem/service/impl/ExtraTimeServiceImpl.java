package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.ExtraTimeDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.entity.ExtraTime;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.ExtraTimeService;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraTimeServiceImpl implements ExtraTimeService {
    @Autowired
    private ExtraTimeDao extraTimeDao;

    @Autowired
    private UserTutorDao userTutorDao;

    @Override
    public List<ExtraTime> findAll(Integer termId) throws UnknownException {
        Optional<SysUserTutor> tutorOptional = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId());
        SysUserTutor tutor = tutorOptional.orElseThrow(
                () -> new UnknownException("未知错误，请联系管理员")
        );
        return extraTimeDao.findAll(tutor.getId(), termId);
    }

    @Override
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
}
