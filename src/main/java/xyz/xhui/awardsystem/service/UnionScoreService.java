package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;

public interface UnionScoreService {

    List<UnionScore> findAll();

    PageDto<List<ScoreDto>> findAll(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException;

    Integer save(ScoreDto scoreDto) throws EntityFieldException, UnknownException;

    Integer saves(ScoreDto[] scoreDtos) throws EntityFieldException;

    Integer deletes(Integer[] ids) throws EntityFieldException;

    List<ScoreDto> findOneByStuId() throws EntityFieldException, UnknownException;
}
