package com.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.db.entity.Account;
import com.mongodb.client.MongoCursor;

public class AccountDao extends BaseDao<Account> {

	public static final String NAME = "Account";

	public AccountDao() {
		super(NAME);
	}

	@Override
	public void insert(Account account) {
		Document document = new Document("accountId", account.accountId).append("accountName", account.accountName)
				.append("cellphone", account.cellphone).append("tuyuOpenId", account.tuyuOpenId)
				.append("taskOpenId", account.taskOpenId).append("taskYiDianPassId",account.taskYiDianPassId);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		dbHelper.insert(collection, documents);
	}
	
	@Override
	public List<Account> queryAll() {
		MongoCursor<Document> cursors = dbHelper.getCursors(collection);
		List<Account> list = new ArrayList<Account>();
		if (cursors != null) {
			while (cursors.hasNext()) {
				Document d = cursors.next();
				Account temp = JSON.parseObject(d.toJson(), new TypeReference<Account>() {
				});
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}


}
