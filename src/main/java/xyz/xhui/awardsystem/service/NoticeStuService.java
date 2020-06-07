package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.NoticeDto;
import xyz.xhui.awardsystem.model.dto.PageDto;

import java.util.List;

public interface NoticeStuService {
    PageDto<List<NoticeDto>> findAll(Integer pageNum, Integer pageSize) throws UnknownException;
}
