package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xhui.awardsystem.dao.TermDao;
import xyz.xhui.awardsystem.model.entity.SysTerm;
import xyz.xhui.awardsystem.service.TermService;

import java.util.List;
import java.util.Optional;

@Service
public class TermServiceImpl implements TermService {
    @Autowired
    private TermDao termDao;

    @Override
    public List<SysTerm> findAll() {
        return termDao.findAll();
    }

    @Override
    public Optional<SysTerm> findById(Integer id) {
        return termDao.findById(id);
    }
}
