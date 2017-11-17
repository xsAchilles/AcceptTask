package com.db.entity;

public class Account {

	/**
	 * 账户Id
	 */
	public String accountId;
	/**
	 * 名称
	 */
	public String accountName;
	/**
	 * 手机号
	 */
	public String cellphone;
	/**
	 * 途豫的openId
	 */
	public String tuyuOpenId;
	/**
	 * 目前观察与途豫的openId一样
	 */
	public String taskOpenId;
	/**
	 * 跟账号有关 需要自己抓包获取
	 */
	public String taskYiDianPassId;
	/**
	 * 账户状态 0 新建 1 使用中 2 停用
	 */
	public String accountStatus;

}
