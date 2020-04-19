package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.model.entity.SysApartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {

    SysApartment save(SysApartment apartment);

    List<SysApartment> findAll();

    Optional<SysApartment> findById(Integer id);

    Boolean deleteById(Integer id);
}
