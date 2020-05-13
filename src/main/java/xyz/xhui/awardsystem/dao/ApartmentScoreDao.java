package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;
import xyz.xhui.awardsystem.model.entity.SysTerm;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ApartmentScoreDao {
    @Select("select * from apartment_score where apartment_id=#{apartmentId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc limit ${pageNum*pageSize}, #{pageSize}")
    List<ApartmentScore> findAllByPagenumAndPagesize(Integer apartmentId, Integer pageNum, Integer pageSize, SysTerm term);

    @Select("select count(*) from apartment_score where apartment_id=#{apartmentId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime}")
    Integer findCountAllApartmentId(Integer apartmentId, SysTerm term);

    @Insert("insert into apartment_score(`apartment_id`, `room`, `bed`, `score`, `remark`, `create_time`) values(#{apartmentId}, #{room}, #{bed}, #{score}, #{remark}, #{createTime})")
    Integer save(ApartmentScore apartmentScore);

    @Select("select * from apartment_score where id=#{id}")
    Optional<ApartmentScore> findById(Integer id);

    @Delete("delete from apartment_score where id=#{id}")
    Integer deleteById(Integer id);

    @Select("select * from apartment_score where apartment_id=#{apartmentId} and room=#{room} and bed=#{bed} order by id desc")
    List<UnionScore> findOneByStuId(SysUserStu stu);
}
