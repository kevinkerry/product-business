package com.zcbspay.platform.business.merch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.business.commons.utils.BeanCopyUtil;
import com.zcbspay.platform.business.commons.utils.StringUtil;
import com.zcbspay.platform.business.exception.BusinessMerchException;
import com.zcbspay.platform.business.merch.bean.MerchMkBean;
//import com.zcbspay.platform.business.merch.dao.MerchMKDAO;
//import com.zcbspay.platform.business.merch.pojo.PojoMerchMK;
import com.zcbspay.platform.business.merch.service.QueryMerchMkService;
import com.zcbspay.platform.member.merchant.bean.MerchMK;
import com.zcbspay.platform.member.merchant.service.MerchMKService;

@Service
public class QueryMerchMkServiceImpl implements QueryMerchMkService {
	@Autowired
	private MerchMKService merchMKservice;

	/**
	 * 查询商户密钥
	 * 
	 * @param memberId
	 * @return
	 */
	@Override
	public MerchMkBean queryMerchMkByMemberId(String memberId) throws BusinessMerchException {
		if (StringUtil.isEmpty(memberId)) {
			throw new BusinessMerchException("BT0000");
		}
		MerchMK merchMK = this.merchMKservice.get(memberId);
		if (merchMK == null)
			return null;
		MerchMkBean result = BeanCopyUtil.copyBean(MerchMkBean.class, merchMK);
		return result;
	}
}
