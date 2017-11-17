package com.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.dao.TaskAcceptedDao;
import com.db.entity.TaskAccepted;

public class Demo {
	public static void main(String[] args) {
		Map<String, String> cookieParams = new HashMap<String, String>();
		cookieParams.put("TuYuOAuthOpenId", "oAGkHj_0euDRuvGbb4lMnLV6dYSg");
		cookieParams.put("ASP.NET_SessionId", "eetvsylxcty2fnoqlknpuaek");
		cookieParams.put("YiDianZhuShouTaskOpenId", "oAGkHj_0euDRuvGbb4lMnLV6dYSg");
		cookieParams.put("TaskYiDianPassId", "0|100935|18896736951|[datetime]");
		AcceptJDTask.startTask(cookieParams, 5);

//		TaskAcceptedDao dao = new TaskAcceptedDao();
//		List<TaskAccepted> list = dao.queryAll();
//		if (list != null) {
//			for (TaskAccepted taskAccepted : list) {
//				System.out.println(taskAccepted.accepterName);
//			}
//		}

		 AcceptTest.startTask(cookieParams, 5);
		// System.out.println(DateUtils.isBefore(19));
		//
		// TaskAcceptedDao dao = new TaskAcceptedDao();
		// TaskAccepted taskAccepted = new TaskAccepted();
		// taskAccepted.accepterName = "张三";
		// taskAccepted.platform = "京东";
		// taskAccepted.cellphone = "17625604670";
		// taskAccepted.acceptTime = "2017-11-14 09:00:00";
		//
		// dao.insert(taskAccepted);
		// MongoCursor<Document> cursors = dao.queryAll();
		// while(cursors.hasNext()){
		// TaskAccepted t =
		// JSON.parseObject(cursors.next().toJson(),TaskAccepted.class);
		// if(t!=null){
		// System.out.println(t.accepteTime);
		// }
		// }
	}
}
