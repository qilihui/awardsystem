package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.dao.TermDao;
import xyz.xhui.awardsystem.model.entity.SysDept;
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

    @Override
    public Integer sava(SysTerm sysTerm) {
        return termDao.save(sysTerm);
    }

    @Override
    @Transactional
    public Integer deletes(Integer[] ids) throws EntityFieldException {
        Integer count = 0;
        for (Integer id : ids) {
            Optional<SysTerm> termOptional = this.findById(id);
            termOptional.orElseThrow(() -> {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new EntityFieldException("id:" + id + " 不存在");
            });
            count += termDao.deleteById(id);
        }
        return count;
    }
}
