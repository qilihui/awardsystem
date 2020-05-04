package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
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

    @Select("select * from union_score where dept_id=#{deptId} limit ${pageNum*pageSize}, #{pageSize}")
    List<UnionScore> findByPageAndDeptId(Integer deptId, Integer pageNum, Integer pageSize);

    @Select("select count(*) from union_score where dept_id=#{deptId}")
    Integer findCountByPageAndDeptId(Integer deptId);

    @Insert("insert into union_score(`stu_id`, `score`, `remark`, `create_time`, `union_id`, `dept_id`) values(#{stuId}, #{score}, #{remark}, #{createTime}, #{unionId}, #{deptId})")
    Integer save(UnionScore unionScore);

    @Select("select * from union_score where id=#{id}")
    Optional<UnionScore> findById(Integer id);

    @Delete("delete from union_score where id=#{id}")
    Integer deleteById(Integer id);
}
