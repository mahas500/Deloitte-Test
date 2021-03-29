package com.deloitte.Test_Deloitte.Utils;

/*
 * WebServiceResponse class containing resultSet and StatusCodesEnum used in controller classes.
 * */
public class WebServiceResponse {

	
	private Object resultSet;
	private StatusCodesEnum statusCodesEnum;
	
	public Object getResultSet() {
		return resultSet;
	}
	public void setResultSet(Object resultSet) {
		this.resultSet = resultSet;
	}
	public StatusCodesEnum getStatusCode() {
		return statusCodesEnum;
	}
	public void setStatusCode(StatusCodesEnum statusCodesEnum) {
		this.statusCodesEnum = statusCodesEnum;
	}
	
	@Override
	public String toString() {
		return "WebServiceResponse [resultSet=" + resultSet + ", statusCodesEnum=" + statusCodesEnum + "]";
	}
	
	
	
}
