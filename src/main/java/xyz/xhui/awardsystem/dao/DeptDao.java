package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.SysDept;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface DeptDao{
    @Select("select * from sys_dept")
    List<SysDept> findAll();

    @Select("select * from sys_dept where id=#{id}")
    Optional<SysDept> findById(Integer id);

    @Select("select * from sys_dept where name=#{name}")
    Optional<SysDept> findSysDeptByName(String name);

    @Delete("delete from sys_dept where id=#{id}")
    Integer deleteSysDeptById(Integer id);

    @Insert("insert into sys_dept(`name`) values(#{name})")
    Integer save(SysDept dept);

    Integer update(SysDept dept);
}
