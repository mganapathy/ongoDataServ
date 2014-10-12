package com.ongo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hack.ongo.db.Appeal;
import com.hack.ongo.db.AppealType;
import com.hack.ongo.db.Blood;
import com.hack.ongo.db.Cause;
import com.hack.ongo.db.Education;
import com.hack.ongo.db.Fund;
import com.hack.ongo.db.HealthCare;
import com.hack.ongo.db.MongoDBDriver;
import com.hack.ongo.db.Organ;
import com.hack.ongo.db.Teaching;
import com.mongodb.BasicDBObject;
import com.ongo.model.Status;

@Controller
@RequestMapping("/appeal")
public class AppealController {

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addAppeal(@RequestBody Appeal appeal) {
		try {
//			MongoDBDriver dbDriver = new MongoDBDriver();
			MongoDBDriver.insertAppeal(appeal);
			return new Status(1, "Appeal added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			e.printStackTrace();
			return new Status(0, e.toString());
		}

	}


@RequestMapping(value = "/view", method = RequestMethod.GET)
public @ResponseBody
List<Appeal> getAppeals() {
	//Appeal appeal = new Appeal();
	List<Appeal> listOfAppeals = new ArrayList<Appeal>();
	try {
//		MongoDBDriver dbDriver = new MongoDBDriver();
//		user = (User) MongoDBDriver.getUser(id);
//		BasicDBObject basicDBObject = (BasicDBObject) MongoDBDriver.getAppeal();
		
//		appeal.setId(basicDBObject.getInt("_id"));
//		appeal.setDescription(basicDBObject.getString("description"));
//		appeal.setByWhom(basicDBObject.getString("ByWhom"));
		ArrayList< BasicDBObject > array = MongoDBDriver.getAppeal();
		
		for(BasicDBObject obj : array){
			Appeal appeal = new Appeal();
			appeal.setId(Integer.parseInt(obj.getString("_id")));
			appeal.setDescription(obj.getString("description"));
			appeal.setByWhom(obj.getString("byWhom"));
			appeal.setCity(obj.getString("city"));
			appeal.setState(obj.getString("state"));
			appeal.setStatus(obj.getString("status"));
				String cstr = obj.getString("cause");
				
				
				JsonParser parser = new JsonParser();
				JsonObject json = (JsonObject)parser.parse(cstr);
				JsonObject edu = json.getAsJsonObject("education");
				JsonObject hc = json.getAsJsonObject("healthcare");
				JsonObject type;
				if(edu != null){
					type = edu.getAsJsonObject("type");
				}
				else{
					type = hc.getAsJsonObject("type");
				}
				JsonObject fund = null,organ = null,teaching = null,blood = null;
				if(type != null){
					fund = type.getAsJsonObject("fund");
					organ = type.getAsJsonObject("organ");
					teaching = type.getAsJsonObject("teaching");
					blood = type.getAsJsonObject("blood");
				}
				Teaching t = new Teaching();
				Fund f = new Fund();
				Organ o = new Organ();
				Blood b = new Blood();
				if(teaching != null){
					t.setSkill(teaching.get("skill").toString());
					t.setVolunteer_no(Integer.parseInt(teaching.get("volunteer_no").toString()));
				} 
				if(fund != null){
					f.setAmount(1000);
					f.setBeneficiery(fund.get("beneficiery").toString());
				}
				if(organ != null){
					o.setName_of_org(organ.get("name_of_org").toString());
					o.setSpecification(organ.get("specification").toString());
				}
				if(blood != null){
					if(blood.get("blood_group") != null && blood.get("units")!= null){
						b.setBlood_group(blood.get("blood_group").toString());
						b.setUnits(blood.get("units").toString());
					}
					
				}
				
				AppealType at = new AppealType();
				at.setBlood(b);
				at.setFund(f);
				at.setOrgan(o);
				at.setTeaching(t);
				
				Education eduObj = new Education();
				HealthCare hcObj = new HealthCare();
				eduObj.setType(at);
				hcObj.setType(at);

				Cause causeObj = new Cause();
				causeObj.setEducation(eduObj);
				causeObj.setHealthCare(hcObj);
				
			appeal.setCause(causeObj);
			
			listOfAppeals.add(appeal);
		}
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return listOfAppeals;
}

}


