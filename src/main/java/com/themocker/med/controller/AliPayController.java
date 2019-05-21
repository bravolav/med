package com.themocker.med.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.themocker.med.model.Payorder;
import com.themocker.med.model.Puser;
import com.themocker.med.service.PayOrderService;
import com.themocker.med.util.AlipayConfig;
import com.themocker.med.util.TimeUitl;
import org.apache.log4j.Logger;
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

    private static Logger log =Logger.getLogger(AliPayController.class.getClass());

    @Autowired
    PayOrderService payOrderService;


    @RequestMapping(value = "goPay",method = {RequestMethod.POST, RequestMethod.GET})
    public void goPay(HttpServletRequest request, HttpServletResponse httpResponse, HttpSession session) {
        ModelAndView model = new ModelAndView();
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE); //获得初始化的AlipayClient
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

            ModelAndView modelAndView = new ModelAndView();
            return modelAndView;
    }


    @RequestMapping(value = "returnUrl",method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView returnUrl(String payNo,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

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


        //调用SDK验证签名
        boolean signVerified = false;

        signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, AlipayConfig.SIGNTYPE);

        ModelAndView model = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 修改订单状态为支付成功，已付款; 同时新增支付流水
            Payorder payorder = payOrderService.selectPayOrderByPayNo(Integer.parseInt(out_trade_no));
            payorder.setPayStatus(1);
            payorder.setPayNum(trade_no);

            payOrderService.updatePayOrder(payorder);

            log.info("********************** 支付成功(支付宝同步通知) **********************");
            log.info("* 订单号:"+out_trade_no);
            log.info("* 支付宝交易号:"+trade_no);
            log.info("* 实付金额"+total_amount);
            log.info("***************************************************************");
            model.addObject("out_trade_no", out_trade_no);
            model.addObject("trade_no", trade_no);
            model.addObject("total_amount", total_amount);

        }else {
            log.info("支付, 验签失败...");
            model.setViewName("alipayFail");
        }

        return model;
    }
    }
