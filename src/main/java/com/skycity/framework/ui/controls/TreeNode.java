package com.skycity.framework.ui.controls;

import java.util.List;

import com.skycity.framework.collection.Mapx;

public class TreeNode {
	private String id;
	private String text;
	private String state;
	private String pid;
	private Mapx<String, Object> attributes;
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Mapx<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Mapx<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	@Override
	public String toString(){
		return "TreeNode[id="+this.id+",text="+this.text+",pid="+pid+",sate="+state+",attributes="+
				this.attributes+",children"+this.children+"]";
	}
}
