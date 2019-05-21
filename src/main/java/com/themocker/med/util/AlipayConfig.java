package com.themocker.med.util;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016093000634043";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCq3aJ93TYV2LapYrxwaGODPBQYT0sowrh+PiCZbtjWfxX0M2TkB22pGs/sagNYqQWK/6IMPVnOVM+cADg8ikF92HcX+FvZDeZ7Ap7E7IIGuQftokmbz+eGU/0bYm4mMhn2j573NTPT8tgiIfX1XfJSz8T8yppv7JNsfhElc9LuXwMdjG+VFeWjuRBJD0BWgNCzjkEKIcMB2lUj30wMPcnvVVSC4KKcX8/M+CtPTsqiMk/l765L7pAI9gXf+DdwlH2a4cDOfcqu/D6H5jN1TWiHrfXtv3dvhadxUyjpKXcnRG8ZYykAZTVGbK2GZtwBKrc8hNcs/bWKwlos8yH4asM3AgMBAAECggEBAKhuQE5oMHeKoE0LGoyIKohkQb7Q81rjz/AKGCKHhVK0BhUqUGsD85J97rayrkfIncEuSzRbidp9+gVipgzvkK2YI5XKaefl7uwVYiLmrSopXWpfzsW8RRNCWb+T4NbgDn3hh6dSl5t3yJyk84sDJ+uACkPTCDgKcizBfO95ZFU6Tr+tysrCyy+EtViBzg3s0ZMpjDQuexUnbNySnJjx8WxAO0zndZYC1hl7LegDXW0Wj57phhRK6qoOP/8ccOyQLECMYs05LlSJVFx6flBZdFGqFhnwiHSHt+pR+peELu4ypco84xiaZD/ahBuMBtinUwkLI+wVgR9GNOIV3mxGNRkCgYEA6aCbkDoX8975jXWuQaDgVl5G1FjzDO1MF06ARdskYe8YJlZWj1UfPxyCpUcTJJMOZv4TqqjJ2dGHVQ6lWWo4uEjuDk2c/GE4Z+9ZCPxXb/LOk0bOwqBwbae3zyOHb2sPMtQhUpVAgksv6F/T4DpXZAL0KlW1JtKpF2vbr4c3fbsCgYEAuzpr5kKaeUxg7T8EIbRwRicgF/u6txCB+yJI3UJcyC+YHQfs391nYAXGLZe4Uf1K4DWxiddwTsVzXc5s3b5UKMOodvdIWiCjt6HlXiJmu+GHWw8+NN26bWzDFKg/J/AxHUNdQwiAtnm5/5Z8uD4DikHDYKlr/KLqvAfLX66vurUCgYEAp0EJ3ZkZaMeqlLE5DVblBC/+OSts5UZFATJ79obKZypKebe5xGdGNWJtzXEyrS5yUx12aujniP7OCgCwM3EuJhBgDPFXf/8B6LabYSl2V3jWlu1MPF2r33UAzFQ9M3DLsrHXIU4knqLBK3TfNFnQEpNixaQSSjbqQxA+eE0vVt0CgYEAll0X9Mf+uXXuzfpy/gKkvKKFct13GbiHO0JQlGnMi/U1S/NmeG+eqw5YF5umqyoqLSDSIarFBm4q9eZnpjpJ6qcgL9Lu53sZIMxDojMsoK1CgI4gXmLIa0MS9Q8eEZwkRaUpTEGS5XmjODIKngkn3KxuFb5a/H122WXFHmXzuZUCgYBt9HPefu8m7oWumPqDRj41S0QI9GnxyMYP60U4WGPwhMQQRyDKRItct8QkhjXnjY7H8JDob+ea+yHpqLy4BD1fETI4qsWC1sLPa+NXgkJgh0h5SwFRY2+s5xMwl0rjvMIH7uezcDgFDUpAjgXXXdFLn3x+C42Hs/QtEcR8N6exDA==";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://203.195.141.246localhost:8080/Alipay/notifyUrl";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://203.195.141.246/Alipay/returnUrl";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsHV8bG518U+orVM6ACEEZhchtWnTcYXiU6lIRxDMKjAMsFoHAuNt49VdWSQEfa0MZ6D4e8IGIXcY7UY16hDGoFwv5gdqY2X4Cop9XQcxSrdHckMBT4rtF3Wjuw7MuokxkfbdJUdWAohBLKVWfvHzGVzY+c4/l6t7AWdKSanM1iJsDRQ73tELIqvxKST9GBxLJBkawMU4Zp9qVT2YB2lc1prJYQgXM8icl3kR9ad60SpUApLW04uWJhVemnCKXBTzz7S/+U28rN6dYQRLeTQuAIFnRlg2BkHLDvx6n48oJ+Zm9RrEgyqHOicX5pwAJFT9AcpzgcaPg88VJ9Yk/cZ6oQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
