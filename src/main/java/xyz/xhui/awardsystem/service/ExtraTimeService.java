package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.entity.ExtraTime;

import java.util.List;

public interface ExtraTimeService {
    List<ExtraTime> findAll(Integer termId) throws UnknownException;
    Integer save(ExtraTime extraTime) throws UnknownException;
    Integer deleteById(Integer id);
}
