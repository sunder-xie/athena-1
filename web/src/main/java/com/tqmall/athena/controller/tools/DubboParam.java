package com.tqmall.athena.controller.tools;

import java.util.List;

public class DubboParam {
	private String service;
	private String method;
	private List<Param> params;
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}
	
}

class Param {
	private String paramType;
	private String param;
	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParam() {
		return param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
}
