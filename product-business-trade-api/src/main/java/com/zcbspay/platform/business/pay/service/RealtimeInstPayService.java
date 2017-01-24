package com.zcbspay.platform.business.pay.service;

import com.zcbspay.platform.business.pay.bean.RealTimeInstPayBean;
import com.zcbspay.platform.business.pay.bean.ResultBean;

public interface RealtimeInstPayService {
	/****
	 * 单笔支付(无短信验证、无支付密码)
	 * @param bean
	 * @return
	 */
     public ResultBean pay (RealTimeInstPayBean bean);
}
