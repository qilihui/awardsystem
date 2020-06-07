package xyz.xhui.awardsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xyz.xhui.awardsystem.model.dto.NoticeDto;
import xyz.xhui.awardsystem.model.entity.SysUserStu;

import java.util.List;

@Mapper
@Repository
public interface NoticeStuDao {
    @Select("(SELECT id,title,content,submitter,create_time,'辅导员' as type from notice_tutor where dept_id=#{stu.deptId} and grade_id=#{stu.gradeId}) \n" +
            "UNION all (select id,title,content,submitter,create_time,'学生会' as type from  notice_union where dept_id=#{stu.deptId}) \n" +
            "UNION all (select id,title,content,submitter,create_time,'宿舍' as type from  notice_apartment where apartment_id=#{stu.apartmentId}) \n" +
            "ORDER BY create_time DESC limit ${pageNum*pageSize}, #{pageSize}")
    List<NoticeDto> findAll(SysUserStu stu, Integer pageNum, Integer pageSize);

    @Select("SELECT count(id) from ((SELECT id from notice_tutor where dept_id=#{deptId} and grade_id=#{gradeId})\n" +
            "UNION all (select id from notice_union where dept_id=#{deptId}) \n" +
            "UNION all (select id from  notice_apartment where apartment_id=#{apartmentId})) as r")
    Integer findCountAll(SysUserStu stu);
}
