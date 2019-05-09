package com.themocker.med.controller;

import com.themocker.med.model.Payorder;
import com.themocker.med.model.Puser;
import com.themocker.med.service.PayOrderService;
import com.themocker.med.util.TimeUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "payOrder")
public class PayorderController {

    @Autowired
    PayOrderService payOrderService;


    @RequestMapping(value = "paylist",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView paylist(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();

        Puser loginuser = new Puser();
        loginuser = (Puser) session.getAttribute("loginuser");

        List<Payorder> payorders = payOrderService.selectPayOrderByPuserNo((int)loginuser.getPuserNo());

        List<Map<String,Object>> payordersList = new ArrayList<Map<String,Object>>();

        for(int i = 0 ;i<payorders.size();i++){
            Payorder thePayOrder = payorders.get(i);
            Map<String, Object> mapPayOrder = new HashMap<String, Object>(){{
                put("payAmount","¥"+thePayOrder.getPayAmount());
                put("payContent",thePayOrder.getPayContent());
                put("payCreateTime",TimeUitl.getTime(thePayOrder.getPayCreateTime()));

               // put("payStatus",thePayOrder.getPayStatus());
                int status = (int)thePayOrder.getPayStatus();
                switch (status){
                    case 0:  put("payStatus","未支付");
                        put("payHref","/alipay/goPay?regNo="+thePayOrder.getPayNo());
                        put("hreftext","在线支付");
                        put("btnclass","weui-form-preview__btn weui-form-preview__btn_primary");
                        put("disabled","false");
                        put("payFinishTime", "无");
                        break;
                    case 1:  put("payStatus","已支付");
                        put("payHref","");
                        put("hreftext","已支付");
                        put("btnclass","weui-form-preview__btn weui-form-preview__btn_default");
                        put("disabled","true");
                        put("payFinishTime", TimeUitl.getTime(thePayOrder.getPayFinishTime()));
                        break;
                    default:break;
                }

            }};
            payordersList.add(mapPayOrder);

        }
        model.addObject("payOrderList",payordersList);
        model.setViewName("payOrder");
        return model;
    }

}
