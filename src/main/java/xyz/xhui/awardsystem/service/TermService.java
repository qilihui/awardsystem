package xyz.xhui.awardsystem.service;

import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.model.entity.SysTerm;

import java.util.List;
import java.util.Optional;

public interface TermService {
    List<SysTerm> findAll();
    Optional<SysTerm> findById(Integer id);
    Integer sava(SysTerm sysTerm);

    Integer deletes(Integer[] ids) throws EntityFieldException;
}
