package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.model.entity.SysGrade;

import java.util.Optional;

public interface GradeDao extends JpaRepository<SysGrade, Integer> {
    Optional<SysGrade> findSysGradeByName(Integer name);
    Integer deleteSysGradeById(Integer id);
    Boolean existsByName(Integer name);
}
