package com.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.db.dao.AcceptRecordDao;
import com.db.entity.AcceptRecord;
import com.db.entity.Account;
import com.entity.Res;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.task.builder.TaskConnection;
import com.task.taskconfig.Config;
import com.utils.DateUtils;

public class AcceptTest {

	public static void startTask(Account account, int interval) {

		final AcceptRecordDao acceptRecordDao = new AcceptRecordDao();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (account != null) {
					List<AcceptRecord> records = queryRecord(account.accountName, acceptRecordDao);
					boolean canAccpet = false;
					boolean b = DateUtils.isBefore(19);
					if (records != null) {
						canAccpet = records.size() == 0;
					}
					if (!canAccpet) {
						AcceptRecord acceptRecord = new AcceptRecord();
						acceptRecord.recordId = UUID.randomUUID().toString();
						acceptRecord.accepterId = account.accountId;
						acceptRecord.accepterName = account.accountName;
						acceptRecord.platform = "淘宝";
						acceptRecord.message = "接单受限，当前时间允许范围内已接到相应数量订单";
						acceptRecord.tryDateTime = DateUtils.getNowDate();
						acceptRecord.responseInfo = account.accountName + "接单受限，当前时间允许范围内已接到相应数量订单";
						acceptRecordDao.insert(acceptRecord);
						System.out.println("☆☆☆☆淘宝☆☆☆☆" + "-----" + account.accountName + "接单受限，当前时间允许范围内已接到相应数量订单"
								+ " " + DateUtils.getNowDate());
						return;
					}
					Map<String, String> cookieParams = new HashMap<String, String>();
					cookieParams.put("TuYuOAuthOpenId", account.tuyuOpenId);
					cookieParams.put("ASP.NET_SessionId", "ftkj1qvt4piabrwkoj45ljrd");
					cookieParams.put("YiDianZhuShouTaskOpenId", account.taskOpenId);
					cookieParams.put("TaskYiDianPassId",
							account.taskYiDianPassId.replace("[datetime]", DateUtils.getNowDate()));
					TaskConnection.Builder builder = new TaskConnection.Builder();
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("PlatformType", "TodayTask");
					params.put("InTimeType", "ReserveTask");

					String result = builder.setUserAgent(account.userAgent).setUrl(Config.HOST + Config.APPLY_TB_URL).setParams(params)
							.SetCookieParams(cookieParams).build().request();
					Res res = JSON.parseObject(result, Res.class);
					if(res!=null){
						AcceptRecord acceptRecord = new AcceptRecord();
						acceptRecord.recordId = UUID.randomUUID().toString();
						acceptRecord.accepterId = account.accountId;
						acceptRecord.accepterName = account.accountName;
						acceptRecord.platform = "淘宝";
						acceptRecord.message = res.Message;
						acceptRecord.tryDateTime = DateUtils.getNowDate();
						acceptRecord.responseInfo = result;
						acceptRecordDao.insert(acceptRecord);
						System.out.println(
								"☆☆☆☆淘宝☆☆☆☆" + "-----" + account.accountName + res.Message + " " + DateUtils.getNowDate());
					}
				}

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, interval * 60 * 1000);
	}

	private static List<AcceptRecord> queryRecord(String accountName, AcceptRecordDao acceptRecordDao) {
		String today = DateUtils.getTodayDateWithoutHMS();
		String tomorrow = DateUtils.getTomorrowDateWithoutHMS();
		BasicDBObject queryObj = new BasicDBObject();
		DBObject obj = BasicDBObjectBuilder.start(QueryOperators.GT, today).add(QueryOperators.LTE, tomorrow).get();
		queryObj.append("accepterName", accountName);
		queryObj.append("message", "操作成功");
		queryObj.append("platform", "淘宝");
		queryObj.append("tryDateTime", obj);

		List<AcceptRecord> records = acceptRecordDao.getCursorsByCondition(queryObj);
		return records;
	}

}
