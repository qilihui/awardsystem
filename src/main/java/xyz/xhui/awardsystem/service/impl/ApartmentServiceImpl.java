package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.xhui.awardsystem.config.exception.EntityFieldException;
import xyz.xhui.awardsystem.dao.ApartmentDao;
import xyz.xhui.awardsystem.model.entity.SysApartment;
import xyz.xhui.awardsystem.service.ApartmentService;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    ApartmentDao apartmentDao;

    @Override
    public Integer save(String name) throws EntityFieldException {
        SysApartment apartment = new SysApartment();
        apartment.setName(name);
        if (apartmentDao.findSysApartmentByName(apartment.getName()).isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return apartmentDao.save(apartment);
    }

    @Override
    public List<SysApartment> findAll() {
        return apartmentDao.findAll();
    }

    @Override
    public Optional<SysApartment> findById(Integer id) {
        return apartmentDao.findById(id);
    }

//    @Override
//    public Integer deleteById(Integer id) throws EntityFieldException {
//        Integer count = 0;
//        Optional<SysApartment> apartmentOptional = this.findById(id);
//        apartmentOptional.orElseThrow(() -> {
//            return new EntityFieldException("id:" + id + " 不存在");
//        });
//        return apartmentDao.deleteSysApartmentById(id);
//    }

    @Override
    public Integer updatAapartment(SysApartment apartment) throws EntityFieldException {
        if (apartment.getId() == null) {
            throw new EntityFieldException("缺少id字段");
        }
        if (apartment.getName() == null || "".equals(apartment.getName())) {
            throw new EntityFieldException("name不能为空");
        }
        if (apartmentDao.findSysApartmentByName(apartment.getName()).isPresent()) {
            throw new EntityFieldException("name已存在");
        }
        return apartmentDao.update(apartment);
    }

    @Override
    @Transactional
    public Integer deleteApartments(Integer[] ids) throws EntityFieldException {
        Integer count = 0;
        for (Integer id : ids) {
            Optional<SysApartment> apartmentOptional = this.findById(id);
            apartmentOptional.orElseThrow(() -> {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new EntityFieldException("id:" + id + " 不存在");
            });
            count += apartmentDao.deleteSysApartmentById(id);
        }
        return count;
    }
}
