package com.zcbspay.platform.business.order.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.business.commons.utils.DateUtil;
import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.InstPayOrderBean;
import com.zcbspay.platform.business.order.bean.ResultBean;
import com.zcbspay.platform.payment.exception.PaymentOrderException;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring-context.xml")
public class OrderServiceImplTest {
	/*@Autowired
	private com.zcbspay.platform.payment.order.service.OrderService orderService;
	
	@Test
	public void test_create() throws PaymentOrderException {
		SimpleOrderBean orderBean = new SimpleOrderBean();
		orderBean.setBizType("000201");
		orderBean.setTxnType("01");
		orderBean.setTxnSubType("00");
		orderBean.setCoopInstiId("300000000000004");
		orderBean.setCurrencyCode("156");
		orderBean.setMerId("200000000000610");
		orderBean.setTxnTime(DateUtil.getCurrentDateTime());
		orderBean.setTxnAmt("2");
		orderBean.setOrderTimeout("20170202000000");
		orderBean.setMemberId("999999999999999");
		orderBean.setOrderId(System.currentTimeMillis()+"");
		
		String tn= this.orderService.createConsumeOrder(orderBean);
		System.out.println(tn);
	}
*/
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void test_create() throws PaymentOrderException {
		InstPayOrderBean insteadPayOrderBean = new InstPayOrderBean();
		insteadPayOrderBean.setBizType("000207");
		insteadPayOrderBean.setTxnType("70");
		insteadPayOrderBean.setTxnSubType("00");
		insteadPayOrderBean.setCoopInstiId("300000000000004");
		insteadPayOrderBean.setCurrencyCode("156");
		insteadPayOrderBean.setMerId("200000000000610");
		insteadPayOrderBean.setTxnTime(DateUtil.getCurrentDateTime());
		insteadPayOrderBean.setTxnAmt("2");
		insteadPayOrderBean.setOrderId(System.currentTimeMillis()+"");
		insteadPayOrderBean.setAccNo("6228480018543668979");
		insteadPayOrderBean.setAccName("郭佳");
		insteadPayOrderBean.setAccType("01");
		insteadPayOrderBean.setCertifId("110105198610094112");
		insteadPayOrderBean.setCertifTp("01");
		insteadPayOrderBean.setPhoneNo("18600806796");
		try {
			ResultBean resultBean = orderService.createInsteadPayOrder(insteadPayOrderBean);
			System.out.println(resultBean.getResultObj().toString());
		} catch (BusinessOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
