package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.vo.ExtraScoreVo;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ExtraScoreDao {
    @Select("select * from extra_score where dept_id=#{deptId} and grade_id=#{gradeId} and term_id=#{termId} and time_id=#{timeId}")
    List<ExtraScore> findByTutor(Integer deptId, Integer gradeId, Integer termId, Integer timeId);

    @Insert("insert into extra_score(`score`,`remark`,`path`,`create_time`,`time_id`,`term_id`,`stu_id`,`dept_id`,`grade_id`) values(#{score},#{remark},#{path},#{createTime},#{timeId},#{termId},#{stuId},#{deptId},#{gradeId})")
    Integer save(ExtraScore extraScore);

    @Select("select status from extra_score where stu_id=#{stuId} and term_id=#{termId} and time_id=#{timeId} limit 1")
    Integer getStuStatus(Integer stuId, Integer termId, Integer timeId);

    @Select("select * from extra_score where stu_id=#{stuId} and term_id=#{termId} and time_id=#{timeId} limit 1")
    Optional<ExtraScore> findByStu(Integer stuId, Integer termId, Integer timeId);

    @Select("select * from extra_score where id=#{id} and dept_id=#{deptId} and grade_id=#{gradeId} and time_id=#{timeId}")
    Optional<ExtraScore> findById(Integer id, Integer deptId, Integer gradeId, Integer timeId);

    @Delete("delete from extra_score where id=#{id} and dept_id=#{deptId} and grade_id=#{gradeId} and time_id=#{timeId}")
    Integer deleteById(Integer id, Integer deptId, Integer gradeId, Integer timeId);

    @Update("update extra_score set status=#{pass} where id=#{id} and dept_id=#{deptId} and grade_id=#{gradeId} and time_id=#{timeId}")
    Integer passById(Integer id, Integer deptId, Integer gradeId, Integer timeId, Integer pass);
}
