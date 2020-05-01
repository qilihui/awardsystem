package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserStu;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserTutorDao {
    Integer updateInfo(UserInfoDto userInfoDto);

    @ResultMap("userTutorMap")
    @Select("select * from sys_user_tutor")
    List<SysUserTutor> findAll();

    @ResultMap("userTutorMap")
    @Select("select * from sys_user_tutor where id = #{id} limit 1")
    Optional<SysUserTutor> findById(Integer id);

    @ResultMap("userTutorMap")
    @Select("select * from sys_user_tutor where user_id = #{id} limit 1")
    Optional<SysUserTutor> findSysUserTutorByUser_Id(Integer id);

    @Delete("delete from sys_user_tutor where id = #{id}")
    Integer deleteById(Integer id);

    @Insert("insert into sys_user_tutor(`user_id`, `dept_id`, `grade_id`) values(#{user.id}, #{deptId}, #{gradeId})")
    Integer save(SysUserTutor sysUserTutor);
}
