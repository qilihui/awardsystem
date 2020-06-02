package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.vo.ExtraScoreVo;

import java.util.List;

public interface ExtraScoreService {
    Integer save(ExtraScore extraScore) throws UnknownException;

    ExtraScore findByStu(Integer termId, Integer timeId) throws UnknownException;

    List<ExtraScoreVo> findByTutor(Integer termId, Integer timeId) throws UnknownException;

    ExtraScoreVo findById(Integer id, Integer timeId) throws UnknownException;

    Integer deleteById(Integer id, Integer timeId) throws UnknownException;

    Integer passById(Integer id, Integer timeId, Integer pass) throws UnknownException;
}
