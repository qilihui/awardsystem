package xyz.xhui.awardsystem.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MysqlInfoDao {
    @Select("select version()")
    String getVersion();
}
