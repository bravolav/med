package com.themocker.med.controller;

import com.themocker.med.model.*;
import com.themocker.med.service.*;
import com.themocker.med.util.TimeUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PayOrderService payOrderService;

    @RequestMapping(value = "reglist",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reglist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {



        ModelAndView model = new ModelAndView();
        List<Hospital> hospitals = new ArrayList<Hospital>();
        hospitals = hospitalService.getAllHos();

        List<Map<String,Object>> hospitalsList = new ArrayList<Map<String,Object>>();
        for(int i = 0 ;i<hospitals.size();i++){
            Hospital theHos = hospitals.get(i);
            Map<String, Object> mapHos = new HashMap<String, Object>(){{
                put("hosName",theHos.getHosName());
            }};
            hospitalsList.add(mapHos);

        }
        model.addObject("hosList",hospitalsList);




        for(int i = 0 ;i<hospitals.size();i++){
            Hospital tHos = hospitals.get(i);
            List<Map<String,Object>> departmentsList = new ArrayList<Map<String,Object>>();
            List<Department> departments = new ArrayList<>();
            departments = departmentService.selectDepByHosNo((int)tHos.getHosNo());
            for(int j = 0 ;j<departments.size();j++) {
                Department theDep = departments.get(j);
                Map<String, Object> mapDep = new HashMap<String, Object>() {{
                    put("depName", theDep.getDepName());
                    put("depHref", "/register/regDocList?depNo="+theDep.getDepNo()+"&depName="+theDep.getDepName());
                }};
                departmentsList.add(mapDep);
            }
            model.addObject("deplist"+i,departmentsList);
        }

        model.setViewName("regList");
        return model;
    }


    @RequestMapping(value = "regDocList",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView regDocList(String depNo,String depName ,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        List<Doctor> doctors = new ArrayList<>();
        doctors = doctorService.selectDocByDepNo(Integer.parseInt(depNo));
        model.addObject("depName",depName);
        List<Map<String,Object>> doctorsList = new ArrayList<Map<String,Object>>();
        for(int i = 0 ;i<doctors.size();i++){
            Doctor theDoctor = doctors.get(i);
            Map<String, Object> mapDoc = new HashMap<String, Object>(){{
                put("docName",theDoctor.getDocName());
                put("docHref","/register/regConfirm?docNo="+theDoctor.getDocNo());
                put("docClass",theDoctor.getDocClass());
                put("docVisTime",theDoctor.getDocVisTime());
            }};
           doctorsList.add(mapDoc);

        }
        model.addObject("docList",doctorsList);

        model.setViewName("regDocList");
        return model;
    }


    @RequestMapping(value = "regConfirm",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView regConfirm(String docNo,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        Puser loginuser = (Puser)session.getAttribute("loginuser");
        Doctor doctor = doctorService.selectDocByDocNo(Integer.parseInt(docNo));

        String depName = departmentService.selectDepNameByDepNo((int)doctor.getDocDepNo());
        String hosName = hospitalService.selectHosNameByHosNo(departmentService.selectDepHosNoByDepNo((int)doctor.getDocDepNo()));
        String userName = loginuser.getPuserName();
        Long userNo = loginuser.getPuserNo();
        double amount = 0;
        String docClass = doctor.getDocClass();
        switch (docClass){
            case "主治医师": amount = 10.00;break;
            case "副主任医师": amount = 15.00;break;
            case "主任医师": amount = 20.00;break;
            default: break;
        }

        model.addObject("docNo",docNo);
        model.addObject("docName",doctor.getDocName());
        model.addObject("depName",depName);
        model.addObject("hosName",hosName);
        model.addObject("userName",userName);
        model.addObject("userNo",userNo);
        model.addObject("amount",amount);

        model.setViewName("regConfirm");
        return model;
    }


    @RequestMapping(value = "regSuccess",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView regSuccess(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        ModelAndView model = new ModelAndView();
        Register register = new Register();
        Payorder payorder = new Payorder();

        String docNo = request.getParameter("docNo");
        String regTime = request.getParameter("regTime");
        String puserNo = request.getParameter("puserNo");
        String payAmount = request.getParameter("payamount");
        String payContent = "挂号费";


        register.setRegDocNo(Long.parseLong(docNo));
        register.setRegTime(TimeUitl.StringToTimestamp(regTime));
        register.setRegPuserNo(Long.parseLong(puserNo));
        register.setRegStatus(0);

        payorder.setPayAmount(Double.parseDouble(payAmount));
        payorder.setPayContent(payContent);
        payorder.setPayPuserNo(Long.parseLong(puserNo));
        payorder.setPayStatus(0);

        ;

        if(registerService.addRegister(register) == 1 &&
                payOrderService.addPayOrder(payorder) == 1){
            model.setViewName("reg-success");
            return model;
        }else {
            model.setViewName("page-failure");
            return model;
        }


    }




    @RequestMapping(value = "regOrder",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView regOrder(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        Puser loginuser = new Puser();
        loginuser = (Puser) session.getAttribute("loginuser");

        List<Register> registers = registerService.getRegByPuserNo((int)loginuser.getPuserNo());

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



        model.setViewName("regOrder");
        return model;
    }



    @RequestMapping(value = "regCheckTime",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String ,Object> regCheckTime(String docNo,String regTime) throws Exception{

        Map<String,Object> resultMap = new HashMap<String, Object>();
        Doctor regdoctor = doctorService.selectDocByDocNo(Integer.parseInt(docNo));
        String docVisTime = regdoctor.getDocVisTime();
        System.out.println(docVisTime);
        System.out.println(TimeUitl.getWeek(regTime));
        if(!docVisTime.equals(TimeUitl.getWeek(regTime))){
            resultMap.put("result", "就诊时间与医生出诊时间不符！");
            return resultMap;
        }
        resultMap.put("result","SUCCESS");
        return resultMap;
    }




    @RequestMapping(value = "regCancel",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView regCancel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {



        ModelAndView model = new ModelAndView();
        model.setViewName("pags");
        return model;
    }



}
