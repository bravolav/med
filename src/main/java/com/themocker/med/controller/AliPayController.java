package com.themocker.med.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.themocker.med.model.Payorder;
import com.themocker.med.model.Puser;
import com.themocker.med.service.PayOrderService;
import com.themocker.med.util.AlipayConfig;
import com.themocker.med.util.TimeUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.themocker.med.util.AlipayConfig.ALIPAY_PUBLIC_KEY;
import static com.themocker.med.util.AlipayConfig.CHARSET;


@Controller
@RequestMapping(value = "Alipay")
public class AliPayController {

    @Autowired
    PayOrderService payOrderService;


    @RequestMapping(value = "goPay",method = {RequestMethod.POST, RequestMethod.GET})
    public void goPay(HttpServletRequest request, HttpServletResponse httpResponse, HttpSession session) {
        ModelAndView model = new ModelAndView();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址

        String out_trade_no = request.getParameter("payNo");
        String total_amount = request.getParameter("payAmount");
        total_amount = total_amount.substring(1);
        String subject = request.getParameter("payContent");
        System.out.println(out_trade_no+"  "+total_amount+"   "+subject);


        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\""+out_trade_no+"\"," +
                " \"total_amount\":\""+total_amount+"\"," +
                " \"subject\":\""+subject+"\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        try {
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "notifyUrl",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView notifyUrl(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return model;
    }


    @RequestMapping(value = "returnUrl",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView returnUrl(String payNo,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView model = new ModelAndView();

        model.setViewName("payConfirm");
        return model;
    }
    }
