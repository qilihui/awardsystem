package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ApartmentScoreDao {
    @Select("select * from apartment_score where apartment_id=#{apartmentId} limit ${pageNum*pageSize}, #{pageSize}")
    List<ApartmentScore> findAllByPagenumAndPagesize(Integer apartmentId, Integer pageNum, Integer pageSize);

    @Select("select count(*) from apartment_score where apartment_id=#{apartmentId}")
    Integer findCountAllApartmentId(Integer apartmentId);

    @Insert("insert into apartment_score(`apartment_id`, `room`, `bed`, `score`, `remark`, `create_time`) values(#{apartmentId}, #{room}, #{bed}, #{score}, #{remark}, #{createTime})")
    Integer save(ApartmentScore apartmentScore);

    @Select("select * from apartment_score where id=#{id}")
    Optional<ApartmentScore> findById(Integer id);

    @Delete("delete from apartment_score where id=#{id}")
    Integer deleteById(Integer id);
}
