package com.hack.ongo.db;

import java.util.Date;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Event")
public class Event {
	
	private String id;
	private String eventTitle;
	private String eventOwner;
	private String eventDesc;
	private String[] Volunteers;
	private String startDate;
	private String endDate;
	public void setId(String id) {
		this.id = id;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventOwner() {
		return eventOwner;
	}
	public void setEventOwner(String eventOwner) {
		this.eventOwner = eventOwner;
	}
	public String[] getVolunteers() {
		return Volunteers;
	}
	public void setVolunteers(String[] volunteers) {
		Volunteers = volunteers;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getId() {
		return id;
	}
	
}
