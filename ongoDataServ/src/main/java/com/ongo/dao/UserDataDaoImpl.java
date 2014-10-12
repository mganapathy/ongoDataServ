package com.ongo.dao;

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
import com.ongo.model.Employee;
import com.ongo.model.User;

public class UserDataDaoImpl implements UserDao {

	@Override
	public boolean addUser(User user) throws Exception {
		MongoClient mongo = new MongoClient("localhost", 27017);
		Boolean userExists = false;
		String msg = "failure-user already existes";
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");

		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", user.getName());
			DBCursor cursor = col.find(searchQuery);
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
					return false;
				}
			}
			if (userExists == false) {
				System.out.println("new user, insertUserData");
				DBObject doc = convertToDBObject(user);
				System.out.println("dbobject == " + doc);
				col.insert(doc);
				msg = "Success-user created";
				System.out.println("insertUserData after insert" + msg);
				mongo.close();
				return true;
			}
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mongo.close();
		return false;
	}

	@Override
	public User getUserById(long id) throws Exception {
		System.out.println("getUserDataById fun start mmmm id == " + id);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		// BasicDBObject result = new BasicDBObject();
		DBCollection col = db.getCollection("users");
		BasicDBObject query = new BasicDBObject("_id", id);
		DBCursor cursor = col.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println(object);
				return convertToUser(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	@Override
	public List<Employee> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(long id) throws Exception {

		System.out.println("deleteUserDataById fun start mmmm id == " + id);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");
		BasicDBObject deleteQuery = new BasicDBObject("_id", id);
		WriteResult result = col.remove(deleteQuery);
		if (result.getN() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public DBObject convertToDBObject(User user) {

		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("_id", user.getId());
		docBuilder.append("name", user.getName());
		docBuilder.append("role", user.getRole());
		docBuilder.append("isEmployee", user.getIsEmployee());
		docBuilder.append("mPhone", user.getmPhone());
		docBuilder.append("lPhone", user.getlPhone());
		docBuilder.append("password", user.getPassword());
		docBuilder.append("cuase", user.getCuase());
		docBuilder.append("currentAddress", user.getCurrentAddress());
		docBuilder.append("permenantAddress", user.getPermenantAddress());
		docBuilder.append("appealInfo", user.getAppealInfo());
		docBuilder.append("appealCategory", user.getAppealCategory());
		docBuilder.append("appealDate", user.getAppealDate());
		docBuilder.append("appealExpiryDate", user.getAppealExpiryDate());

		return docBuilder.get();
	}

	@Override
	public User convertToUser(DBObject dbObject) {
		User user = new User();

		BasicDBObject basicDBObject = (BasicDBObject) dbObject;
		user.setId(basicDBObject.getInt("_id"));
		user.setName(basicDBObject.getString("name"));
		user.setRole(basicDBObject.getString("role"));
		user.setIsEmployee(basicDBObject.getBoolean("isEmployee"));
		user.setmPhone(basicDBObject.getString("mPhone"));
		user.setlPhone(basicDBObject.getString("lPhone"));
		user.setPassword(basicDBObject.getString("password"));
		user.setCuase(basicDBObject.getString("cuase"));
		user.setCurrentAddress(basicDBObject.getString("currentAddress"));
		user.setPermenantAddress(basicDBObject.getString("permenantAddress"));
		user.setAppealCategory(basicDBObject.getString("appealCategory"));
		user.setAppealInfo(basicDBObject.getString("appealInfo"));
		user.setAppealDate(basicDBObject.getString("appealDate"));
		user.setAppealExpiryDate(basicDBObject.getString("appealExpiryDate"));
		return user;
	}

	@Override
	public User getUserByName(String name) throws Exception {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");

		DBCollection collection = db.getCollection("users");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", name);
		DBCursor cursor = collection.find(searchQuery);
		while (cursor.hasNext()) {
			// if(cursor.length() > 0) {
			System.out.println("cursor val == " + name );
			BasicDBObject obj = (BasicDBObject) cursor.next();
			System.out.println("cursor val == " + name + "obj" + obj);
			return convertToUser(obj);
		}
		return null;
	}

	@Override
	public boolean isValidUser(String userName, String password) {
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			Boolean userCredenatial = false;
			DB db = mongo.getDB("hacktest");

			DBCollection collection = db.getCollection("users");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", userName);
			DBCursor cursor = collection.find(searchQuery);
			while (cursor.hasNext()) {
				// if(cursor.length() > 0) {
				System.out.println("cursor val == " + userName + "pwd == " + password);
				BasicDBObject obj = (BasicDBObject) cursor.next();
				System.out.println("cursor val == " + userName + "pwd == " + password + "obj" + obj);
				if (obj.getString("password").equalsIgnoreCase(password)) {
					String result = "";
					result += obj.getString("password");
					System.out.println("result val == " + result + "obj.getString==" + obj.getString("password"));
					// if (result.equals(user.getPassword())) {
					System.out.println("valid password found");
					return true;
				}
			}

			if (userCredenatial == false) {
				System.out.println("invalid login data");
				return false;
			}
		} catch (MongoException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean updateUser(long id, User user) throws Exception  {
		System.out.println("getUserDataById fun start mmmm id == " + id);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("hacktest");
		DBCollection col = db.getCollection("users");
		BasicDBObject query = new BasicDBObject("_id", id);
		DBObject doc = convertToDBObject(user);
		WriteResult update = col.update(query, doc);
		if (update.getN() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		
		MongoClient mongo = new MongoClient("localhost", 27017);
		List<User> users = new ArrayList<>();
		DB db = mongo.getDB("hacktest");
		// BasicDBObject result = new BasicDBObject();
		DBCollection col = db.getCollection("users");
		BasicDBObject query = new BasicDBObject();
		DBCursor cursor = col.find(query);
		try {
			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println(object);
				users.add(convertToUser(object));
			}
		} finally {
			cursor.close();
		}
		
		return users;
	}

}
