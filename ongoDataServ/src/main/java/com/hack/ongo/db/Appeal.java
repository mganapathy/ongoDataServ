package com.hack.ongo.db;

import java.util.List;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Appeal")
public class Appeal {
    private String description;
    private int id;
    private Cause cause;
    private String byWhom;
    private Boolean isNGO;
    private String status;
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String city;
    private String state;
    
	public Boolean getIsNGO() {
		return isNGO;
	}
	public void setIsNGO(Boolean isNGO) {
		this.isNGO = isNGO;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cause getCause() {
		return cause;
	}
	public void setCause(Cause cause) {
		this.cause = cause;
	}
	public String getByWhom() {
		return byWhom;
	}
	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}
    
    
}