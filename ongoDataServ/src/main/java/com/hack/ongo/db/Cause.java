package com.hack.ongo.db;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

public class Cause {

	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public HealthCare getHealthCare() {
		return healthCare;
	}
	public void setHealthCare(HealthCare healthCare) {
		this.healthCare = healthCare;
	}
	private Education education;
	private HealthCare healthCare;
	
}
