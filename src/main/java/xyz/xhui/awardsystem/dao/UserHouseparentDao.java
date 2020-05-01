package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserHouseparentDao {
    Integer updateInfo(UserInfoDto userInfoDto);

    @ResultMap("userHouseparentMap")
    @Select("select * from sys_user_houseparent")
    List<SysUserHouseparent> findAll();

    @ResultMap("userHouseparentMap")
    @Select("select * from sys_user_houseparent where id = #{id} limit 1")
    Optional<SysUserHouseparent> findById(Integer id);

    @ResultMap("userHouseparentMap")
    @Select("select * from sys_user_houseparent where user_id = #{id} limit 1")
    Optional<SysUserHouseparent> findSysUserHouseparentByUser_Id(Integer id);

    @Delete("delete from sys_user_houseparent where id = #{id}")
    Integer deleteById(Integer id);

    @Insert("insert into sys_user_houseparent(`user_id`,`apartment_id`) values(#{user.id}, #{apartmentId})")
    Integer save(SysUserHouseparent userHouseparent);

    @Select("select * from sys_user_houseparent where id=#{id} limit 1")
    Optional<SysUserHouseparent> findByApartmentId(Integer id);
}
