package com.db.dao;

import java.util.List;

import org.bson.Document;

import com.db.config.DBConfig;
import com.db.oper.DBHelper;
import com.mongodb.client.MongoCollection;

public abstract class BaseDao<T> {

	protected DBHelper dbHelper;
	MongoCollection<Document> collection;

	protected BaseDao(String collectionName) {
		dbHelper = DBHelper.getInstance();
		dbHelper.initClient();
		try {
			collection = dbHelper.getCollection(dbHelper.getDatabase(DBConfig.DB_NAME), collectionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void insert(T t);

	public abstract List<T> queryAll();

	public void closeCollection() {
		if (dbHelper != null) {
			dbHelper.closeClient();
		}
	}
}
