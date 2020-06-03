package xyz.xhui.awardsystem.service;

import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.ExamScoreDto;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.ExamScore;
import xyz.xhui.awardsystem.model.entity.ExtraScore;
import xyz.xhui.awardsystem.model.vo.ExamScoreVo;

import java.util.List;

public interface ExamScoreService {
    Integer saves(ExamScoreVo scoreVo) throws EntityFieldException;

    PageDto<List<ExamScoreDto>> findAll(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException;

    Integer deletes(Integer[] ids, Integer termId) throws EntityFieldException;

    PageDto<List<ExamScoreDto>> findAllByTutor(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException;

    PageDto<List<ExamScoreDto>> findAllByStu(Integer pageNum, Integer pageSize, Integer termId) throws UnknownException;
}
