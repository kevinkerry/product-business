package com.zcbspay.platform.business.order.service;

import com.zcbspay.platform.business.exception.BusinessOrderException;
import com.zcbspay.platform.business.order.bean.ResultBean;
/***
 * 查询订单相关
 * @author zhanglh
 *
 */
public interface QueryOrderService {
	/****
	 * 根据tn查询订单
	 * @param tn
	 * @return
	 */
	public ResultBean queryOrder(String tn ) throws BusinessOrderException;
	/***
	 * 根据商户和授理订单号查询订单
	 * @param merchId
	 * @param accOrderNo
	 * @return
	 */
	public ResultBean queryOrder(String merchId, String accOrderNo) throws BusinessOrderException;
	/***
	 * 
	 * @param merchId
	 * @param accOrderNo
	 * @return
	 * @throws BusinessOrderException
	 */
	public ResultBean queryInstPayOrder(String merchId, String accOrderNo)throws BusinessOrderException;
	
}
