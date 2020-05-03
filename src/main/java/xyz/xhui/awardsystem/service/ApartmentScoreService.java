package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.ApartmentScoreDto;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;

import java.util.List;

public interface ApartmentScoreService {
    List<ApartmentScore> findAll(Integer pageNum, Integer pageSize) throws UnknownException;

    Integer save(ApartmentScore apartmentScore) throws UnknownException;

    Integer deletes(Integer[] ids) throws EntityFieldException;

    Integer saves(ApartmentScoreDto[] apartmentScoreDtos) throws EntityFieldException;
}
