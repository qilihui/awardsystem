package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysGrade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Integer save(Integer name) throws EntityFieldException;

    List<SysGrade> findAll();

    Optional<SysGrade> findById(Integer id);

//    Integer deleteById(Integer id) throws EntityFieldException;

    Integer deleteGrades(Integer[] ids) throws EntityFieldException;

    Integer updateGrade(SysGrade grade) throws EntityFieldException;
}
