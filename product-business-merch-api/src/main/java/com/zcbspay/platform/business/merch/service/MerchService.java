package com.zcbspay.platform.business.merch.service;

import com.zcbspay.platform.business.exception.BusinessMerchException;

public interface MerchService {

	/**
	 * 更新商户公钥
	 * @param memberId
	 * @param pub_key
	 * @return
	 */
	public boolean  updateMerchPubKey(String memberId, String pub_key)throws BusinessMerchException ;
	/**
	 * 重置商户支付密码
	 * @param memberId
	 * @param pwd
	 * @return
	 * @throws DataCheckFailedException
	 */
	public boolean resetPayPwd(String memberId, String pwd) throws BusinessMerchException;
}
