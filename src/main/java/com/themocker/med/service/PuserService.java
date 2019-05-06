package com.themocker.med.service;

import com.themocker.med.dao.PuserDao;
import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Puser;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuserService {

    @Autowired
    PuserDao puserDao;




    public Puser getPuserByAccountAndPassword(String account,String password){
            return puserDao.selectByPasswordandAccount(account,password);
    }

    public int updatePuserPassword(Puser puser){
        return  puserDao.updatePassword(puser);
    }
    public int updatePuser(Puser puser){
        return  puserDao.update(puser);
    }
}
