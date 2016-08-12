package com.skycity.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int id; // 订单ID
	private String orderDate; // 订单日期
	private String orderTotalPrice; // 订单价格
	private double orderRealPrice;		//订单实际收款金额
	private String orderStatus; // 订单状态(订单生成/已交定金/正在生产/正在安装/已撤销)
	private String orderDescrption; // 订单描述信息
	private Date lastUpdateTime;// 订单状态最后更新时间
	private String orderCharge; // 订单负责人
	
	private String floorMaterial;			//台面材料
	private String shutterMaterial;			//门板材料
	private String cabinetCabinetMaterial;	//吊柜柜体材料
	private String seePlateMaterial;		//见光板材料
	private String mesaColor;				//台面色号
	private String doorColor;				//门板色号
	private String floorCabinetMaterial;	//地柜柜体材料
	private String topSealingMaterial;		//顶封板材料
	private String mesaBlock;				//台面挡水边
	private String otherMaterial;			//其它
	private String otherMaterial2;			//其它2
	private String handle;					//拉手
	
	private Customer customer = new Customer();		//顾客信息
	private List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(String orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDescrption() {
		return orderDescrption;
	}

	public void setOrderDescrption(String orderDescrption) {
		this.orderDescrption = orderDescrption;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getOrderCharge() {
		return orderCharge;
	}

	public void setOrderCharge(String orderCharge) {
		this.orderCharge = orderCharge;
	}

	public double getOrderRealPrice() {
		return orderRealPrice;
	}

	public void setOrderRealPrice(double orderRealPrice) {
		this.orderRealPrice = orderRealPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getFloorMaterial() {
		return floorMaterial;
	}

	public void setFloorMaterial(String floorMaterial) {
		this.floorMaterial = floorMaterial;
	}

	public String getShutterMaterial() {
		return shutterMaterial;
	}

	public String getOtherMaterial2() {
		return otherMaterial2;
	}

	public void setOtherMaterial2(String otherMaterial2) {
		this.otherMaterial2 = otherMaterial2;
	}

	public void setShutterMaterial(String shutterMaterial) {
		this.shutterMaterial = shutterMaterial;
	}

	public String getCabinetCabinetMaterial() {
		return cabinetCabinetMaterial;
	}

	public void setCabinetCabinetMaterial(String cabinetCabinetMaterial) {
		this.cabinetCabinetMaterial = cabinetCabinetMaterial;
	}

	public String getSeePlateMaterial() {
		return seePlateMaterial;
	}

	public void setSeePlateMaterial(String seePlateMaterial) {
		this.seePlateMaterial = seePlateMaterial;
	}

	public String getMesaColor() {
		return mesaColor;
	}

	public void setMesaColor(String mesaColor) {
		this.mesaColor = mesaColor;
	}

	public String getDoorColor() {
		return doorColor;
	}

	public void setDoorColor(String doorColor) {
		this.doorColor = doorColor;
	}

	public String getFloorCabinetMaterial() {
		return floorCabinetMaterial;
	}

	public void setFloorCabinetMaterial(String floorCabinetMaterial) {
		this.floorCabinetMaterial = floorCabinetMaterial;
	}

	public String getTopSealingMaterial() {
		return topSealingMaterial;
	}

	public void setTopSealingMaterial(String topSealingMaterial) {
		this.topSealingMaterial = topSealingMaterial;
	}

	public String getMesaBlock() {
		return mesaBlock;
	}

	public void setMesaBlock(String mesaBlock) {
		this.mesaBlock = mesaBlock;
	}

	public String getOtherMaterial() {
		return otherMaterial;
	}

	public void setOtherMaterial(String otherMaterial) {
		this.otherMaterial = otherMaterial;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
