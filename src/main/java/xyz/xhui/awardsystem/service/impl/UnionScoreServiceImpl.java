package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.UnionScoreDao;
import xyz.xhui.awardsystem.dao.UserDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.model.dto.UnionScoreDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.UnionScore;
import xyz.xhui.awardsystem.service.UnionScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
public class UnionScoreServiceImpl implements UnionScoreService {
    @Autowired
    private UnionScoreDao unionScoreDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public UserStuDao userStuDao;

    @Override
    public List<UnionScore> findAll() {
        return unionScoreDao.findAll();
    }

    @Override
    public Optional<UnionScore> findById(Integer id) {
        return unionScoreDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            unionScoreDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    @RolesAllowed("UNION")
    public UnionScore save(UnionScoreDto unionScoreDto) throws EntityFieldException, UnknownException {
        SysUser user = userDao.findSysUserByUsernameEqualsAndRoleEquals(unionScoreDto.getUsername(), RoleEnum.ROLE_STU);
        if (user == null) {
            throw new EntityFieldException("学号不存在");
        }
        SysUserStu sysUserStu = userStuDao.findSysUserStuByUser_Id(user.getId()).get();
        if (sysUserStu == null) {
            throw new UnknownException("未知错误");
        }
        UnionScore unionScore = new UnionScore();
        unionScore.setUserStu(sysUserStu);
        unionScore.setRemark(unionScoreDto.getRemark());
        unionScore.setScore(unionScoreDto.getScore());
        unionScore.setSubmitter(MyUserUtils.getUsername());
        return unionScoreDao.save(unionScore);
    }
}
