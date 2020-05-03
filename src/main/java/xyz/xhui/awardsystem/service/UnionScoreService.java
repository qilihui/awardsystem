package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.dto.UnionScoreDto;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;

public interface UnionScoreService {

    List<UnionScore> findAll();

    List<ScoreDto> findAll(Integer pageNum, Integer pageSize) throws UnknownException;

//    Optional<UnionScore> findById(Integer id);

//    Boolean deleteById(Integer id);

    Integer save(ScoreDto scoreDto) throws EntityFieldException, UnknownException;

    Integer saves(ScoreDto[] scoreDtos) throws EntityFieldException;

    Integer deletes(Integer[] ids) throws EntityFieldException;
}
