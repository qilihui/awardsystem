package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;
import xyz.xhui.awardsystem.model.entity.SysUserUnion;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserUnionMybatisDao {
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
}
