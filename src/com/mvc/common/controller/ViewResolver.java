package com.mvc.common.controller;

public class ViewResolver {
	public String prefix;
	public String suffix;
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	//접근자는 getView로 만들거다
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
