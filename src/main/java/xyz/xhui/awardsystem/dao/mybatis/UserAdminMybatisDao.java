package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.entity.SysUserAdmin;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserAdminMybatisDao {
    @Select("select * from sys_user_admin")
    @ResultMap("userAdminMap")
    List<SysUserAdmin> findAll();

    @Select("select * from sys_user_admin where id = #{id} limit 1")
    @ResultMap("userAdminMap")
    Optional<SysUserAdmin> findById(Integer id);

    @Select("select * from sys_user_admin where user_id = #{id} limit 1")
    @ResultMap("userAdminMap")
    Optional<SysUserAdmin> findSysUserAdminByUser_Id(Integer id);

    @Delete("delete from sys_user_admin where id = #{id}")
    Integer deleteById(Integer id);
}
