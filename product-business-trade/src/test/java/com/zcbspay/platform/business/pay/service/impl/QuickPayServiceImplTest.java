package com.zcbspay.platform.business.pay.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.business.commons.enums.OrderStatusEnum;
import com.zcbspay.platform.business.commons.utils.BeanCopyUtil;
import com.zcbspay.platform.business.pay.bean.ResultBean;
import com.zcbspay.platform.payment.exception.PaymentQuickPayException;
import com.zcbspay.platform.payment.exception.PaymentRouterException;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class QuickPayServiceImplTest extends TestCase {
	@Autowired
	private com.zcbspay.platform.payment.quickpay.service.QuickPayService quickPayService;

	@SuppressWarnings("unused")
	@Test
	public void testPay() throws PaymentQuickPayException, PaymentRouterException {
		com.zcbspay.platform.payment.quickpay.bean.PayBean payBean = null;
		ResultBean resultBean = null;

		com.zcbspay.platform.business.pay.bean.PayBean bean = new com.zcbspay.platform.business.pay.bean.PayBean();
		bean.setCardNo("6228480018543668979");
		bean.setCardKeeper("郭佳");
		bean.setCardType("1");
		bean.setPhone("18600806796");
		bean.setCertNo("110105198610094112");
		bean.setTn("170118061000000030");
		bean.setTxnAmt("2");
		try {

			if (bean == null) {
				System.out.println(new ResultBean("BP0000", "参数不能为空！"));
			}
			payBean = BeanCopyUtil.copyBean(com.zcbspay.platform.payment.quickpay.bean.PayBean.class, bean);
			System.out.println("start to pay");
			com.zcbspay.platform.payment.bean.ResultBean result = this.quickPayService.pay(payBean);
			if (result != null) {
				if (result.isResultBool()) {
					resultBean = new ResultBean(OrderStatusEnum.SUCCESS.getStatus());
				} else {
					resultBean = BeanCopyUtil.copyBean(ResultBean.class, result);
				}
			} else {
				resultBean = new ResultBean("BP0012", "快捷支付失败！");
			}
		}catch (PaymentQuickPayException e) {
			e.printStackTrace();
			resultBean = new ResultBean("BP0003", "快捷支付异常！");
		} catch (PaymentRouterException e) {
			e.printStackTrace();
			resultBean = new ResultBean("BP0004", "路由信息异常！");
		}catch(Exception e){
			e.printStackTrace();
			resultBean = new ResultBean("BP00013", "支付异常！");
		}
	}

}
