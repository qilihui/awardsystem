package xyz.xhui.awardsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.UnionScoreDao;
import xyz.xhui.awardsystem.dao.UserStuDao;
import xyz.xhui.awardsystem.dao.UserUnionDao;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;
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
    @RolesAllowed("UNION")
    @Transactional
    public PageDto<List<ScoreDto>> findAll(Integer pageNum, Integer pageSize) throws UnknownException {
        Optional<SysUserUnion> loginUser = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId());
        loginUser.orElseThrow(
                () -> new UnknownException("未知错误 请联系管理员")
        );
        List<UnionScore> unionScoreList = unionScoreDao.findByPageAndDeptId(loginUser.get().getDeptId(), pageNum, pageSize);
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
        PageDto<List<ScoreDto>> pageDto = new PageDto<>();
        pageDto.setObj(scoreDtoList);
        pageDto.setCount(unionScoreDao.findCountByPageAndDeptId(loginUser.get().getDeptId()));
        return pageDto;
    }

    @Override
    @RolesAllowed("UNION")
    @Transactional
    public Integer save(ScoreDto scoreDto) throws EntityFieldException, UnknownException {
        Optional<SysUserStu> stuOptional = userStuDao.findStuByUsername(scoreDto.getUsername());
        SysUserStu userStu = stuOptional.orElseThrow(
                () -> new EntityFieldException("学号: " + scoreDto.getUsername() + " 学生不存在")
        );
        Optional<SysUserUnion> unionOptional = userUnionDao.findSysUserUnionByUser_Id(MyUserUtils.getId());
        SysUserUnion userUnion = unionOptional.orElseThrow(
                () -> new UnknownException("系统错误 请联系管理员")
        );
        if (!userUnion.getDeptId().equals(userStu.getDeptId())) {
            throw new EntityFieldException("学号: " + scoreDto.getUsername() + " 不是本系学生");
        }
        UnionScore unionScore = new UnionScore();
        unionScore.setRemark(scoreDto.getRemark());
        unionScore.setScore(scoreDto.getScore());
        unionScore.setDeptId(userUnion.getDeptId());
        unionScore.setStuId(userStu.getId());
        unionScore.setUnionId(userUnion.getId());
        return unionScoreDao.save(unionScore);
    }

    @Override
    @RolesAllowed("UNION")
    @Transactional
    public Integer saves(ScoreDto[] scoreDtos) throws EntityFieldException {
        int j = 0;
        for (int i = 1; i < scoreDtos.length; i++) {
            try {
                scoreDtos[i].setScore(Integer.valueOf(scoreDtos[i].getScoreStr()));
                j += this.save(scoreDtos[i]);
            } catch (EntityFieldException | UnknownException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " " + e.getMessage());
            } catch (NumberFormatException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " 格式错误" + e.getMessage());
            }
        }
        return j;
    }

    @Override
    @Transactional
    public Integer deletes(Integer[] ids) throws EntityFieldException {
        Integer retCount = 0;
        for (Integer id : ids) {
            log.info(id.toString());
            Optional<UnionScore> unionScoreOptional = unionScoreDao.findById(id);
            unionScoreOptional.orElseThrow(() -> {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new EntityFieldException("id: " + id + " 不存在");
                    }
            );
            retCount += unionScoreDao.deleteById(id);
        }
        log.info(retCount.toString());
        return retCount;
    }

    @Override
    @Transactional
    public List<ScoreDto> findOneByStuId() throws EntityFieldException, UnknownException {
        Optional<SysUserStu> userStuOptional = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId());
        SysUserStu userStu = userStuOptional.orElseThrow(() -> {
            return new EntityFieldException("未知错误 请联系管理员");
        });
        List<UnionScore> unionScoreList = unionScoreDao.findOneByStuId(userStu.getId());
        List<ScoreDto> scoreDtoList = new ArrayList<>();
        for (UnionScore unionScore : unionScoreList) {
            Optional<SysUser> unionOptional = userUnionDao.findSysUserByUnoinId(unionScore.getUnionId());
            unionOptional.orElseThrow(
                    () -> new UnknownException("未知错误 请联系管理员")
            );
            ScoreDto scoreDto = new ScoreDto(unionScore.getId(), null, null, unionScore.getScore(), unionScore.getRemark(), unionOptional.get().getUsername(), unionScore.getCreateTime());
            scoreDtoList.add(scoreDto);
        }
        return scoreDtoList;
    }
}
