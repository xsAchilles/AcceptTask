package com.db.entity;

public class AcceptRecord {

	/**
	 * 随机的uuid
	 */
	public String recordId;
	/**
	 * 请求任务的人的id
	 */
	public String accepterId;
	/**
	 * 请求任务人的那么
	 */
	public String accepterName;
	/**
	 * 任务平台 1 淘宝 2 京东
	 */
	public String platform;
	/**
	 * 服务器返回的信息
	 */
	public String message;
	/**
	 * 时间 接口返回的时间 也可认为是尝试请求的时间
	 */
	public String tryDateTime;
	
	/**
	 * 完整的返回
	 */
	public String responseInfo;
}
