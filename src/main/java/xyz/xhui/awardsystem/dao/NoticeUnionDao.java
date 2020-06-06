package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.NoticeUnion;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface NoticeUnionDao {
    @Select("select * from notice_union where dept_id=#{deptId} order by create_time desc limit ${pageNum*pageSize}, #{pageSize}")
    List<NoticeUnion> findPageByDept(Integer deptId, Integer pageNum, Integer pageSize);

    @Select("select count(id) from notice_union where dept_id=#{deptId}")
    Integer findPageCountByDept(Integer deptId);

    @Delete("delete from notice_union where id=#{id}")
    Integer deleteById(Integer id);

    @Insert("insert into notice_union(`title`,`content`,`dept_id`,`submitter`,`create_time`) values(#{title},#{content},#{deptId},#{submitter},#{createTime})")
    Integer save(NoticeUnion noticeUnion);

    @Select("select * from notice_union where id=#{id} and dept_id=#{deptId}")
    Optional<NoticeUnion> findById(Integer id, Integer deptId);

    @Update("update notice_union set title=#{title}, content=#{content}, create_time=#{createTime} where id=#{id}")
    Integer update(NoticeUnion noticeUnion);
}
