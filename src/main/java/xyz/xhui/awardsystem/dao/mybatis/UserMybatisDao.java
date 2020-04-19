package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.SysUserDto;

@Mapper
@Repository
public interface UserMybatisDao {

    Integer updateEmailAndRealName(SysUserDto userDto);

    Integer updatePassword(Integer id, String newPassword);

    @Select("select password from sys_user where id = #{id}")
    String selectPassword(Integer id);
}
