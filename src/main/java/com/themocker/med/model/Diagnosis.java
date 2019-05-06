package com.themocker.med.model;

public class Diagnosis {

  private long diaNo;
  private long diaRegNo;
  private long diaPuserNo;
  private String diaResult;
  private java.sql.Timestamp diaCreateTime;


  public long getDiaNo() {
    return diaNo;
  }

  public void setDiaNo(long diaNo) {
    this.diaNo = diaNo;
  }


  public long getDiaRegNo() {
    return diaRegNo;
  }

  public void setDiaRegNo(long diaRegNo) {
    this.diaRegNo = diaRegNo;
  }


  public long getDiaPuserNo() {
    return diaPuserNo;
  }

  public void setDiaPuserNo(long diaPuserNo) {
    this.diaPuserNo = diaPuserNo;
  }


  public String getDiaResult() {
    return diaResult;
  }

  public void setDiaResult(String diaResult) {
    this.diaResult = diaResult;
  }


  public java.sql.Timestamp getDiaCreateTime() {
    return diaCreateTime;
  }

  public void setDiaCreateTime(java.sql.Timestamp diaCreateTime) {
    this.diaCreateTime = diaCreateTime;
  }

}
