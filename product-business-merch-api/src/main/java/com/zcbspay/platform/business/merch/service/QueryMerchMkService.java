package com.zcbspay.platform.business.merch.service;

import com.zcbspay.platform.business.exception.BusinessMerchException;
import com.zcbspay.platform.business.merch.bean.MerchMkBean;

public interface QueryMerchMkService {
	 /**
    *查询商户密钥
    * @param memberId
    * @return
    */
   public MerchMkBean queryMerchMkByMemberId(String memberId) throws BusinessMerchException;
}
