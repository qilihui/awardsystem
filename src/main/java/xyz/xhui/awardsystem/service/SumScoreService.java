package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.SumScoreDto;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;

public interface SumScoreService {
    SumScoreDto getByTutor(SumScoreVo sumScoreVo) throws UnknownException;
}
