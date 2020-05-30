package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ExtraTime;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ExtraTimeDao {
    @Select("select * from extra_time where tutor_id=#{tutorId} and term_id=#{termId}")
    List<ExtraTime> findAll(Integer tutorId, Integer termId);

    @Insert("insert into extra_time(`name`,`term_id`,`begin_time`,`end_time`,`dept_id`,`grade_id`,`tutor_id`) values(#{name},#{termId},#{beginTime},#{endTime},#{deptId},#{gradeId},#{tutorId})")
    Integer save(ExtraTime extraTime);

    @Delete("delete from extra_time where id=#{id}")
    Integer deleteById(Integer id);

    @Select("select * from extra_time where dept_id=#{deptId} and grade_id=#{gradeId} and term_id=#{termId}")
    List<ExtraTime> findByStu(Integer deptId, Integer gradeId, Integer termId);

    @Select("select * from extra_time where id=#{id}")
    Optional<ExtraTime> findById(Integer id);
}
