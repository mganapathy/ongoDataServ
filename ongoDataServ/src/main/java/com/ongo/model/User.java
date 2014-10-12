package com.ongo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {
    private int id;
    private String name;
    private String role;
    private boolean isEmployee;
	private String mPhone;
    private String lPhone;
    private String password;
    private String cuase;
    private String currentAddress;
    private String permenantAddress;
    private String appealInfo;
    private String appealCategory;
    private String appealDate;
    private String appealExpiryDate;
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean getIsEmployee() {
        return isEmployee;
    }
    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }
    public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getlPhone() {
		return lPhone;
	}
	public void setlPhone(String lPhone) {
		this.lPhone = lPhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCuase() {
		return cuase;
	}
	public void setCuase(String cuase) {
		this.cuase = cuase;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPermenantAddress() {
		return permenantAddress;
	}
	public void setPermenantAddress(String permenantAddress) {
		this.permenantAddress = permenantAddress;
	}
	public String getAppealInfo() {
		return appealInfo;
	}
	public void setAppealInfo(String appealInfo) {
		this.appealInfo = appealInfo;
	}
	public String getAppealCategory() {
		return appealCategory;
	}
	public void setAppealCategory(String appealCategory) {
		this.appealCategory = appealCategory;
	}
	public String getAppealDate() {
		return appealDate;
	}
	public void setAppealDate(String appealDate) {
		this.appealDate = appealDate;
	}
	public String getAppealExpiryDate() {
		return appealExpiryDate;
	}
	public void setAppealExpiryDate(String appealExpiryDate) {
		this.appealExpiryDate = appealExpiryDate;
	}

}