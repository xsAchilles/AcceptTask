package com.task;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.entity.Res;
import com.task.builder.TaskConnection;
import com.task.taskconfig.Config;
import com.utils.DateUtils;

public class AcceptJDTask {

	public static void startTask(Map<String, String> cookieParams, int interval) {

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (cookieParams != null) {
					cookieParams.get("TaskYiDianPassId").replace("[datetime]", DateUtils.getNowDate());
				}
				TaskConnection.Builder builder = new TaskConnection.Builder();
				String result = builder.setUrl(Config.HOST + Config.APPLY_JD_URL).SetCookieParams(cookieParams).build()
						.request();
				Res res = JSON.parseObject(result, Res.class);
				System.out.println("※※※※京东※※※※" + "-----" + res.Message + " " + DateUtils.getNowDate());

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, interval * 60 * 1000);
	}

}
