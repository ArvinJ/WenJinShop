package com.yandz.steels.entity;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = -360654807033118597L;
	private String res;// 操作状态
	private String msg;// 返回信息
	private Object data; // 返回数据
	

	public Result() {
	}

	public Result(String res, String msg) {
		this.res = res;
		this.msg = msg;
	}

	public Result(String res, String msg, Object data) {
		this.res = res;
		this.msg = msg;
		this.data = data;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [res=" + res + ", msg=" + msg + ", data=" + data + "]";
	}

}
