package com.themocker.med.controller;


import com.themocker.med.model.Diagnosis;
import com.themocker.med.model.Doctor;
import com.themocker.med.model.Puser;
import com.themocker.med.model.Register;
import com.themocker.med.util.TimeUitl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "diagnosis")
public class DiagnosisController {

    @RequestMapping(value = "dialist",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView dialist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();

        Puser loginuser = new Puser();
        loginuser = (Puser) session.getAttribute("loginuser");

        List<Diagnosis> diagnoses = registerService.getRegByPuserNo((int)loginuser.getPuserNo());

        List<Map<String,Object>> reglistersList = new ArrayList<Map<String,Object>>();

        for(int i = 0 ;i<registers.size();i++){
            Register theRegister = registers.get(i);
            Doctor doctor = doctorService.selectDocByDocNo((int)theRegister.getRegDocNo());

            String depName = departmentService.selectDepNameByDepNo((int)doctor.getDocDepNo());
            String hosName = hospitalService.selectHosNameByHosNo(departmentService.selectDepHosNoByDepNo((int)doctor.getDocDepNo()));

            Map<String, Object> mapReglister = new HashMap<String, Object>(){{
                put("dephosName",hosName+"-"+depName);
                put("regDocName",doctor.getDocName());
                put("regNo",theRegister.getRegNo());
                put("regCreateTime", TimeUitl.getTime(theRegister.getRegCreateTime()));
                put("regTime",TimeUitl.getTime(theRegister.getRegTime()));
                int status = (int)theRegister.getRegStatus();
                switch (status){
                    case 0:  put("regStatus","未出诊");
                        put("cancelRegHref","/register/regCancel?regNo="+theRegister.getRegNo());
                        break;
                    case 1:  put("regStatus","已出诊");
                        put("cancelRegHref","");
                        break;
                    case 2:  put("regStatus","已取消");
                        put("cancelRegHref","");
                        break;
                    default:break;
                }

            }};
            reglistersList.add(mapReglister);

        }
        model.addObject("regsterList",reglistersList);


        model.setViewName("diagnosis");
        return model;
    }
}
