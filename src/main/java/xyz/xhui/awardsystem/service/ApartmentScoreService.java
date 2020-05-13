package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.dto.ScoreDto;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;

import java.util.List;

public interface ApartmentScoreService {
    PageDto<List<ApartmentScore>> findAll(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException;

    Integer save(ApartmentScore apartmentScore) throws UnknownException;

    Integer deletes(Integer[] ids) throws EntityFieldException;

    Integer saves(ApartmentScoreDto[] apartmentScoreDtos) throws EntityFieldException;

    List<ScoreDto> findOneByStuId() throws EntityFieldException;
}
