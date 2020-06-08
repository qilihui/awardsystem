package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;

import java.util.List;

public interface ApartmentScoreService {
    PageDto<List<ScoreDto>> findAll(Integer pageNum, Integer pageSize, Integer termId, Integer week) throws UnknownException;

    Integer save(ApartmentScore apartmentScore) throws UnknownException;

    Integer deletes(Integer[] ids) throws EntityFieldException;

    Integer saves(ApartmentScoreDto[] apartmentScoreDtos) throws EntityFieldException;

    List<ScoreDto> findByStuId(Integer termId) throws EntityFieldException, UnknownException;

    List<ScoreDto> findByNowWeek() throws UnknownException;

    PageDto<List<ScoreDto>> findByStuIdByTutor(Integer pageNum, Integer pageSize, Integer termId, Integer week) throws EntityFieldException, UnknownException;
}
