package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.model.entity.ApartmentScore;

import java.util.List;
import java.util.Optional;

public interface ApartmentScoreService {

    List<ApartmentScore> findAll();

    Optional<ApartmentScore> findById(Integer id);

    Boolean deleteById(Integer id);

    ApartmentScore save(ApartmentScore apartmentScore);
}
