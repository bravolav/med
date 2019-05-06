package com.themocker.med.controller;


import com.themocker.med.model.Puser;
import com.themocker.med.service.PuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "user")
public class PuserController {
    @Autowired
    private PuserService puserService;

    @RequestMapping(value = "mine",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView minehome(){
        ModelAndView model = new ModelAndView();
        model.setViewName("mine");
        return model;
    }

    @RequestMapping(value = "person",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView person(){
        ModelAndView model = new ModelAndView();
        model.setViewName("person");
        return model;
    }

    @RequestMapping(value = "password",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView password(){
        ModelAndView model = new ModelAndView();
        model.setViewName("password");
        return model;
    }

    @RequestMapping(value = "personModify",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView personModify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        Puser loginuser = new Puser();

        loginuser = (Puser) session.getAttribute("loginuser");


        String pusername = request.getParameter("pusername");
        String pusersex = request.getParameter("pusersex");
        String puserage = request.getParameter("puserage");
        String pusercall = request.getParameter("pusercall");


        loginuser.setPuserName(pusername);
        loginuser.setPuserSex(pusersex);
        loginuser.setPuserAge(Integer.parseInt(puserage));
        loginuser.setPuserCall(pusercall);

        if(puserService.updatePuser(loginuser)==1) {
            model.addObject("perMSG", "修改成功！");
            session.removeAttribute("loginuser");
            session.setAttribute("loginuser",loginuser);
            model.setViewName("person");
            return model;
        }

        model.addObject("perMSG","修改失败！请重新修改");
        model.setViewName("person");
        return model;



}


    @RequestMapping(value = "passwordModify",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView passwordModify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        Puser loginuser = new Puser();
        loginuser = (Puser) session.getAttribute("loginuser");


        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpassword = request.getParameter("confirmnewpassword");

        if(!oldpassword.equals(loginuser.getPuserPassword())){
            model.addObject("pasMSG","初始密码不正确！");
            model.setViewName("password");
            return model;
        }else if(!newpassword.equals(confirmnewpassword)){
            model.addObject("pasMSG","两次密码不一致！");
            model.setViewName("password");
            return model;
        }else if(oldpassword.equals(newpassword)){
            model.addObject("pasMSG","新密码与旧密码相同！");
            model.setViewName("password");
            return model;
        }

        loginuser.setPuserPassword(newpassword);

        if(puserService.updatePuserPassword(loginuser)==1) {
            model.addObject("pasMSG", "修改成功！返回登录界面");
            session.removeAttribute("loginuser");
            model.setViewName("login");
            return model;
        }

        model.addObject("pasMSG","修改失败！请重新修改");
        model.setViewName("password");
        return model;
    }



}
