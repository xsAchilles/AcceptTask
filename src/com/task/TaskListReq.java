package com.task;

import java.util.HashMap;
import java.util.Map;

import com.db.entity.Account;
import com.task.builder.TaskConnection;
import com.task.taskconfig.Config;
import com.utils.DateUtils;

public class TaskListReq {
	public static void queryTaskList(Account account){
		Map<String, String> cookieParams = new HashMap<String, String>();
		cookieParams.put("TuYuOAuthOpenId", account.tuyuOpenId);
		cookieParams.put("ASP.NET_SessionId", "eetvsylxcty2fnoqlknpuaek");
		cookieParams.put("YiDianZhuShouTaskOpenId", account.taskOpenId);
		cookieParams.put("TaskYiDianPassId",
				account.taskYiDianPassId.replace("[datetime]", DateUtils.getNowDate()));
		TaskConnection.Builder builder = new TaskConnection.Builder();
		String result = builder.setUserAgent(account.userAgent).setUrl(Config.HOST + Config.TASK_STEP_FOUR).SetCookieParams(cookieParams)
				.build().request();
		System.out.println(result);
	}

}
