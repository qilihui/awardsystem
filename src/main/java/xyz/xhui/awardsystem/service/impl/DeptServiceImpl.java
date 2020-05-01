package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.dao.DeptDao;
import xyz.xhui.awardsystem.model.entity.SysDept;
import xyz.xhui.awardsystem.service.DeptService;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptDao deptDao;

    @Override
    public Integer save(String name) throws EntityFieldException {
        SysDept dept = new SysDept();
        dept.setName(name);
        if (deptDao.findSysDeptByName(dept.getName()).isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return deptDao.save(dept);
    }

    @Override
    public List<SysDept> findAll() {
        return deptDao.findAll();
    }

    @Override
    public Optional<SysDept> findById(Integer id) {
        return deptDao.findById(id);
    }

//    @Override
//    @Transactional
//    public Boolean deleteById(Integer id) throws EntityFieldException {
//        Optional<SysDept> deptOptional = this.findById(id);
//        deptOptional.orElseThrow(() -> {
//            return new EntityFieldException("id:" + id + " 不存在");
//        });
//        deptDao.deleteById(id);
//        return true;
//    }

    @Override
    @Transactional
    public Integer deleteDepts(Integer[] ids) throws EntityFieldException {
        Integer count = 0;
        for (Integer id : ids) {
            Optional<SysDept> deptOptional = this.findById(id);
            deptOptional.orElseThrow(() -> {
                return new EntityFieldException("id:" + id + " 不存在");
            });
            count += deptDao.deleteSysDeptById(id);
        }
        return count;
    }

    @Override
    public Integer updateDept(SysDept dept) throws EntityFieldException {
        if (dept.getId() == null) {
            throw new EntityFieldException("缺少id字段");
        }
        if (dept.getName() == null || "".equals(dept.getName())) {
            throw new EntityFieldException("name不能为空");
        }
        if (deptDao.findSysDeptByName(dept.getName()).isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return deptDao.update(dept);
    }
}
