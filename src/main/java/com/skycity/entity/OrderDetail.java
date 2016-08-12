package com.skycity.entity;

public class OrderDetail {
	private int id;
	private int orderId; // 订单号
	private String goodsName; // 商品名称
	private String unit; // 单位
	private float count; // 销量
	private float price; // 销售单价
	private float amountOfPrice; // 金额

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAmountOfPrice() {
		return amountOfPrice;
	}

	public void setAmountOfPrice(float amountOfPrice) {
		this.amountOfPrice = amountOfPrice;
	}

}
