package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserStuDao {
    Integer updateInfo(UserInfoDto userInfoDto);

    @ResultMap("userStuMap")
    @Select("select * from sys_user_stu")
    List<SysUserStu> findAll();

    @ResultMap("userStuMap")
    @Select("select * from sys_user_stu where id = #{id} limit 1")
    Optional<SysUserStu> findById(Integer id);

    @ResultMap("userStuMap")
    @Select("select * from sys_user_stu where user_id = #{id} limit 1")
    Optional<SysUserStu> findSysUserStuByUser_Id(Integer id);

    @Delete("delete from sys_user_stu where id = #{id}")
    Integer deleteById(Integer id);

    @Insert("insert into sys_user_stu(`user_id`, `dept_id`, `grade_id`, `apartment_id`, `room`, `bed`) values(#{user.id}, #{deptId}, #{gradeId}, #{apartmentId}, #{room}, #{bed})")
    Integer save(SysUserStu userStu);

    @Select("select u.* from sys_user u, sys_user_stu s where u.id=s.user_id and s.id=#{stuId}")
    Optional<SysUser> findSysUserByStuId(Integer stuId);

    @Select("select s.*, u.*, u.id as id2 from sys_user u, sys_user_stu s where u.id=s.user_id and u.username=#{username}")
    @ResultMap("userStuMap2")
    Optional<SysUserStu> findStuByUsername(String username);
}
