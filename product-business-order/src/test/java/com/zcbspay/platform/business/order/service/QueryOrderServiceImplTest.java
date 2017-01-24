package com.zcbspay.platform.business.order.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.ResultBean;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring-context.xml")
public class QueryOrderServiceImplTest {
	@Autowired
	private QueryOrderService queryOrderService;
	
	@Test
	public void testQueryOrderString() {
		String tn = "1170117061000000026";
		try {
			ResultBean bean = queryOrderService.queryOrder(tn);
			System.out.println(bean.toString());
		} catch (BusinessOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryOrderStringString() {
		String tn = "";
		String merchId = "";
		try {
			ResultBean bean = queryOrderService.queryOrder(merchId, tn);
			System.out.println(bean.toString());
		} catch (BusinessOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryInstPayOrder() {
		String tn = "";
		String merchId = "";
		try {
			ResultBean bean = queryOrderService.queryInstPayOrder(merchId, tn);
			System.out.println(bean.toString());
		} catch (BusinessOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
