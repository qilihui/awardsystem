package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.NoticeTutorDao;
import xyz.xhui.awardsystem.dao.UserTutorDao;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeTutor;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.service.NoticeTutorService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class NoticeTutorServiceImpl implements NoticeTutorService {
    @Autowired
    private NoticeTutorDao noticeTutorDao;

    @Autowired
    private UserTutorDao tutorDao;

    @Override
    @RolesAllowed("TUTOR")
    public PageDto<List<NoticeTutor>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        SysUserTutor tutor = tutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<NoticeTutor>> pageDto = new PageDto<>();
        pageDto.setObj(noticeTutorDao.findPageByDeptAndGrade(tutor.getDeptId(),tutor.getGradeId(),  pageNum, pageSize));
        pageDto.setCount(noticeTutorDao.findPageCountByDeptAndGrade(tutor.getDeptId(),tutor.getGradeId()));
        return pageDto;
    }

    @Override
    public NoticeTutor findById(Integer id) throws UnknownException {
        SysUserTutor tutor = tutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return noticeTutorDao.findById(id, tutor.getDeptId(), tutor.getGradeId()).orElseThrow(
                () -> new UnknownException("id不存在")
        );
    }

    @Override
    public Integer deleteById(Integer id) {
        return noticeTutorDao.deleteById(id);
    }

    @Override
    public Integer save(NoticeTutor noticeUnion) throws UnknownException {
        SysUserTutor tutor = tutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeUnion.setDeptId(tutor.getDeptId());
        noticeUnion.setGradeId(tutor.getGradeId());
        noticeUnion.setSubmitter(tutor.getUser().getRealName());
        return noticeTutorDao.save(noticeUnion);
    }

    @Override
    public Integer update(NoticeTutor noticeUnion) throws UnknownException {
        SysUserTutor tutor = tutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeUnion.setDeptId(tutor.getDeptId());
        noticeUnion.setGradeId(tutor.getGradeId());
        noticeUnion.setSubmitter(tutor.getUser().getRealName());
        return noticeTutorDao.update(noticeUnion);
    }
}
