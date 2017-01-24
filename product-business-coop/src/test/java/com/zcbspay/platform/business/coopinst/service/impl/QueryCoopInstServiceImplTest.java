package com.zcbspay.platform.business.coopinst.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.member.coopinsti.bean.CoopInsti;
import com.zcbspay.platform.member.coopinsti.bean.CoopInstiMK;
import com.zcbspay.platform.member.coopinsti.bean.enums.TerminalAccessType;
import com.zcbspay.platform.member.coopinsti.service.CoopInstiService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring-context.xml")
public class QueryCoopInstServiceImplTest extends TestCase {

	@Autowired
	private CoopInstiService  coopInstiService;
	
	@Test
	public void testQueryCoopInstMk() {
		TerminalAccessType  term= TerminalAccessType.fromValue(Integer.valueOf(1));
		CoopInsti coopInst= this.coopInstiService.getInstiByInstiCode("300000000000004");
		if(coopInst==null){
			System.out.println("coopInst is null");
		}
		CoopInstiMK mk = this.coopInstiService.getCoopInstiMK("300000000000004", term);
		if(mk==null){
			System.out.println("mk is null");
		}
	}

}
