package com.themocker.med.service;

import com.themocker.med.dao.HospiatlDao;
import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Hospital;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    HospiatlDao hospiatlDao;


    public  Hospital getById(int id){
            return hospiatlDao.selectByHosId(id);

    }

    public String selectHosNameByHosNo(int id){
        return hospiatlDao.selectHosNameByHosNo(id);
    }

    public List<Hospital> getAllHos(){
        return  hospiatlDao.selectAllHospital();
    }
}
