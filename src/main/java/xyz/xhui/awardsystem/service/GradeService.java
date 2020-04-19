package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.model.entity.SysGrade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    /**
     * 添加系部
     * @param grade
     * @return
     */
    SysGrade save(SysGrade grade);

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
     * @param id
     */
    Boolean deleteById(Integer id);
}
