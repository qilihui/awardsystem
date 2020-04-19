package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysDept;

public interface DeptDao extends JpaRepository<SysDept, Integer> {
}
