package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;

@Mapper
@Repository
public interface UnionScoreDao {
    @Select("select * from union_score limit ${pageNum*pageSize}, #{pageSize}")
    @ResultMap("unionScoreMaapper")
    List<UnionScore> findAllByPagenumAndPagesize(Integer pageNum, Integer pageSize);

    @Select("select * from union_score")
    List<UnionScore> findAll();

    @Select("select * from union_score limit ${pageNum*pageSize}, #{pageSize}")
    List<UnionScore> findByPage(Integer pageNum, Integer pageSize);
}
