package com.db.oper;

import java.util.List;

import org.bson.Document;

import com.db.config.DBConfig;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.utils.StringUtils;

public class DBHelper {

	private static DBHelper dbHelper;
	private static MongoClient client;

	private DBHelper() {

	}

	public static synchronized DBHelper getInstance() {
		if (dbHelper == null) {
			dbHelper = new DBHelper();
		}
		return dbHelper;
	}

	public void initClient() {
		client = new MongoClient(DBConfig.DB_IP, DBConfig.DB_PORT);
	}

	public void closeClient() {
		if (client != null) {
			client.close();
		}
	}

	public MongoDatabase getDatabase(String name) throws Exception {
		if (client == null) {
			throw new Exception("client 为空");
		}
		return client.getDatabase(name);
	}

	public MongoCollection<Document> getCollection(MongoDatabase db, String name) throws Exception {
		if (db == null) {
			throw new Exception("MongoDatabase不能为null");
		}
		if (StringUtils.isEmpty(name)) {
			throw new Exception("name不能为空");
		}
		System.out.println("heh");
		return db.getCollection(name);
	}

	public MongoCursor<Document> getCursors(MongoCollection<Document> collection) {
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		return mongoCursor;
	}

	public void insert(MongoCollection<Document> collection, List<Document> documents) {
		collection.insertMany(documents);
	}

}
