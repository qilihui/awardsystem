package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UnionScoreByTutorDto;
import xyz.xhui.awardsystem.model.entity.SysTerm;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UnionScoreDao {
    @Select("select * from union_score limit ${pageNum*pageSize}, #{pageSize}")
//    @ResultMap("unionScoreMaapper")
    List<UnionScore> findAllByPagenumAndPagesize(Integer pageNum, Integer pageSize);

    @Select("select * from union_score")
    List<UnionScore> findAll();

    @Select("select * from union_score where dept_id=#{deptId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc limit ${pageNum*pageSize}, #{pageSize}")
    List<UnionScore> findByPageAndDeptId(Integer deptId, Integer pageNum, Integer pageSize, SysTerm term);

    @Select("select count(*) from union_score where dept_id=#{deptId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime}")
    Integer findCountByPageAndDeptId(Integer deptId, SysTerm term);

    @Insert("insert into union_score(`stu_id`, `score`, `remark`, `create_time`, `union_id`, `dept_id`) values(#{stuId}, #{score}, #{remark}, #{createTime}, #{unionId}, #{deptId})")
    Integer save(UnionScore unionScore);

    @Select("select * from union_score where id=#{id}")
    Optional<UnionScore> findById(Integer id);

    @Delete("delete from union_score where id=#{id}")
    Integer deleteById(Integer id);

    @Select("select * from union_score where stu_id=#{id} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc")
    List<UnionScore> findByStuId(Integer id, SysTerm term);

    @Select("select sc.id, sc.union_id, sc.score, sc.create_time, sc.remark, user.username, user.real_name from union_score sc, sys_user_stu stu, sys_user user where sc.dept_id=#{tutor.deptId} and sc.dept_id=stu.dept_id and sc.stu_id=stu.id and stu.user_id=user.id and stu.grade_id=#{tutor.gradeId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc limit ${pageNum*pageSize}, #{pageSize}")
    List<UnionScoreByTutorDto> findByTutor(Integer pageNum, Integer pageSize, SysUserTutor tutor, SysTerm term);

    @Select("select count(sc.id) from union_score sc, sys_user_stu stu, sys_user user where sc.dept_id=#{tutor.deptId} and sc.dept_id=stu.dept_id and sc.stu_id=stu.id and stu.user_id=user.id and stu.grade_id=#{tutor.gradeId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} ")
    Integer findCountByTutor(SysUserTutor tutor, SysTerm term);
}
