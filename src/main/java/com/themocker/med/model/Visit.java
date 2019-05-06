package com.themocker.med.model;


public class Visit {

  private long visNo;
  private long visDocNo;
  private long visDepNo;
  private java.sql.Timestamp visTime;


  public long getVisNo() {
    return visNo;
  }

  public void setVisNo(long visNo) {
    this.visNo = visNo;
  }


  public long getVisDocNo() {
    return visDocNo;
  }

  public void setVisDocNo(long visDocNo) {
    this.visDocNo = visDocNo;
  }


  public long getVisDepNo() {
    return visDepNo;
  }

  public void setVisDepNo(long visDepNo) {
    this.visDepNo = visDepNo;
  }


  public java.sql.Timestamp getVisTime() {
    return visTime;
  }

  public void setVisTime(java.sql.Timestamp visTime) {
    this.visTime = visTime;
  }

}
