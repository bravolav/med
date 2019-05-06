package com.themocker.med.service;

import com.themocker.med.dao.DepartmentDao;
import com.themocker.med.dao.RegisterDao;
import com.themocker.med.model.Department;
import com.themocker.med.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;



    public String selectDepNameByDepNo(int id) {
        return departmentDao.selectDepNameByDepNo(id);
    }

    public int selectDepHosNoByDepNo(int id) {
        return departmentDao.selectDepHosNoByDepNo(id);
    }

    public List<Department> selectDepByHosNo(int id){
        return  departmentDao.selectDepByHosNo(id);
    }
}


