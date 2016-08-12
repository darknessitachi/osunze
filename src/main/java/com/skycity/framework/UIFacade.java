package com.skycity.framework;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skycity.framework.collection.Mapx;
import com.skycity.framework.utility.ServletUtil;
import com.skycity.framework.utility.StringUtil;
import com.skycity.util.PageInfo;

public abstract class UIFacade<T> {
	protected Mapx<String, Object> data = new Mapx<String,Object>();
	protected PageInfo<T> page = new PageInfo<T>(10);
	protected ObjectMapper objectMapper= new ObjectMapper();
	public UIFacade(){
		data = new Mapx<String,Object>();
	}
	
	public PageInfo<T> getPage() {
		return page;
	}

	public void setPage(PageInfo<T> page) {
		this.page = page;
	}

	public ResponseImpl Response = new ResponseImpl();

	public void initRequest(HttpServletRequest request) {
		data = new Mapx<String,Object>();
		String sendData = request.getParameter("_SEND_DATA");
		if (StringUtil.isNotEmpty(sendData)) {
			data = Mapx.toMap(sendData);
		}
		data.putAll(ServletUtil.getParameterMap(request));
	}
	
	protected void clearData(){
		data = new Mapx<String,Object>();
	}
	
	public Mapx<String, Object> getData() {
		return data;
	}

	/**
	 * 
	 * @Title: $V
	 * @Description:根据ID得到值
	 * @return String
	 * @throws
	 */
	public String $V(String id) {
		String v = data.getString(id);
		return v;
	}

	public void $S(String id, Object value) {
		this.Response.put(id, value);
	}
	
	public ResponseImpl getResponse() {
		return Response;
	}

	public void setResponse(ResponseImpl response) {
		this.Response = response;
	}

	public String toJson() {
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for (String id : data.keySet()) {
			Object value = data.get(id);
			sb.append("\""+id+"\":");
			sb.append("\""+value+"\"");
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}");
		return sb.toString();
	}
}
