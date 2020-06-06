package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.NoticeApartmentDao;
import xyz.xhui.awardsystem.dao.UserHouseparentDao;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeApartment;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.service.NoticeApartmentService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class NoticeApartmentServiceImpl implements NoticeApartmentService {
    @Autowired
    private NoticeApartmentDao noticeApartmentDao;

    @Autowired
    private UserHouseparentDao userHouseparentDao;

    @Override
    @RolesAllowed("HOUSEPARENT")
    public PageDto<List<NoticeApartment>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        SysUserHouseparent houseparent = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<NoticeApartment>> pageDto = new PageDto<>();
        pageDto.setObj(noticeApartmentDao.findPageByApartment(houseparent.getApartmentId(), pageNum, pageSize));
        pageDto.setCount(noticeApartmentDao.findPageCountByApartment(houseparent.getApartmentId()));
        return pageDto;
    }

    @Override
    public NoticeApartment findById(Integer id) throws UnknownException {
        SysUserHouseparent houseparent = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return noticeApartmentDao.findById(id, houseparent.getApartmentId()).orElseThrow(
                () -> new UnknownException("id不存在")
        );
    }

    @Override
    public Integer deleteById(Integer id) {
        return noticeApartmentDao.deleteById(id);
    }

    @Override
    public Integer save(NoticeApartment noticeApartment) throws UnknownException {
        SysUserHouseparent houseparent = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeApartment.setApartmentId(houseparent.getApartmentId());
        noticeApartment.setSubmitter(houseparent.getUser().getRealName());
        return noticeApartmentDao.save(noticeApartment);
    }

    @Override
    public Integer update(NoticeApartment noticeApartment) throws UnknownException {
        SysUserHouseparent houseparent = userHouseparentDao.findSysUserHouseparentByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeApartment.setApartmentId(houseparent.getApartmentId());
        noticeApartment.setSubmitter(houseparent.getUser().getRealName());
        return noticeApartmentDao.update(noticeApartment);
    }
}
