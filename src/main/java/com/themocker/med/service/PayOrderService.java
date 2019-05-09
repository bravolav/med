package com.themocker.med.service;

import com.themocker.med.dao.PayOrderDao;
import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Payorder;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayOrderService {

    @Autowired
    PayOrderDao payOrderDao;


     public List<Payorder> selectPayOrderByPuserNo(int id){
        return  payOrderDao.selectPayOrderByPuserNo(id);
    }

    public int addPayOrder(Payorder payorder){
         return payOrderDao.addPayOrder(payorder);
    }
}
