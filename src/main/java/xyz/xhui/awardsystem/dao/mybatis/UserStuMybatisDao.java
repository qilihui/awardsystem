package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserStuMybatisDao {
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
}
