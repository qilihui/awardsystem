package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.SumScoreDto;
import xyz.xhui.awardsystem.model.vo.SumScoreVo;

import java.util.List;

public interface SumScoreService {
    List<SumScoreDto> getByTutor(SumScoreVo sumScoreVo) throws UnknownException;
}
