package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.NoticeTutor;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface NoticeTutorDao {
    @Select("select * from notice_tutor where dept_id=#{deptId} and grade_id=#{gradeId} order by create_time desc limit ${pageNum*pageSize}, #{pageSize}")
    List<NoticeTutor> findPageByDeptAndGrade(Integer deptId, Integer gradeId, Integer pageNum, Integer pageSize);

    @Select("select count(id) from notice_tutor where dept_id=#{deptId} and grade_id=#{gradeId}")
    Integer findPageCountByDeptAndGrade(Integer deptId, Integer gradeId);

    @Delete("delete from notice_tutor where id=#{id}")
    Integer deleteById(Integer id);

    @Insert("insert into notice_tutor(`title`,`content`,`dept_id`,`grade_id`,`submitter`,`create_time`) values(#{title},#{content},#{deptId},#{gradeId},#{submitter},#{createTime})")
    Integer save(NoticeTutor noticeTutor);

    @Select("select * from notice_tutor where id=#{id} and dept_id=#{deptId} and grade_id=#{gradeId}")
    Optional<NoticeTutor> findById(Integer id, Integer deptId, Integer gradeId);

    @Update("update notice_tutor set title=#{title}, content=#{content}, create_time=#{createTime} where id=#{id}")
    Integer update(NoticeTutor noticeTutor);
}
