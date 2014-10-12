package com.hack.ongo.db;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.ongo.model.Status;
import com.hack.ongo.db.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MongoDBDriver {

	public static String insertUserData(User user) throws Exception {

		System.out.println("inside calling MongoDBDriver.insertUserData");
		MongoClient mongo = new MongoClient("localhost", 27017);
		Boolean userExists = false;
		String msg = "failure-user already existes";
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");

		try {
			DBCollection collection = db.getCollection("users");
			BasicDBObject document = new BasicDBObject();
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", user.getName());
			DBCursor cursor = collection.find(searchQuery);
			System.out.println("after find calling MongoDBDriver.insertUserData user.getName == " + user.getName());
			while (cursor.hasNext()) {
				System.out.println("cursor val == " + user.getName() + "cur == " + cursor.next());
				BasicDBObject obj = (BasicDBObject) cursor.next();
				String result = "";
				result += obj.getString("name");
				System.out.println("result val == " + result);
				if (result.equals(user.getName())) {
					System.out.println("User Name already found");
					userExists = true;
					mongo.close();
					return msg;
				}
			}
			if (userExists == false) {
				System.out.println("new user, insertUserData");
				DBObject doc = createDBObject(user);
				System.out.println("dbobject == " + doc);
				col.insert(doc);
				msg = "Success-user created";
				collection.insert(doc);
				System.out.println("insertUserData after insert" + msg);
				mongo.close();
				return msg;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mongo.close();
		return msg;
		/*
		 * // read example DBObject query =
		 * BasicDBObjectBuilder.start().add("_id", user.getId()).get(); DBCursor
		 * cursor = col.find(query); while (cursor.hasNext()) { DBObject object
		 * = cursor.next(); System.out.println(object); }
		 */
	}

	public static void updateUserDataById(int id, User newData) throws UnknownHostException {
		System.out.println("getUserDataById fun start mmmm id == " + id);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");
		BasicDBObject query = new BasicDBObject("_id", id);
		DBObject doc = createDBObject(newData);
		// DBObject newDocument = new BasicDBObject("_id", newData);
		// BasicDBObject updateObj = new BasicDBObject();
		// updateObj.put("$set", newDocument);
		col.update(query, doc);
	}

	public static DBObject searchUserDataById(int id) throws UnknownHostException {
		System.out.println("getUserDataById fun start mmmm id == " + id);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		BasicDBObject result = new BasicDBObject();
		DBCollection col = db.getCollection("users");
		BasicDBObject query = new BasicDBObject("_id", id);
		DBCursor cursor = col.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println(object);
				return object;
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	public static void deleteUserDataById(int id) throws UnknownHostException {
		System.out.println("deleteUserDataById fun start mmmm id == " + id);

		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");

		BasicDBObject deleteQuery = new BasicDBObject("_id", id);
		WriteResult result = col.remove(deleteQuery);

	}

	public static DBObject createDBObject(User user) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("_id", user.getId());
		docBuilder.append("name", user.getName());
		docBuilder.append("role", user.getRole());
		docBuilder.append("isEmployee", user.getIsEmployee());
		docBuilder.append("mPhone", user.getmPhone());
		docBuilder.append("lPhone", user.getlPhone());
		docBuilder.append("Password", user.getPassword());
		docBuilder.append("Cuase", user.getCuase());
		docBuilder.append("CurrentAddress", user.getCurrentAddress());
		docBuilder.append("PermenantAddress", user.getPermenantAddress());
		docBuilder.append("AppealInfo", user.getAppealInfo());
		docBuilder.append("AppealCategory", user.getAppealCategory());
		docBuilder.append("AppealDate", user.getAppealDate());
		docBuilder.append("AppealExpiryDate", user.getAppealExpiryDate());

		return docBuilder.get();
	}

	public static String validateLoginData(User user) {
		String msg = "failure-user invalid login credential";
		try {
			System.out.println("inside calling MongoDBDriver.validateLoginData");
			MongoClient mongo = new MongoClient("localhost", 27017);
			Boolean userCredenatial = false;
			DB db = mongo.getDB("hacktest");
			DBCollection col = db.getCollection("users");

			DBCollection collection = db.getCollection("users");
			BasicDBObject document = new BasicDBObject();
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", user.getName());
			DBCursor cursor = collection.find(searchQuery);
			System.out.println("after find calling MongoDBDriver.validateLoginData user.getName == " + user.getName()
					+ "pwd ==" + user.getPassword());
			while (cursor.hasNext()) {
				// if(cursor.length() > 0) {
				System.out.println("cursor val == " + user.getName() + "pwd == " + user.getPassword());
				BasicDBObject obj = (BasicDBObject) cursor.next();
				System.out.println("cursor val == " + user.getName() + "pwd == " + user.getPassword() + "obj" + obj);
				if (obj.getString("Password").equalsIgnoreCase(user.getPassword())) {
					String result = "";
					result += obj.getString("Password");
					System.out.println("result val == " + result + "obj.getString==" + obj.getString("Password"));
					// if (result.equals(user.getPassword())) {
					System.out.println("valid password found");
					userCredenatial = true;
					msg = "User valid login credential";
					mongo.close();
					return msg;
					// }
				}
			}

			if (userCredenatial == false) {
				System.out.println("invalid login data");
				return msg;
			}
		} catch (MongoException e) {
			e.printStackTrace();
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return msg;
		}
		return msg;
	}
	   public static DBObject createNGODBObject(NGO ngo_obj) {
	        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
	          
	        docBuilder.append("userName", ngo_obj.getUserName());
	        docBuilder.append("password", ngo_obj.getPassword());
	        docBuilder.append("organisationName", ngo_obj.getOrganisationName());
	        docBuilder.append("coordinatorName", ngo_obj.getCoordinatorName());
	        docBuilder.append("coordinatorEmailId", ngo_obj.getCoordinatorEmailId());
	        docBuilder.append("coordinatorMobileNo", ngo_obj.getCoordinatorMobileNo());
	        docBuilder.append("aboutUs", ngo_obj.getAboutUs());
	        docBuilder.append("causes", ngo_obj.getCauses());
	        docBuilder.append("contactUs", ngo_obj.getContactUs());
	        docBuilder.append("location",ngo_obj.getLocation());
	        return docBuilder.get();
	    }
	    
	    
	    public static DBObject getNGO(int id) throws UnknownHostException {
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("hacktest");
			DBCollection col = db.getCollection("NGOs");
			BasicDBObject query = new BasicDBObject("_id", id);
			
			DBCursor cursor = col.find(query);
			try {
			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println(object);
				return object;
			}
			}	finally {
				   cursor.close();
				}
			return null;

		}
	    
	    public static void insertNGO(NGO ngo) throws Exception	   {
	    //	System.out.println(request);
	    //   NGO ngo = createNewNgo(request);
	      // System.out.println("ji");
		   DBObject doc = createNGODBObject(ngo);
		   
	       MongoClient mongo = new MongoClient("localhost", 27017);
	       DB db = mongo.getDB("hacktest");
	        
	       DBCollection col = db.getCollection("NGOs");
	       
	       System.out.println("dbobject == " + doc);
	       WriteResult result = col.insert(doc);
		   }
	    
		public static List<BasicDBObject> searchNGOByCriteria(String category, String city) throws UnknownHostException {
				List<BasicDBObject> ngoList = new ArrayList<BasicDBObject>();
				MongoClient mongo = new MongoClient("localhost", 27017);
		        DB db = mongo.getDB("hacktest");
		        
		        DBCollection col = db.getCollection("NGOs");
		        BasicDBObject q = new BasicDBObject();
		        q.put("causes", "health care");
		        q.put("location", "chennai");
		        
		        
		        DBCursor cursor = col.find(q);

		        while(cursor.hasNext()){
		        	DBObject dbObj = cursor.next();
		        	ngoList.add((BasicDBObject) dbObj);        	
		        }
		        cursor.close();
		        
			return ngoList;
		}
		
public static DBObject createEventDBObject(Event event) {
	    	
	        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
	                                 
	        docBuilder.append("_id", event.getId());
	        docBuilder.append("eventTitle", event.getEventTitle());
	        docBuilder.append("eventOwner", event.getEventOwner());
	        docBuilder.append("eventDesc", event.getEventDesc());
	        docBuilder.append("volunteers", event.getVolunteers());
	        docBuilder.append("startDate", event.getStartDate());
	        docBuilder.append("endDate", event.getEndDate());
	        return docBuilder.get();
	        
	    }
public static void insertEventData(Event event) throws Exception {
		    
		       //Event event = createEvent(request);
		       DBObject doc = createEventDBObject(event);
		      
		       MongoClient mongo = new MongoClient("localhost", 27017);
		       
		       DB db = mongo.getDB("hacktest");
		        
		       DBCollection col = db.getCollection("events");
		       
		       //System.out.println("dbobject == " + doc);
		       
		       WriteResult result = col.insert(doc);

		       //System.out.println(result.getN());
		       //System.out.println(result.getLastConcern());

		       //read example
		       DBObject query = BasicDBObjectBuilder.start().add("_id", event.getId()).get();
		       DBCursor cursor = col.find(query);
		       while(cursor.hasNext()){
		       	DBObject object = cursor.next();
		           System.out.println(object);
		       }
	    }
	    
	    public static DBObject getEvent(int id) throws UnknownHostException {
			
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("hacktest");
			BasicDBObject result = new BasicDBObject();
			
			DBCollection col = db.getCollection("events");

			//BasicDBObject keys = new BasicDBObject();
			//keys.put("id", id);
			BasicDBObject query = new BasicDBObject("_id", id);
			
			DBCursor cursor = col.find(query);
			try {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println(object);
				return object;
			}
			}	finally {
				   cursor.close();
				}
			return null;

		}

	   public static List<BasicDBObject> getEventsForNGO(String name) throws UnknownHostException {
			
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("hacktest");
			//BasicDBObject result = new BasicDBObject();
			
			DBCollection col = db.getCollection("events");

			//BasicDBObject keys = new BasicDBObject();
			//keys.put("id", id);
			BasicDBObject query = new BasicDBObject("eventOwner", name);
			
			DBCursor cursor = col.find(query);
			List<BasicDBObject> res = new ArrayList<BasicDBObject>();
			try {
			while (cursor.hasNext()) {
				res.add((BasicDBObject)cursor.next());
			}
			}finally {
				   cursor.close();
				}
			return res;

		}

		
}
