package com.themocker.med.controller;

import com.themocker.med.model.Hospital;
import com.themocker.med.service.HospitalService;
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
@RequestMapping(value = "hospital")
public class HospitalController {

   @Autowired
   private HospitalService hospitalService;


    @RequestMapping(value = "hoslist",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView hoslist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        List<Hospital> hospitals= new ArrayList<Hospital>();
        hospitals = hospitalService.getAllHos();
        List<Map<String,Object>> hospitalsList = new ArrayList<Map<String,Object>>();
        for(int i = 0 ;i<hospitals.size();i++){
            Hospital theHos = hospitals.get(i);
            Map<String, Object> maoHos = new HashMap<String, Object>(){{
                put("hosHref", "/hospital/hosDetail?hosNo="+theHos.getHosNo());
                put("hosImg", "/images/icon/"+theHos.getHosName()+".jpg");
                if(theHos.getHosNo() == 1) {
                    put("hosClass", "总院");
                    put("classType","weui-mark-rt bg-blue");
                }
                else {
                    put("hosClass", "分院");
                    put("classType","weui-mark-rt bg-orange");
                }
                put("hosName",theHos.getHosName());
            }};
            hospitalsList.add(maoHos);

        }
        model.addObject("hosList",hospitalsList);
        model.setViewName("hospital");
        return model;
    }

    @RequestMapping(value = "hosDetail",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView hosDetail(String hosNo,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        System.out.println(hosNo);
        Hospital hospital = new Hospital();
        hospital = hospitalService.getById(Integer.parseInt(hosNo));
        model.addObject("imgUrl","/images/icon/"+hospital.getHosName()+".jpg");
        model.addObject("hosName",hospital.getHosName());
        model.addObject("hosDesc",hospital.getHosDesc());
        model.addObject("hosLocal",hospital.getHosLocal());
        model.addObject("hosCall",hospital.getHosCall());
        model.setViewName("hospital_detail");
        return model;
    }


}
