package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserHouseparent;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserHouseparentMybatisDao {
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
}
