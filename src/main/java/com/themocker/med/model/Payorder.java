package com.themocker.med.model;


public class Payorder {

  private long payNo;
  private long payPuserNo;
  private String payNum;
  private String payContent;
  private double payAmount;
  private java.sql.Timestamp payCreateTime;
  private java.sql.Timestamp payFinishTime;
  private long payStatus;


  public long getPayNo() {
    return payNo;
  }

  public void setPayNo(long payNo) {
    this.payNo = payNo;
  }


  public long getPayPuserNo() {
    return payPuserNo;
  }

  public void setPayPuserNo(long payPuserNo) {
    this.payPuserNo = payPuserNo;
  }


  public String getPayContent() {
    return payContent;
  }

  public void setPayContent(String payContent) {
    this.payContent = payContent;
  }


  public double getPayAmount() {
    return payAmount;
  }

  public void setPayAmount(double payAmount) {
    this.payAmount = payAmount;
  }


  public java.sql.Timestamp getPayCreateTime() {
    return payCreateTime;
  }

  public void setPayCreateTime(java.sql.Timestamp payCreateTime) {
    this.payCreateTime = payCreateTime;
  }


  public java.sql.Timestamp getPayFinishTime() {
    return payFinishTime;
  }

  public void setPayFinishTime(java.sql.Timestamp payFinishTime) {
    this.payFinishTime = payFinishTime;
  }


  public long getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(long payStatus) {
    this.payStatus = payStatus;
  }

  public String getPayNum() {
    return payNum;
  }

  public void setPayNum(String payNum) {
    this.payNum = payNum;
  }
}
