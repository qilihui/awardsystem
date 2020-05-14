package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.*;

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

    @Select("select * from apartment_score where apartment_id=#{stu.apartmentId} and room=#{stu.room} and bed=#{stu.bed} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc")
    List<UnionScore> findByStuId(SysUserStu stu, SysTerm term);

    @Select("select sc.*, stu.user_id as stu_id from apartment_score sc, sys_user_stu stu where sc.apartment_id=stu.apartment_id and sc.room=stu.room and sc.bed=stu.bed and stu.dept_id=#{tutor.deptId} and stu.grade_id=#{tutor.gradeId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime} order by id desc limit ${pageNum*pageSize}, #{pageSize}")
    List<UnionScore> findByTutor(Integer pageNum,  Integer pageSize, SysUserTutor tutor, SysTerm term);

    @Select("select count(*) from apartment_score sc, sys_user_stu stu where sc.apartment_id=stu.apartment_id and sc.room=stu.room and sc.bed=stu.bed and stu.dept_id=#{tutor.deptId} and stu.grade_id=#{tutor.gradeId} and create_time>=#{term.beginTime} and create_time<=#{term.endTime}")
    Integer findCountByTutor(SysUserTutor tutor, SysTerm term);
}
