package com.themocker.med.controller;


import com.themocker.med.model.Puser;
import com.themocker.med.service.PuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private PuserService puserService;


    @RequestMapping(value = "login",method = {RequestMethod.POST, RequestMethod.GET})
    public String mine() {
        return "login";
    }

    @RequestMapping(value = "userLogin",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model=new ModelAndView();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Puser loginPuser = new Puser();
        loginPuser  = puserService.getPuserByAccountAndPassword(account,password);
        if(null!=loginPuser){
            session.setAttribute("loginuser",loginPuser);
            model.setViewName("mine");
        }else{
            model.addObject("MSG","用户名或密码错误");
            model.setViewName("login");
        }

        return model;
    }

    @RequestMapping(value = "register",method = {RequestMethod.POST, RequestMethod.GET})
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "registerFinish",method = {RequestMethod.POST, RequestMethod.GET})
    public String registerFinish(@Valid Puser puser, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }else if (puserService.selectPuserById((int) puser.getPuserNo()) != null) {
            return "该账号已经被注册";

        }else {
                puserService.addPuser(puser);
                return "注册成功!";
            }


    }

    @RequestMapping(value = "loginOut",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model=new ModelAndView();
        model.setViewName("login");
        if(session != null){
            session.removeAttribute("loginuser");
        }

        return model;
    }


}
