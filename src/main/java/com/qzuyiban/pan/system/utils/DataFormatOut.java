package com.qzuyiban.pan.system.utils;

public class DataFormatOut {

	private Object data;
	private Integer code;
	private boolean status;
			
	public DataFormatOut() {
		
	}	
	
	public DataFormatOut(Object data, Integer code, boolean status) {
		super();
		this.data = data;
		this.code = code;
		this.status = status;
	}



	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
