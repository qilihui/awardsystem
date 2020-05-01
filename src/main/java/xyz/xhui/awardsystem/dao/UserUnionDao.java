package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUser;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserUnionDao {
    Integer updateInfo(UserInfoDto userInfoDto);

    @ResultMap("userUnionMap")
    @Select("select * from sys_user_union")
    List<SysUserUnion> findAll();

    @ResultMap("userUnionMap")
    @Select("select * from sys_user_union where id = #{id} limit 1")
    Optional<SysUserUnion> findById(Integer id);

    @ResultMap("userUnionMap")
    @Select("select * from sys_user_union where user_id = #{id} limit 1")
    Optional<SysUserUnion> findSysUserUnionByUser_Id(Integer id);

    @Delete("delete from sys_user_union where id = #{id}")
    Integer deleteById(Integer id);

    @Insert("insert into sys_user_union(`user_id`, `dept_id`) values(#{user.id}, #{deptId})")
    Integer save(SysUserUnion userUnion);

    @Select("select u.* from sys_user u, sys_user_union n where u.id=n.user_id and n.id=#{unionId}")
    Optional<SysUser> findSysUserByUnoinId(Integer unionId);
}
