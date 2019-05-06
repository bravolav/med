package com.themocker.med.service;

import com.themocker.med.dao.DiagnosisDao;
import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Diagnosis;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisDao diagnosisDao;


    public List<Diagnosis> getDiaByPuserNo(int id){
        return  diagnosisDao.selectDiaByPuserNo(id);
    }
}
