package com.zcbspay.platform.business.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.business.commons.utils.BeanCopyUtil;
import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.OrderResultBean;
import com.zcbspay.platform.business.order.bean.ResultBean;
import com.zcbspay.platform.support.order.query.exception.QueryOrderException;

@Service
public class QueryOrderServiceImpl implements QueryOrderService {
	private final static Logger log = LoggerFactory.getLogger(QueryOrderServiceImpl.class);
	@Autowired
	private com.zcbspay.platform.support.order.query.query.service.QueryOrderService queryService;//com\zcbspay\platform\support\order\query\query\service

	@Override
	public ResultBean queryOrder(String tn) throws BusinessOrderException {
		OrderResultBean order = null;
		com.zcbspay.platform.support.order.query.query.bean.OrderResultBean orderBean;
		try {
			orderBean = this.queryService.queryOrderByTN(tn);
			order = BeanCopyUtil.copyBean(OrderResultBean.class, orderBean);
			return new ResultBean(order);
		} catch (QueryOrderException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultBean("BO00012", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessOrderException("BO00013");// 查询订单异常
		}
	}

	@Override
	public ResultBean queryOrder(String merchId, String accOrderNo) throws BusinessOrderException {
		OrderResultBean order = null;
		com.zcbspay.platform.support.order.query.query.bean.OrderResultBean orderBean;
		try {
			orderBean = this.queryService.queryOrder(merchId, accOrderNo);
			order = BeanCopyUtil.copyBean(OrderResultBean.class, orderBean);
			return new ResultBean(order);
		} catch (QueryOrderException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultBean("BO00012", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessOrderException("BO00013");// 查询订单异常
		}
	}

	@Override
	public ResultBean queryInstPayOrder(String merchId, String accOrderNo) throws BusinessOrderException {
		OrderResultBean order = null;
		com.zcbspay.platform.support.order.query.query.bean.OrderResultBean orderBean;
		try {
			orderBean = this.queryService.queryInsteadPayOrder(merchId, accOrderNo);
			order = BeanCopyUtil.copyBean(OrderResultBean.class, orderBean);
			return new ResultBean(order);
		} catch (QueryOrderException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultBean("BO00012", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessOrderException("BO00013");// 查询实时代付订单异常
		}
	}

}
