package com.zcbspay.platform.business.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.business.commons.utils.BeanCopyUtil;
import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.InstPayOrderBean;
import com.zcbspay.platform.business.order.bean.OrderBean;
import com.zcbspay.platform.business.order.bean.ResultBean;
import com.zcbspay.platform.payment.exception.PaymentOrderException;
import com.zcbspay.platform.payment.order.bean.InsteadPayOrderBean;
import com.zcbspay.platform.payment.order.bean.SimpleOrderBean;

@Service
public class OrderServiceImpl implements OrderService {
	private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private com.zcbspay.platform.payment.order.service.OrderService orderService;

	public ResultBean createConsumeOrder(OrderBean order) throws BusinessOrderException {
		try {
			if (order == null || order.getTxnSubType() == null || order.getTxnType() == null
					|| order.getBizType() == null) {
				throw new BusinessOrderException("BO0000");
			}
			SimpleOrderBean orderBean = BeanCopyUtil.copyBean(SimpleOrderBean.class, order);
			String tn = this.orderService.createConsumeOrder(orderBean);
			return new ResultBean(tn);
		} catch (PaymentOrderException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultBean("BO0001", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessOrderException("BO0002");// 创建消费订单异常
		}
	}

	@Override
	public ResultBean createInsteadPayOrder(InstPayOrderBean order) throws BusinessOrderException {
		try {
			if (order == null || order.getTxnSubType() == null || order.getTxnType() == null
					|| order.getBizType() == null) {
				throw new BusinessOrderException("BO0000");
			}
			InsteadPayOrderBean orderBean = BeanCopyUtil.copyBean(InsteadPayOrderBean.class, order);
			String tn = this.orderService.createInsteadPayOrder(orderBean);
			return new ResultBean(tn);
		} catch (PaymentOrderException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultBean("BO0005", e.getMessage());// 创建实时代付订单失败
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessOrderException("BO0006");// 创建实时代付订单异常
		}

	}
}
