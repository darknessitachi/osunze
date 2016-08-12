package com.skycity.framework;

import com.skycity.framework.collection.Mapx;

public class ResponseImpl extends Mapx<String,Object> {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 1;

	public static final int FAILED = 0;

	public int Status = 1;

	public String Message = "";

	public String getMessage() {
		return this.Message;
	}

	public void setFailedMessage(String message) {
		setStatusAndMessage(0, message);
	}

	public void setSuccessMessage(String message) {
		setStatusAndMessage(1, message);
	}

	public int getStatus() {
		return this.Status;
	}

	public void setStatus(int status) {
		this.Status = status;
		this.setStatusAndMessage(status, "");
	}

	public void setStatusAndMessage(int status, String message) {
		this.Status = status;
		put("Status", status);
		this.Message = message;
		put("Message", this.Message);
	}
	
	public String toJson() {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for (String id : keySet()) {
			Object value = get(id);
			sb.append("\""+id+"\":");
			sb.append("\""+value+"\"");
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}");
		return sb.toString();
	}
}
