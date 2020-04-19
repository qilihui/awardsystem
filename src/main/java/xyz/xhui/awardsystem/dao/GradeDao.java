package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysGrade;

public interface GradeDao extends JpaRepository<SysGrade, Integer> {
}
