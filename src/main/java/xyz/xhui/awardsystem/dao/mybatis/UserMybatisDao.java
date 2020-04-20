package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.config.sysenum.RoleEnum;
import xyz.xhui.awardsystem.model.dto.SysUserDto;
import xyz.xhui.awardsystem.model.entity.SysUser;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserMybatisDao {

    Integer updateEmailAndRealName(SysUserDto userDto);

    Integer updatePassword(Integer id, String newPassword);

    @Select("select password from sys_user where id = #{id}")
    String selectPassword(Integer id);

    @Select("select * from sys_user")
    List<SysUser> findAll();

    @Select("select * from sys_user limit ${pageNum*pageSize}, #{pageSize}")
    List<SysUser> findAllByPagenumAndPagesize(Integer pageNum, Integer pageSize);

    @Select("select * from sys_user where id = #{id} limit 1")
    Optional<SysUser> findById(Integer id);

    @Select("select * from sys_user where username = #{username} limit 1")
    Optional<SysUser> findSysUserByUsernameEquals(String username);

    @Select("select count(*) from sys_user")
    Integer findAllCount();

    Integer findAllCountByRole(RoleEnum roleEnum);

    @Delete("delete from  sys_user where id = #{id}")
    Integer deleteById(Integer id);
}
