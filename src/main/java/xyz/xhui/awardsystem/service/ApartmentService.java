package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysApartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {

    SysApartment save(String name) throws EntityFieldException;

    List<SysApartment> findAll();

    Optional<SysApartment> findById(Integer id);

    Integer deleteById(Integer id) throws EntityFieldException;

    SysApartment updatAapartment(SysApartment apartment) throws EntityFieldException;

    Integer deleteApartments(Integer[] ids) throws EntityFieldException;
}
