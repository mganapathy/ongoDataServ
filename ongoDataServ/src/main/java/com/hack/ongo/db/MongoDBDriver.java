package com.hack.ongo.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class MongoDBDriver {

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
		
		public static void insertAppeal(Appeal appeal) throws Exception	   {
		   DBObject doc = createDBObject(appeal);
       MongoClient mongo = new MongoClient("localhost", 27017);
       //Mongo mongo = new Mongo();
       DB db = mongo.getDB("hacktest");
        
       DBCollection col = db.getCollection("appeal");
       
//       System.out.println("dbobject == " + doc);
       WriteResult result = col.insert(doc);

       System.out.println(result.getN());
       System.out.println(result.getLastConcern());

       //read example
       DBObject query = BasicDBObjectBuilder.start().add("_id", appeal.getId()).get();
       DBCursor cursor = col.find(query);
       while(cursor.hasNext()){
       	DBObject object = cursor.next();
           System.out.println(object);
       }
       }

	   public static ArrayList<BasicDBObject> getAppeal() throws UnknownHostException {
		ArrayList< BasicDBObject > array = new ArrayList< BasicDBObject >();
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		BasicDBObject result = new BasicDBObject();
		
		DBCollection col = db.getCollection("appeal");

		//BasicDBObject keys = new BasicDBObject();
		//keys.put("id", id);
		BasicDBObject query = new BasicDBObject();
		
		DBCursor cursor = col.find();
		try {
		while (cursor.hasNext()) {
			BasicDBObject object = (BasicDBObject)cursor.next();
			System.out.println(object);
			array.add(object);
		}
		}	finally {
			   cursor.close();
			}
		return array;

	}
	
	public static DBObject createDBObject(Appeal appeal) {
	        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
	                                 
	        docBuilder.append("_id", appeal.getId());
	      
	        docBuilder.append("description", appeal.getDescription());
	        docBuilder.append("ByWhom", appeal.getByWhom());
	        
	        DBObject teaching = BasicDBObjectBuilder.start()
	        		.append("skill", appeal.getCause().getEducation().getType().getTeaching().getSkill())
	        		.append("volunteer_no", appeal.getCause().getEducation().getType().getTeaching().getVolunteer_no())
	        		.get();
	        
	        DBObject type = BasicDBObjectBuilder.start()
	        		.append("teaching", teaching)
	        		.get();
	        DBObject education = BasicDBObjectBuilder.start()
	        		.append("type", type)
	        		.get();
	        docBuilder.append("cause", education);
	        docBuilder.append("city", appeal.getCity());
	        docBuilder.append("state", appeal.getState());
	        docBuilder.append("status", appeal.getStatus());
	        return docBuilder.get();
	    }


		
}
