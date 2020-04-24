package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysGrade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    /**
     * 添加系部
     * @param
     * @return
     */
    SysGrade save(Integer name) throws EntityFieldException;

    /**
     * 查询所有系部
     * @return
     */
    List<SysGrade> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysGrade> findById(Integer id);

    /**
     * 删除
     * @param
     * @return
     */
    Integer deleteById(Integer id) throws EntityFieldException;

    Integer deleteGrades(Integer[] ids) throws EntityFieldException;

    SysGrade updateGrade(SysGrade grade) throws EntityFieldException;
}
