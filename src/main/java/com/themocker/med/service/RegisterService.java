package com.themocker.med.service;

import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RegisterService {

    @Autowired
    RegisterDao registerDao;


    public List<Register> getRegByPuserNo(int id){
        return  registerDao.selectRegByDepNo(id);
    }

    public int selectDocNoByRegNo(int id){
        return registerDao.selectDocNoByRegNo(id);
    }
}
