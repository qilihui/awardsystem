package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.dao.GradeDao;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.model.entity.SysGrade;
import xyz.xhui.awardsystem.service.GradeService;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeDao gradeDao;

    @Override
    public SysGrade save(Integer name) throws EntityFieldException {
        SysGrade grade = new SysGrade();
        grade.setName(name);
        Optional<SysGrade> deptOptional = gradeDao.findSysGradeByName(grade.getName());
        if (deptOptional.isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return gradeDao.save(grade);
    }

    @Override
    public List<SysGrade> findAll() {
        return gradeDao.findAll();
    }

    @Override
    public Optional<SysGrade> findById(Integer id) {
        return gradeDao.findById(id);
    }

    @Override
    public Integer deleteById(Integer id) throws EntityFieldException {
        Optional<SysGrade> gradeOptional = this.findById(id);
        gradeOptional.orElseThrow(() -> {
            return new EntityFieldException("id:" + id + " 不存在");
        });
        return gradeDao.deleteSysGradeById(id);
    }

    @Override
    @Transactional
    public Integer deleteGrades(Integer[] ids) throws EntityFieldException {
        Integer count = 0;
        for (Integer id : ids) {
            Optional<SysGrade> gradeOptional = this.findById(id);
            gradeOptional.orElseThrow(() -> {
                return new EntityFieldException("id:" + id + " 不存在");
            });
            count += gradeDao.deleteSysGradeById(id);
        }
        return count;
    }

    @Override
    public SysGrade updateGrade(SysGrade grade) throws EntityFieldException {
        if (grade.getId() == null) {
            throw new EntityFieldException("缺少id字段");
        }
        if (grade.getName() == null || "".equals(grade.getName())) {
            throw new EntityFieldException("name不能为空");
        }
        Optional<SysGrade> sysDeptByName = gradeDao.findSysGradeByName(grade.getName());
        if (sysDeptByName.isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return gradeDao.save(grade);
    }
}
