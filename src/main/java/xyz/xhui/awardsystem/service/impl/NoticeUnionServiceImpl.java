package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.NoticeUnionDao;
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeUnion;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
import xyz.xhui.awardsystem.service.NoticeUnionService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
public class NoticeUnionServiceImpl implements NoticeUnionService {
    @Autowired
    private NoticeUnionDao noticeUnionDao;

    @Autowired
    private UserUnionDao userUnionDao;

    @Override
    @RolesAllowed("UNION")
    public PageDto<List<NoticeUnion>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        SysUserUnion union = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<NoticeUnion>> pageDto = new PageDto<>();
        pageDto.setObj(noticeUnionDao.findPageByDept(union.getDeptId(), pageNum, pageSize));
        pageDto.setCount(noticeUnionDao.findPageCountByDept(union.getDeptId()));
        return pageDto;
    }

    @Override
    public NoticeUnion findById(Integer id) throws UnknownException {
        SysUserUnion union = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        return noticeUnionDao.findById(id, union.getDeptId()).orElseThrow(
                () -> new UnknownException("id不存在")
        );
    }

    @Override
    public Integer deleteById(Integer id) {
        return noticeUnionDao.deleteById(id);
    }

    @Override
    public Integer save(NoticeUnion noticeUnion) throws UnknownException {
        SysUserUnion union = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeUnion.setDeptId(union.getDeptId());
        noticeUnion.setSubmitter(union.getUser().getRealName());
        return noticeUnionDao.save(noticeUnion);
    }

    @Override
    public Integer update(NoticeUnion noticeUnion) throws UnknownException {
        SysUserUnion union = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        noticeUnion.setDeptId(union.getDeptId());
        noticeUnion.setSubmitter(union.getUser().getRealName());
        return noticeUnionDao.update(noticeUnion);
    }
}
