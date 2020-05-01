package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysDept;

import java.util.List;
import java.util.Optional;

public interface DeptService {
    Integer save(String name) throws EntityFieldException;

    List<SysDept> findAll();

    Optional<SysDept> findById(Integer id);

//    Boolean deleteById(Integer id) throws EntityFieldException;

    Integer deleteDepts(Integer[] ids) throws EntityFieldException;

    Integer updateDept(SysDept dept) throws EntityFieldException;
}
