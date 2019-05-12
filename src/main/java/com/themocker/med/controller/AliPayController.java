package com.themocker.med.controller;

import com.themocker.med.model.Payorder;
import com.themocker.med.model.Puser;
import com.themocker.med.service.PayOrderService;
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
@RequestMapping(value = "Alipay")
public class AliPayController {

    @Autowired
    PayOrderService payOrderService;


    @RequestMapping(value = "goPay",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView goPay(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        return model;
    }


    @RequestMapping(value = "notifyUrl",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView notifyUrl(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        return model;
    }


    @RequestMapping(value = "returnUrl",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView returnUrl(String payNo,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();

        model.setViewName("payConfirm");
        return model;
    }
    }
