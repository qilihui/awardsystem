package xyz.xhui.awardsystem.service;

import org.springframework.web.bind.annotation.RequestParam;
import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeUnion;

import java.util.List;

public interface NoticeUnionService {
    PageDto<List<NoticeUnion>> findAll(Integer pageNum, Integer pageSize) throws UnknownException;

    NoticeUnion findById(Integer id) throws UnknownException;

    Integer deleteById(Integer id);

    Integer save(NoticeUnion noticeUnion) throws UnknownException;

    Integer update(NoticeUnion noticeUnion) throws UnknownException;
}
