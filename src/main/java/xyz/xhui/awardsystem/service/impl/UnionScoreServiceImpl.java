package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.dao.UnionScoreDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.dto.UnionScoreDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.UnionScore;
import xyz.xhui.awardsystem.service.UnionScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UnionScoreServiceImpl implements UnionScoreService {
    @Autowired
    private UnionScoreDao unionScoreDao;

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private UserUnionDao userUnionDao;

    @Override
    public List<UnionScore> findAll() {
        return unionScoreDao.findAll();
    }

    @Override
    public List<ScoreDto> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        List<UnionScore> unionScoreList = unionScoreDao.findByPage(pageNum, pageSize);
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (UnionScore unionScore : unionScoreList) {
            Optional<SysUser> stuOptional = userStuDao.findSysUserByStuId(unionScore.getStuId());
            stuOptional.orElseThrow(
                    () -> new UnknownException("未知错误 请联系管理员")
            );
            Optional<SysUser> unionOptional = userUnionDao.findSysUserByUnoinId(unionScore.getUnionId());
            unionOptional.orElseThrow(
                    () -> new UnknownException("未知错误 请联系管理员")
            );
            ScoreDto scoreDto = new ScoreDto(unionScore.getId(), stuOptional.get().getUsername(), stuOptional.get().getRealName(), unionScore.getScore(), unionScore.getRemark(), unionOptional.get().getUsername(), unionScore.getCreateTime());
            scoreDtoList.add(scoreDto);
        }
        return scoreDtoList;
    }


//    @Override
//    public Boolean deleteById(Integer id) {
//        try {
//            unionScoreDao.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            return false;
//        }
//        return true;
//    }

    @Override
    @RolesAllowed("UNION")
    public Integer save(UnionScoreDto unionScoreDto) throws EntityFieldException, UnknownException {
//        SysUser user = userDao.findSysUserByUsernameEqualsAndRoleEquals(unionScoreDto.getUsername(), RoleEnum.ROLE_STU);
//        if (user == null) {
//            throw new EntityFieldException("学号不存在");
//        }
//        SysUserStu sysUserStu = userStuDao.findSysUserStuByUser_Id(user.getId()).get();
//        if (sysUserStu == null) {
//            throw new UnknownException("未知错误");
//        }
//        UnionScore unionScore = new UnionScore();
//        unionScore.setUserStu(sysUserStu);
//        unionScore.setRemark(unionScoreDto.getRemark());
//        unionScore.setScore(unionScoreDto.getScore());
//        unionScore.setSubmitter(MyUserUtils.getUsername());
//        return unionScoreDao.save(unionScore);
        return null;
    }
}
