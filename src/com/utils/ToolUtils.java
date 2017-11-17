package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ToolUtils {
	// ��map��תΪ���������
	public static String urlencode(Map<String, Object> data) {
		if (data == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
