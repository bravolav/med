package com.themocker.med.controller;


import com.themocker.med.model.Diagnosis;
import com.themocker.med.model.Doctor;
import com.themocker.med.model.Puser;
import com.themocker.med.model.Register;
import com.themocker.med.service.*;
import com.themocker.med.util.TimeUitl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RegisterService registerService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DiagnosisService diagnosisService;


    @RequestMapping(value = "dialist",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView dialist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();

        Puser loginuser = new Puser();
        loginuser = (Puser) session.getAttribute("loginuser");

        List<Diagnosis> diagnoses = diagnosisService.getDiaByPuserNo((int)loginuser.getPuserNo());

        List<Map<String,Object>> diagnosesList = new ArrayList<Map<String,Object>>();

        for(int i = 0 ;i<diagnoses.size();i++){
            Diagnosis thediagnosis = diagnoses.get(i);

            int docNo = registerService.selectDocNoByRegNo((int)thediagnosis.getDiaRegNo());
            Doctor doctor = doctorService.selectDocByDocNo(docNo);

            String depName = departmentService.selectDepNameByDepNo((int)doctor.getDocDepNo());
            String hosName = hospitalService.selectHosNameByHosNo(departmentService.selectDepHosNoByDepNo((int)doctor.getDocDepNo()));

            Map<String, Object> mapDiag = new HashMap<String, Object>(){{
                put("diaHosDepName",hosName+"-"+depName);
                put("diaDocName",doctor.getDocName());
                put("diagRegNo",thediagnosis.getDiaRegNo());
                put("diagCreateTime",TimeUitl.getTime(thediagnosis.getDiaCreateTime()));
                put("diagResult", thediagnosis.getDiaResult());
            }};
            diagnosesList.add(mapDiag);
        }
        model.addObject("diagList",diagnosesList);
        model.setViewName("diagnosis");
        return model;
    }
}
