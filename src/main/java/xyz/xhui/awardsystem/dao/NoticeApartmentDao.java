package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.NoticeApartment;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface NoticeApartmentDao {
    @Select("select * from notice_apartment where apartment_id=#{apartmentId} order by create_time desc limit ${pageNum*pageSize}, #{pageSize}")
    List<NoticeApartment> findPageByApartment(Integer apartmentId, Integer pageNum, Integer pageSize);

    @Select("select count(id) from notice_apartment where apartment_id=#{apartmentId}")
    Integer findPageCountByApartment(Integer apartmentId);

    @Delete("delete from notice_apartment where id=#{id}")
    Integer deleteById(Integer id);

    @Insert("insert into notice_apartment(`title`,`content`,`apartment_id`,`submitter`,`create_time`) values(#{title},#{content},#{apartmentId},#{submitter},#{createTime})")
    Integer save(NoticeApartment noticeApartment);

    @Select("select * from notice_apartment where id=#{id} and apartment_id=#{apartmentId}")
    Optional<NoticeApartment> findById(Integer id, Integer apartmentId);

    @Update("update notice_apartment set title=#{title}, content=#{content}, create_time=#{createTime} where id=#{id}")
    Integer update(NoticeApartment noticeApartment);
}
