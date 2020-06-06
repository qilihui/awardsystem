package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.UnknownException;
import xyz.xhui.awardsystem.model.dto.PageDto;
import xyz.xhui.awardsystem.model.entity.NoticeApartment;

import java.util.List;

public interface NoticeApartmentService {
    PageDto<List<NoticeApartment>> findAll(Integer pageNum, Integer pageSize) throws UnknownException;

    NoticeApartment findById(Integer id) throws UnknownException;

    Integer deleteById(Integer id);

    Integer save(NoticeApartment noticeApartment) throws UnknownException;

    Integer update(NoticeApartment noticeApartment) throws UnknownException;
}
