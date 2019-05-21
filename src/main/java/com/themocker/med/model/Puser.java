package com.themocker.med.model;


import javax.validation.constraints.*;

public class Puser {

  private long puserNo;

  @NotEmpty(message="账号不能为空")
  private String puserAccount;

  @NotEmpty(message="密码不能为空")
  private String puserPassword;

  @NotEmpty(message="用户名不能为空")
  private String puserName;

  @NotEmpty(message="性别不能为空")
  @Pattern(regexp = "['男'|'女']",message = "性别格式为'男'或'女'")
  private String puserSex;

  @NotNull(message="年龄不能为空")
  @Min(value=0,message="年龄最小为0岁")
  @Max(value=110,message="年龄最大为110岁")
  private Integer puserAge;

  @NotEmpty(message="电话不能为空")
  @Size(max = 11,min = 11,message = "请输入正确的11位手机号码")
  private String puserCall;


  public long getPuserNo() {
    return puserNo;
  }

  public void setPuserNo(long puserNo) {
    this.puserNo = puserNo;
  }


  public String getPuserAccount() {
    return puserAccount;
  }

  public void setPuserAccount(String puserAccount) {
    this.puserAccount = puserAccount;
  }


  public String getPuserPassword() {
    return puserPassword;
  }

  public void setPuserPassword(String puserPassword) {
    this.puserPassword = puserPassword;
  }


  public String getPuserName() {
    return puserName;
  }

  public void setPuserName(String puserName) {
    this.puserName = puserName;
  }


  public String getPuserSex() {
    return puserSex;
  }

  public void setPuserSex(String puserSex) {
    this.puserSex = puserSex;
  }


  public Integer getPuserAge() {
    return puserAge;
  }

  public void setPuserAge(Integer puserAge) {
    this.puserAge = puserAge;
  }


  public String getPuserCall() {
    return puserCall;
  }

  public void setPuserCall(String puserCall) {
    this.puserCall = puserCall;
  }

}
