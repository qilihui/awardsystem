package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.SysApartment;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ApartmentDao {
    @Select("select * from sys_apartment")
    List<SysApartment> findAll();

    @Select("select * from sys_apartment where id=#{id}")
    Optional<SysApartment> findById(Integer id);

    @Select("select * from sys_apartment where name=#{name}")
    Optional<SysApartment> findSysApartmentByName(String name);

    @Delete("delete from sys_apartment where id=#{id}")
    Integer deleteSysApartmentById(Integer id);

    @Insert("insert into sys_apartment(`name`) values(#{name})")
    Integer save(SysApartment apartment);

    Integer update(SysApartment apartment);
}
