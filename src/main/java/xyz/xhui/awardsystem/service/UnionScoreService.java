package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.UnionScoreDto;
import xyz.xhui.awardsystem.model.entity.UnionScore;

import java.util.List;
import java.util.Optional;

public interface UnionScoreService {

    List<UnionScore> findAll();

    Optional<UnionScore> findById(Integer id);

    Boolean deleteById(Integer id);

    UnionScore save(UnionScoreDto unionScoreDto) throws EntityFieldException, UnknownException;
}
