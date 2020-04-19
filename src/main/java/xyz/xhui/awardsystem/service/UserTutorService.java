package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysUserTutor;

import java.util.List;
import java.util.Optional;

public interface UserTutorService {
    /**
     * 查询所有辅导员详细信息
     * @return
     */
    List<SysUserTutor> findAll();

    /**
     * 添加辅导员
     * @param userTutor
     * @return
     */
    SysUserTutor save(SysUserTutor userTutor) throws EntityFieldException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Optional<SysUserTutor> findById(Integer id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Boolean deleteById(Integer id);

    Optional<SysUserTutor> findBySysUserId(Integer id);
}
