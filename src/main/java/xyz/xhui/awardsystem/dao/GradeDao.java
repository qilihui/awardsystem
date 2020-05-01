package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.SysGrade;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface GradeDao {
    @Select("select * from sys_grade")
    List<SysGrade> findAll();

    @Select("select * from sys_grade where id=#{id}")
    Optional<SysGrade> findById(Integer id);

    @Select("select * from sys_grade where name=#{name}")
    Optional<SysGrade> findSysGradeByName(Integer name);

    @Delete("delete from sys_grade where id=#{id}")
    Integer deleteSysGradeById(Integer id);

    @Insert("insert into sys_grade(`name`) values(#{name})")
    Integer save(SysGrade grade);

    Integer update(SysGrade grade);
}
