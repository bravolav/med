package com.themocker.med.service;

import com.themocker.med.dao.DepartmentDao;
import com.themocker.med.dao.DoctorDao;
import com.themocker.med.model.Department;
import com.themocker.med.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorDao doctorDao;


    public Doctor selectDocByDocNo(int id){
        return doctorDao.selectDocByDocNo(id);
    }

    public List<Doctor> selectDocByDepNo(int id){
        return  doctorDao.selectDocByDepNo(id);
    }
}


