package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.db.entity.AcceptRecord;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class AcceptRecordDao  extends BaseDao<AcceptRecord> {
	
	public static final String NAME = "AcceptRecord18";

	public AcceptRecordDao() {
		super(NAME);
	}

	@Override
	public void insert(AcceptRecord acceptRecord) {
		Document document = new Document("recordId", acceptRecord.recordId).append("accepterId", acceptRecord.accepterId)
				.append("accepterName", acceptRecord.accepterName).append("platform", acceptRecord.platform)
				.append("message", acceptRecord.message).append("tryDateTime",acceptRecord.tryDateTime).append("responseInfo", acceptRecord.responseInfo);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		dbHelper.insert(collection, documents);
		
	}

	@Override
	public List<AcceptRecord> queryAll() {
		MongoCursor<Document> cursors = dbHelper.getCursors(collection);
		List<AcceptRecord> list = new ArrayList<AcceptRecord>();
		if (cursors != null) {
			while (cursors.hasNext()) {
				Document d = cursors.next();
				AcceptRecord temp = JSON.parseObject(d.toJson(), new TypeReference<AcceptRecord>() {
				});
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}
	
//	public List<AcceptRecord> queryRecordsByTryDateTime(){
//		MongoCursor<Document> cursors = dbHelper.getCursorsByCondition(collection);
//		List<AcceptRecord> list = new ArrayList<AcceptRecord>();
//		if (cursors != null) {
//			while (cursors.hasNext()) {
//				Document d = cursors.next();
//				AcceptRecord temp = JSON.parseObject(d.toJson(), new TypeReference<AcceptRecord>() {
//				});
//				if (temp != null) {
//					list.add(temp);
//				}
//			}
//		}
//		return list;
//	}
	
	public List<AcceptRecord> getCursorsByCondition(BasicDBObject queryObj) {
		FindIterable<Document> findIterable = collection.find(queryObj);
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		List<AcceptRecord> list = new ArrayList<AcceptRecord>();
		if (mongoCursor != null) {
			while (mongoCursor.hasNext()) {
				Document d = mongoCursor.next();
				AcceptRecord temp = JSON.parseObject(d.toJson(), new TypeReference<AcceptRecord>() {
				});
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}
	

}
