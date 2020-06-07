package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.NoticeDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.service.NoticeStuService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class NoticeStuServiceImpl implements NoticeStuService {
    /*@Autowired
    private NoticeTutorDao noticeTutorDao;

    @Autowired
    private NoticeApartmentDao noticeApartmentDao;

    @Autowired
    private NoticeUnionDao noticeUnionDao;*/

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private NoticeStuDao noticeStuDao;

    @Override
    @RolesAllowed("STU")
    public PageDto<List<NoticeDto>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        SysUserStu stu = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<NoticeDto>> pageDto = new PageDto<>();
        pageDto.setObj(noticeStuDao.findAll(stu, pageNum, pageSize));
        pageDto.setCount(noticeStuDao.findCountAll(stu));
        return pageDto;
    }
}
