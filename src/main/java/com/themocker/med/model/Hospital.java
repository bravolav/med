package com.themocker.med.model;

public class Hospital {

  private long hosNo;
  private String hosName;
  private String hosLocal;
  private String hosDesc;
  private String hosCall;


  public long getHosNo() {
    return hosNo;
  }

  public void setHosNo(long hosNo) {
    this.hosNo = hosNo;
  }


  public String getHosName() {
    return hosName;
  }

  public void setHosName(String hosName) {
    this.hosName = hosName;
  }


  public String getHosLocal() {
    return hosLocal;
  }

  public void setHosLocal(String hosLocal) {
    this.hosLocal = hosLocal;
  }


  public String getHosCall() {
    return hosCall;
  }

  public void setHosCall(String hosCall) {
    this.hosCall = hosCall;
  }

  public String getHosDesc() {
    return hosDesc;
  }

  public void setHosDesc(String hosDesc) {
    this.hosDesc = hosDesc;
  }
}
