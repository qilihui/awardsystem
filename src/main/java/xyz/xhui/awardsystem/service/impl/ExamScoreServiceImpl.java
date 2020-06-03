package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.config.utils.MyUserUtils;
import xyz.xhui.awardsystem.dao.*;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.*;
import xyz.xhui.awardsystem.model.vo.ExamScoreVo;
import xyz.xhui.awardsystem.model.dto.ExamScoreDto;
import xyz.xhui.awardsystem.service.ExamScoreService;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamScoreServiceImpl implements ExamScoreService {
    @Autowired
    private ExamScoreDao examScoreDao;

    @Autowired
    private UserStuDao userStuDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private UserTutorDao userTutorDao;

    @Override
    @Transactional
    public Integer saves(ExamScoreVo scoreVo) throws EntityFieldException {
        int retVal = 0;
        List<ExamScoreVo.DataBean> beans = scoreVo.getData();
        for (int i = 1; i < beans.size(); i++) {
            try {
                int finalI = i;
                SysUserStu stu = userStuDao.findStuByUsername(beans.get(i).getUsername()).orElseThrow(
                        () -> new EntityFieldException("学号" + beans.get(finalI).getUsername() + "不存在")
                );
                Optional<ExamScore> exist = examScoreDao.existByStuIdAndTermId(stu.getId(), scoreVo.getTermId());
                exist.ifPresent(examScore -> examScoreDao.deleteById(examScore.getId()));
                ExamScore examScore = new ExamScore(null, stu.getId(), Double.valueOf(beans.get(i).getScoreStr()), beans.get(i).getRemark(), Integer.valueOf(beans.get(i).getCount()), scoreVo.getTermId(), stu.getDeptId(), stu.getGradeId());
                retVal += examScoreDao.save(examScore);
            } catch (EntityFieldException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " " + e.getMessage());
            } catch (NumberFormatException e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("序号" + i + " 信息错误");
            }
        }
        return retVal;
    }

    @Override
    @Transactional
    public PageDto<List<ExamScoreDto>> findAll(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException {
        PageDto<List<ExamScoreDto>> pageDto = new PageDto<>();
        pageDto.setCount(examScoreDao.findPageAllCount(pageNum, pageSize, termId));
        List<ExamScore> scoreList = examScoreDao.findPageAll(pageNum, pageSize, termId);
        List<ExamScoreDto> dtos = new ArrayList<>();
        for (ExamScore e : scoreList) {
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            SysDept dept = deptDao.findById(stu.getDeptId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            SysGrade grade = gradeDao.findById(stu.getGradeId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            ExamScoreDto examScoreDto = new ExamScoreDto(e, stu);
            examScoreDto.setDeptName(dept.getName());
            examScoreDto.setGradeName(grade.getName().toString());
            dtos.add(examScoreDto);
        }
        pageDto.setObj(dtos);
        return pageDto;
    }

    @Override
    @Transactional
    public Integer deletes(Integer[] ids, Integer termId) throws EntityFieldException {
        Integer count = 0;
        for (Integer id : ids) {
            if (examScoreDao.existById(id, termId) == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new EntityFieldException("id:" + id + " 不存在");
            }
            count += examScoreDao.deleteById(id);
        }
        return count;
    }

    @Override
    @Transactional
    @RolesAllowed("TUTOR")
    public PageDto<List<ExamScoreDto>> findAllByTutor(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException {
        SysUserTutor tutor = userTutorDao.findSysUserTutorByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<ExamScoreDto>> pageDto = new PageDto<>();
        pageDto.setCount(examScoreDao.findPageAllCountByTutor(pageNum, pageSize, termId, tutor.getDeptId(), tutor.getGradeId()));
        List<ExamScore> scoreList = examScoreDao.findPageAllByTutor(pageNum, pageSize, termId, tutor.getDeptId(), tutor.getGradeId());
        List<ExamScoreDto> dtos = new ArrayList<>();
        for (ExamScore e : scoreList) {
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            ExamScoreDto examScoreDto = new ExamScoreDto(e, stu);
            dtos.add(examScoreDto);
        }
        pageDto.setObj(dtos);
        return pageDto;
    }

    @Override
    @Transactional
    @RolesAllowed("STU")
    public PageDto<List<ExamScoreDto>> findAllByStu(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException {
        SysUserStu userStu = userStuDao.findSysUserStuByUser_Id(MyUserUtils.getId()).orElseThrow(
                () -> new UnknownException("未知错误")
        );
        PageDto<List<ExamScoreDto>> pageDto = new PageDto<>();
        pageDto.setCount(examScoreDao.findPageAllCountByTutor(pageNum, pageSize, termId, userStu.getDeptId(), userStu.getGradeId()));
        List<ExamScore> scoreList = examScoreDao.findPageAllByTutor(pageNum, pageSize, termId, userStu.getDeptId(), userStu.getGradeId());
        List<ExamScoreDto> dtos = new ArrayList<>();
        for (ExamScore e : scoreList) {
            SysUserStu stu = userStuDao.findById(e.getStuId()).orElseThrow(
                    () -> new UnknownException("未知错误")
            );
            ExamScoreDto examScoreDto = new ExamScoreDto(e, stu);
            dtos.add(examScoreDto);
        }
        pageDto.setObj(dtos);
        return pageDto;
    }
}
