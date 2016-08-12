package com.skycity.controller;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skycity.entity.Order;

/** 
 * @author YingBo.Dai 
 * @E-mail:lyyb2001@163.com 
 * @qq:20880488 
 * @version 创建时间：2016年8月10日 上午8:38:56 
 * 程序的简单说明 
 */
public class OrderTest {
	ObjectMapper objectMapper= new ObjectMapper();
	@Test
	public void turnJsonStr() throws JsonProcessingException{
		Order order = new Order();
		order.getCustomer().setName("dyb");
		order.getCustomer().setMobile("13725039842");
		order.setFloorCabinetMaterial("dasfas");
		String orderStr = objectMapper.writeValueAsString(order);
		System.out.println(orderStr);
		try {
			Order order2 = objectMapper.readValue(orderStr,Order.class);

			System.out.println(objectMapper.writeValueAsString(order2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
