package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.db.config.DBConfig;
import com.db.oper.DBHelper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

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

	public List<T> queryAll() {
		MongoCursor<Document> cursors = dbHelper.getCursors(collection);
		List<T> list = new ArrayList<T>();
		if (cursors != null) {
			while (cursors.hasNext()) {
				Document d = cursors.next();
				String s = "{ \"_id\" : { \"$oid\" : \"5a0a53bd91b3ffcca3a4074a\" }, \"accepterName\" : \"÷£¡Æ\", \"platform\" : \"Ã‘±¶\", \"cellphone\" : \"18896736951\", \"accepteTime\" : \"2017-11-14 08:00:00\" }";
				T temp = JSON.parseObject(s, new TypeReference<T>() {
				});
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}

	public void closeCollection() {
		if (dbHelper != null) {
			dbHelper.closeClient();
		}
	}
}
