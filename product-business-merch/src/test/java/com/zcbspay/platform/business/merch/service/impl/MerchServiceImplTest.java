package com.zcbspay.platform.business.merch.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.business.exception.BusinessMerchException;
import com.zcbspay.platform.business.merch.service.MerchService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring-context.xml")
public class MerchServiceImplTest{
	@Autowired
	private MerchService server;
	
	@Test
	public void testUpdateMerchPubKey() {
		String memberId = "200000000000915";
		String pub_key = "111111122222222222222222222222";
		try {
			boolean result = server.updateMerchPubKey(memberId, pub_key);
			System.out.println("测试结果：" + result);
		} catch (BusinessMerchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testResetPayPwd() {
		String memberId = "200000000000793";
		String payPwd = "111111";
		try {
			boolean result = server.resetPayPwd(memberId, payPwd);
			System.out.println("测试结果：" + result);
		} catch (BusinessMerchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
