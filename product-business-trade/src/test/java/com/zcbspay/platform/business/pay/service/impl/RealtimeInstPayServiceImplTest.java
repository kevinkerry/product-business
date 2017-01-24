package com.zcbspay.platform.business.pay.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.business.commons.utils.DateUtil;
import com.zcbspay.platform.business.pay.bean.RealTimeInstPayBean;
import com.zcbspay.platform.business.pay.bean.ResultBean;
import com.zcbspay.platform.business.pay.service.RealtimeInstPayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class RealtimeInstPayServiceImplTest {
	@Autowired
	private RealtimeInstPayService service;
	
	@Test
	public void testPay() {
		RealTimeInstPayBean bean = new RealTimeInstPayBean();
		bean.setBizType("000207");
		bean.setTxnType("70");
		bean.setTxnSubType("00");
		bean.setCoopInstiId("300000000000004");
		bean.setCurrencyCode("156");
		bean.setMerId("200000000000610");
		bean.setTxnTime(DateUtil.getCurrentDateTime());
		bean.setTxnAmt("2");
		bean.setOrderId(System.currentTimeMillis()+"");
		bean.setAccNo("6228480018543668979");
		bean.setAccName("郭佳");
		bean.setAccType("01");
		bean.setCertifId("110105198610094112");
		bean.setCertifTp("01");
		bean.setPhoneNo("18600806796");
		bean.setTn("170123061000000026");
		
		ResultBean resultBean = service.pay(bean);
		System.out.println(resultBean.getResultObj().toString());
	}

}
