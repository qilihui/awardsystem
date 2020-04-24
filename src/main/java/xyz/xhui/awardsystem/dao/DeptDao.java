package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysDept;

import java.util.Optional;

public interface DeptDao extends JpaRepository<SysDept, Integer> {
    Optional<SysDept> findSysDeptByName(String name);
    Integer deleteSysDeptById(Integer id);
}
