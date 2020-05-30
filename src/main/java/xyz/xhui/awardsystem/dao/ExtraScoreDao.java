package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ExtraScore;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ExtraScoreDao {
    @Select("select * from extra_score where dept_id=#{deptId} and grade_id=#{gradeId}")
    List<ExtraScore> findByDeptGrade(Integer deptId, Integer gradeId);

    @Insert("insert into extra_score(`score`,`remark`,`path`,`time_id`,`term_id`,`stu_id`,`dept_id`,`grade_id`) values(#{score},#{remark},#{path},#{timeId},#{termId},#{stuId},#{deptId},#{gradeId})")
    Integer save(ExtraScore extraScore);

    @Select("select status from extra_score where stu_id=#{stuId} and term_id=#{termId} and time_id=#{timeId} limit 1")
    Integer getStuStatus(Integer stuId, Integer termId, Integer timeId);

    @Select("select * from extra_score where stu_id=#{stuId} and term_id=#{termId} and time_id=#{timeId} limit 1")
    Optional<ExtraScore> findByStu(Integer stuId, Integer termId, Integer timeId);
}
