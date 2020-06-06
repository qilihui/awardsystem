package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeTutor;

import java.util.List;

public interface NoticeTutorService {
    PageDto<List<NoticeTutor>> findAll(Integer pageNum, Integer pageSize) throws UnknownException;

    NoticeTutor findById(Integer id) throws UnknownException;

    Integer deleteById(Integer id);

    Integer save(NoticeTutor noticeTutor) throws UnknownException;

    Integer update(NoticeTutor noticeTutor) throws UnknownException;
}
