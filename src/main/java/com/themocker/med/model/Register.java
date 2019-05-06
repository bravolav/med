package com.themocker.med.model;


public class Register {

  private long regNo;
  private long regDocNo;
  private long regPuserNo;
  private java.sql.Timestamp regTime;
  private java.sql.Timestamp regCreateTime;
  private long regStatus;


  public long getRegNo() {
    return regNo;
  }

  public void setRegNo(long regNo) {
    this.regNo = regNo;
  }


  public long getRegDocNo() {
    return regDocNo;
  }

  public void setRegDocNo(long regDocNo) {
    this.regDocNo = regDocNo;
  }


  public long getRegPuserNo() {
    return regPuserNo;
  }

  public void setRegPuserNo(long regPuserNo) {
    this.regPuserNo = regPuserNo;
  }


  public java.sql.Timestamp getRegTime() {
    return regTime;
  }

  public void setRegTime(java.sql.Timestamp regTime) {
    this.regTime = regTime;
  }


  public java.sql.Timestamp getRegCreateTime() {
    return regCreateTime;
  }

  public void setRegCreateTime(java.sql.Timestamp regCreateTime) {
    this.regCreateTime = regCreateTime;
  }


  public long getRegStatus() {
    return regStatus;
  }

  public void setRegStatus(long regStatus) {
    this.regStatus = regStatus;
  }

}
