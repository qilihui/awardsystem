package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.SysTerm;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface TermDao {
    @Select("select * from sys_term order by begin_time desc")
    List<SysTerm> findAll();

    @Select("select * from sys_term where id=#{id}")
    Optional<SysTerm> findById(Integer id);

    @Insert("insert into sys_term(`name`,`begin_time`,`end_time`) values(#{name}, #{beginTime}, #{endTime})")
    Integer save(SysTerm term);

    @Delete("delete from sys_term where id=#{id}")
    Integer deleteById(Integer id);
}
