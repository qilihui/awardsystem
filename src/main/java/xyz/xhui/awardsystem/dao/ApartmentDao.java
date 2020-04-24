package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysApartment;

import java.util.Optional;

public interface ApartmentDao extends JpaRepository<SysApartment, Integer> {
    Optional<SysApartment> findSysApartmentByName(String name);

    Integer deleteSysApartmentById(Integer id);
}
