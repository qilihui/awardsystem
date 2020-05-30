package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.entity.ExtraScore;

public interface ExtraScoreService {
    Integer save(ExtraScore extraScore) throws UnknownException;
    ExtraScore findByStu(Integer termId, Integer timeId) throws UnknownException;
}
