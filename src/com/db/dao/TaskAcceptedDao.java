package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.db.entity.TaskAccepted;
import com.mongodb.client.MongoCursor;

public class TaskAcceptedDao extends BaseDao<TaskAccepted> {

	public static final String NAME = "Task_Accepted";

	public TaskAcceptedDao() {
		super(NAME);
	}

	@Override
	public void insert(TaskAccepted taskAccepted) {
		Document document = new Document("accepterName", taskAccepted.accepterName)
				.append("platform", taskAccepted.platform).append("cellphone", taskAccepted.cellphone)
				.append("accepteTime", taskAccepted.accepteTime);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		dbHelper.insert(collection, documents);
	}
	
	@Override
	public List<TaskAccepted> queryAll() {
		MongoCursor<Document> cursors = dbHelper.getCursors(collection);
		List<TaskAccepted> list = new ArrayList<TaskAccepted>();
		if (cursors != null) {
			while (cursors.hasNext()) {
				Document d = cursors.next();
				TaskAccepted temp = JSON.parseObject(d.toJson(), new TypeReference<TaskAccepted>() {
				});
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}

}
