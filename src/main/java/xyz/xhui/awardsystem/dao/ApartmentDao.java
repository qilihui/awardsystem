package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.SysApartment;

public interface ApartmentDao extends JpaRepository<SysApartment, Integer> {
}
