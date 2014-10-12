package com.hack.ongo.db;

public class NGO {
	private String organisationName;
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCoordinatorName() {
		return coordinatorName;
	}
	public void setCoordinatorName(String coordinatorName) {
		this.coordinatorName = coordinatorName;
	}
	public String getCoordinatorEmailId() {
		return coordinatorEmailId;
	}
	public void setCoordinatorEmailId(String coordinatorEmailId) {
		this.coordinatorEmailId = coordinatorEmailId;
	}
	public String getCoordinatorMobileNo() {
		return coordinatorMobileNo;
	}
	public void setCoordinatorMobileNo(String coordinatorMobileNo) {
		this.coordinatorMobileNo = coordinatorMobileNo;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public String[] getCauses() {
		return causes;
	}
	public void setCauses(String[] causes) {
		this.causes = causes;
	}
	public String getContactUs() {
		return contactUs;
	}
	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String[] getAppeals() {
		return appeals;
	}
	public void setAppeals(String[] appeals) {
		this.appeals = appeals;
	}
	public String[] getVolunteers() {
		return volunteers;
	}
	public void setVolunteers(String[] volunteers) {
		this.volunteers = volunteers;
	}
	public String[] getEvents() {
		return events;
	}
	public void setEvents(String[] events) {
		this.events = events;
	}
	private String userName;
	private String password;
	private String coordinatorName;
	private String coordinatorEmailId;
	private String coordinatorMobileNo;
	private String aboutUs;
	private String causes[];
	private String contactUs;
	private String location;
	private String appeals[];
	private String volunteers[];
	private String events[];
	
	
}
