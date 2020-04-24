package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysDept;

import java.util.List;
import java.util.Optional;

public interface DeptService {
    /**
     * 添加系部
     * @param
     * @return
     */
    SysDept save(String name) throws EntityFieldException;

    /**
     * 查询所有系部
     * @return
     */
    List<SysDept> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysDept> findById(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean deleteById(Integer id) throws EntityFieldException;

    Integer deleteDepts(Integer[] ids) throws EntityFieldException;

    SysDept updateDept(SysDept dept) throws EntityFieldException;
}
