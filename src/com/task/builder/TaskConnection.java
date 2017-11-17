package com.task.builder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.task.taskconfig.Config;
import com.utils.ToolUtils;

public class TaskConnection {

	private String url;

	private Map<String, Object> params;

	private Map<String, String> cookieParams;

	public static class Builder {

		private String url;

		private Map<String, Object> params;

		private Map<String, String> cookieParams;

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public Builder setParams(Map<String, Object> params) {
			this.params = params;
			return this;
		}

		public Builder SetCookieParams(Map<String, String> cookieParams) {
			this.cookieParams = cookieParams;
			return this;
		}

		public TaskConnection build() {
			TaskConnection taskConnection = new TaskConnection();
			taskConnection.url = this.url;
			taskConnection.params = this.params;
			taskConnection.cookieParams = this.cookieParams;
			return taskConnection;
		}

	}

	private String getCookie() {
		StringBuilder cookieStr = new StringBuilder();
		if (this.cookieParams != null) {
			for (Entry<String, String> entry : this.cookieParams.entrySet()) {
				if (entry != null) {
					cookieStr.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
				}
			}
		}
		return cookieStr.toString();
	}

	public String request() {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(this.url);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("User-agent", Config.USER_AGENT);
			conn.setUseCaches(false);
			conn.setConnectTimeout(Config.DEF_CONN_TIMEOUT);
			conn.setReadTimeout(Config.DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestProperty("Cookie", getCookie());
			conn.connect();
			if (this.params == null) {
				this.params = new HashMap<String, Object>();
			}
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(ToolUtils.urlencode(this.params));
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, Config.DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
