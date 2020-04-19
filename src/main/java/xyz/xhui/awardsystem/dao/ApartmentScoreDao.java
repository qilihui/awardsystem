package xyz.xhui.awardsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.xhui.awardsystem.model.entity.ApartmentScore;

public interface ApartmentScoreDao extends JpaRepository<ApartmentScore, Integer> {
}
