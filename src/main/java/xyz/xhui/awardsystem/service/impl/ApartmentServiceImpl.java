package xyz.xhui.awardsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
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
    public SysApartment save(SysApartment dept) {
        return apartmentDao.save(dept);
    }

    @Override
    public List<SysApartment> findAll() {
        return apartmentDao.findAll();
    }

    @Override
    public Optional<SysApartment> findById(Integer id) {
        return apartmentDao.findById(id);
    }

    @Override
    public Boolean deleteById(Integer id) {
        try{
            apartmentDao.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
