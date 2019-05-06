package com.themocker.med.model;


public class Doctor {

  private long docNo;
  private long docDepNo;
  private String docName;
  private String docVisTime;
  private String docClass;


  public long getDocNo() {
    return docNo;
  }

  public void setDocNo(long docNo) {
    this.docNo = docNo;
  }


  public long getDocDepNo() {
    return docDepNo;
  }

  public void setDocDepNo(long docDepNo) {
    this.docDepNo = docDepNo;
  }


  public String getDocName() {
    return docName;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }





  public String getDocClass() {
    return docClass;
  }

  public void setDocClass(String docClass) {
    this.docClass = docClass;
  }

  public String getDocVisTime() {
    return docVisTime;
  }

  public void setDocVisTime(String docVisTime) {
    this.docVisTime = docVisTime;
  }
}
