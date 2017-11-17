package com.task;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.entity.Res;
import com.task.builder.TaskConnection;
import com.task.taskconfig.Config;
import com.utils.DateUtils;

public class AcceptTest {

	public static void startTask(Map<String, String> cookieParams, int interval) {

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (cookieParams != null) {
					cookieParams.get("TaskYiDianPassId").replace("[datetime]", DateUtils.getNowDate());
				}
				TaskConnection.Builder builder = new TaskConnection.Builder();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("PlatformType", "TodayTask");
				params.put("InTimeType", "ReserveTask");

				String result = builder.setUrl(Config.HOST + Config.APPLY_TB_URL).setParams(params)
						.SetCookieParams(cookieParams).build().request();
				Res res = JSON.parseObject(result, Res.class);
				System.out.println("☆☆☆☆淘宝☆☆☆☆" + "-----" + res.Message + " " + DateUtils.getNowDate());

			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, interval * 60 * 1000);
	}

}
