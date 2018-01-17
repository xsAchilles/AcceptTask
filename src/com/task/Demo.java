package com.task;

import java.util.List;

import com.db.dao.AccountDao;
import com.db.entity.Account;

public class Demo {
	public static void main(String[] args) {

		AccountDao accoutDao = new AccountDao();
		List<Account> accountList = accoutDao.queryAll();
		if (accountList != null) {
			for (Account account : accountList) {
				AcceptJDTask.startTask(account, 2);
				AcceptTest.startTask(account, 2);
//				if("Ö£Á®".equals(account.accountName)){
//					TaskListReq.queryTaskList(account);
//				}
			}
		}

	}
}
