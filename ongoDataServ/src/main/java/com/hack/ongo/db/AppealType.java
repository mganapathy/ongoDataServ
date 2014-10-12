package com.hack.ongo.db;

public class AppealType {
  private Teaching teaching;
  private Fund fund;
  private Organ organ;
  private Blood blood;
  public int flag;
public Teaching getTeaching() {
	return teaching;
}
public void setTeaching(Teaching teaching) {
	this.teaching = teaching;
	this.flag=0;
}
public Fund getFund() {
	return fund;
	
}
public void setFund(Fund fund) {
	this.fund = fund;
	this.flag=1;
}
public Organ getOrgan() {
	return organ;
}
public void setOrgan(Organ organ) {
	this.organ = organ;
	this.flag=2;
}
public Blood getBlood() {
	return blood;
}
public void setBlood(Blood blood) {
	this.blood = blood;
	this.flag=3;
}
  
}
