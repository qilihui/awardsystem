package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.UserInfoDto;

@Mapper
@Repository
public interface UserTutorMybatisDao {
    Integer updateInfo(UserInfoDto userInfoDto);
}
