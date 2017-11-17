package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.db.entity.TaskAccepted;

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

}
