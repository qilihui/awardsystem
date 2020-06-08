package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ExamScore;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ExamScoreDao {
    @Insert("insert into exam_score(`stu_id`,`score`,`remark`,`count`,`term_id`,`dept_id`,`grade_id`) values(#{stuId},#{score},#{remark},#{count},#{termId},#{deptId},#{gradeId})")
    Integer save(ExamScore examScore);

    @Select("select id from exam_score where stu_id=#{stuId} and term_id=#{termId} limit 1")
    Optional<ExamScore> existByStuIdAndTermId(Integer stuId, Integer termId);

    @Select("select count(id) from exam_score where id=#{id} and term_id=#{termId} limit 1")
    Integer existById(Integer id, Integer termId);

    @Delete("delete from exam_score where id=#{id}")
    Integer deleteById(Integer id);

    @Select("select * from exam_score where term_id=#{termId} and dept_id=#{deptId} limit ${pageNum*pageSize}, #{pageSize}")
    List<ExamScore> findPageAll(Integer pageNum, Integer pageSize, Integer termId, Integer deptId);

    @Select("select count(id) from exam_score where term_id=#{termId} and dept_id=#{deptId}")
    Integer findPageAllCount(Integer pageNum, Integer pageSize, Integer termId, Integer deptId);

    @Select("select * from exam_score where term_id=#{termId} and dept_id=#{deptId} and grade_id=#{gradeId} order by score desc limit ${pageNum*pageSize}, #{pageSize}")
    List<ExamScore> findPageAllByTutor(Integer pageNum, Integer pageSize, Integer termId, Integer deptId, Integer gradeId);

    @Select("select count(id) from exam_score where term_id=#{termId} and dept_id=#{deptId} and grade_id=#{gradeId}")
    Integer findPageAllCountByTutor(Integer pageNum, Integer pageSize, Integer termId, Integer deptId, Integer gradeId);

    @Select("select * from exam_score where term_id=#{termId} and dept_id=#{deptId} and grade_id=#{gradeId} order by score desc")
    List<ExamScore> findAllByTutor(Integer termId, Integer deptId, Integer gradeId);
}
