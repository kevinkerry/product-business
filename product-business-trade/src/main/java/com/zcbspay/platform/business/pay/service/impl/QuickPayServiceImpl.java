package com.zcbspay.platform.business.pay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.business.commons.enums.OrderStatusEnum;
import com.zcbspay.platform.business.commons.utils.BeanCopyUtil;
import com.zcbspay.platform.business.pay.bean.PayBean;
import com.zcbspay.platform.business.pay.bean.ResultBean;
import com.zcbspay.platform.business.pay.service.QuickPayService;
import com.zcbspay.platform.member.individual.bean.QuickpayCustBean;
import com.zcbspay.platform.payment.exception.PaymentQuickPayException;
import com.zcbspay.platform.payment.exception.PaymentRouterException;

/****
 * 快捷支付
 * 
 * @author zhanglh
 *
 */
@Service
public class QuickPayServiceImpl implements QuickPayService {

	private final static Logger log = LoggerFactory.getLogger(QuickPayServiceImpl.class);

	@Autowired
	private com.zcbspay.platform.payment.quickpay.service.QuickPayService quickPayService;
	// @Autowired
	// private MemberBankCardService memberBankCardService;

	/****
	 * 快捷支付(无短信验证、无支付密码)
	 * 
	 * @param bean
	 * @return
	 */
	@Override
	public ResultBean pay(PayBean bean) {
		com.zcbspay.platform.payment.quickpay.bean.PayBean payBean = null;
		ResultBean resultBean = null;
		try {
			if (bean == null) {
				return new ResultBean("BP0000", "参数不能为空！");
			}
			/*
			 * if(StringUtil.isNotEmpty(bean.getBindId())){ QuickpayCustBean
			 * quickPay
			 * =this.memberBankCardService.getMemberBankCardById(Long.valueOf(
			 * bean.getBindId())); if(quickPay == null){ return new
			 * ResultBean("BP0014", "查询不到卡信息"); } bean =
			 * toPayBean(bean,quickPay); }
			 */
			// 根据TN校验订单信息
			/*
			 * OrderResultBean orderBean
			 * =this.queryService.queryOrderByTN(bean.getTn()); if(orderBean ==
			 * null){ return new ResultBean("BP0006","查询订单失败"); }else
			 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.SUCCESS)){
			 * return new ResultBean("BP0007", "发送短信失败！此交易已支付"); }else
			 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.PAYING)){
			 * return new ResultBean("BP0008", "发送短信失败！此交易正支付中"); }else
			 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.FAILED)){
			 * return new ResultBean("BP009", "发送短信失败！此交易已超时"); }
			 */
			payBean = BeanCopyUtil.copyBean(com.zcbspay.platform.payment.quickpay.bean.PayBean.class, bean);
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
			/*
			 * } catch (PaymentOrderException e) { e.printStackTrace();
			 * log.error(e.getMessage()); resultBean = new ResultBean("BP0001",
			 * "订单查询异常！");
			 */
		} catch (PaymentQuickPayException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultBean = new ResultBean("BP0003", "快捷支付异常！");
		} catch (PaymentRouterException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultBean = new ResultBean("BP0004", "路由信息异常！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultBean = new ResultBean("BP00013", "支付异常！");
		}
		return resultBean;
	}

	/*
	 * @Override public ResultBean payBySmsCode(PayBean bean, String smsCode) {
	 * com.zlebank.zplatform.payment.quickpay.bean.PayBean payBean =null;
	 * ResultBean resultBean = null; try { if(bean == null||smsCode==null){
	 * return new ResultBean("BP0000", "参数不能为空！"); } String phone =null;
	 * if(StringUtil.isNotEmpty(bean.getBindId())){ QuickpayCustBean quickPay
	 * =this.memberBankCardService.getMemberBankCardById(Long.valueOf(bean.
	 * getBindId())); if(quickPay == null){ return new ResultBean("BP0014",
	 * "查询不到卡信息"); } bean = toPayBean(bean,quickPay); phone=quickPay.getPhone();
	 * }else{ phone=bean.getPhone(); } //根据TN校验订单信息 OrderResultBean orderBean
	 * =this.queryService.queryOrderByTN(bean.getTn()); if(orderBean == null){
	 * return new ResultBean("BP0006","查询订单失败"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.SUCCESS)){ return
	 * new ResultBean("BP0007", "发送短信失败！此交易已支付"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.PAYING)){ return new
	 * ResultBean("BP0008", "发送短信失败！此交易正支付中"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.FAILED)){ return new
	 * ResultBean("BP009", "发送短信失败！此交易已超时"); } //校验短信 int vcode=0; try { vcode
	 * =this.smsService.verifyCode(phone, bean.getTn(), smsCode); } catch
	 * (Exception e) { e.getMessage(); return new ResultBean("BP0005",
	 * "查询短信失败，请重新获取短信验证码"); } SmsValidateEnum valEnum
	 * =SmsValidateEnum.fromValue(String.valueOf(vcode)); if(valEnum !=
	 * SmsValidateEnum.SV1){ return new ResultBean("BP0005", valEnum.getMsg());
	 * } //支付 payBean =
	 * BeanCopyUtil.copyBean(com.zlebank.zplatform.payment.quickpay.bean.PayBean
	 * .class, bean); com.zlebank.zplatform.payment.commons.bean.ResultBean
	 * result =this.quickPayService.pay(payBean); if(result!=null){
	 * if(result.isResultBool()){ resultBean=new
	 * ResultBean(OrderStatusEnum.SUCCESS.getStatus()); }else{ resultBean=
	 * BeanCopyUtil.copyBean(ResultBean.class,result); } }else{ resultBean = new
	 * ResultBean("BP0012", "快捷支付失败！"); } } catch (PaymentOrderException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0001", "订单查询异常！"); }catch (PaymentQuickPayException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0003", "快捷支付异常！"); } catch (PaymentRouterException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0004", "路由信息异常！"); } catch(Exception e){
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP00013", "支付异常！"); } return resultBean; }
	 */

	/*
	 * @Override public ResultBean payByPayPassword(PayBean bean ,String
	 * memberId, String payPassword) {
	 * com.zlebank.zplatform.payment.quickpay.bean.PayBean payBean =null;
	 * ResultBean resultBean = null; try { if(bean == null ||
	 * bean.getTn()==null||payPassword==null){ return new ResultBean("BP0000",
	 * "参数不能为空！"); } if(StringUtil.isNotEmpty(bean.getBindId())){
	 * QuickpayCustBean quickPay
	 * =this.memberBankCardService.getMemberBankCardById(Long.valueOf(bean.
	 * getBindId())); if(quickPay == null){ return new ResultBean("BP0014",
	 * "查询不到卡信息"); } bean = toPayBean(bean,quickPay); } //根据TN校验订单信息
	 * OrderResultBean orderBean
	 * =this.queryService.queryOrderByTN(bean.getTn()); if(orderBean == null){
	 * return new ResultBean("BP0006","查询订单失败"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.SUCCESS)){ return
	 * new ResultBean("BP0007", "发送短信失败！此交易已支付"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.PAYING)){ return new
	 * ResultBean("BP0008", "发送短信失败！此交易正支付中"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.FAILED)){ return new
	 * ResultBean("BP009", "发送短信失败！此交易已超时"); } //校验会员信息 PoMemberBean member =
	 * memberService.getMbmberByMemberId(memberId, MemberType.INDIVIDUAL); if
	 * (member == null) {// 资金账户不存在 return new ResultBean("BP0010", "查询会员失败"); }
	 * MemberBean memberBean = new MemberBean();
	 * memberBean.setLoginName(member.getLoginName());
	 * memberBean.setInstiId(member.getInstiId());
	 * memberBean.setPhone(member.getPhone());
	 * memberBean.setPaypwd(payPassword); boolean pwdFlag=
	 * this.memberOperationService.verifyPayPwd(MemberType.INDIVIDUAL,
	 * memberBean); if(!pwdFlag){ return new ResultBean("BP0011", "支付密码校验失败"); }
	 * //支付 payBean =
	 * BeanCopyUtil.copyBean(com.zlebank.zplatform.payment.quickpay.bean.PayBean
	 * .class, bean); com.zlebank.zplatform.payment.commons.bean.ResultBean
	 * result =this.quickPayService.pay(payBean); if(result!=null){
	 * if(result.isResultBool()){ resultBean=new
	 * ResultBean(OrderStatusEnum.SUCCESS.getStatus()); }else{ resultBean=
	 * BeanCopyUtil.copyBean(ResultBean.class,result); } }else{ resultBean = new
	 * ResultBean("BP0012", "快捷支付失败！"); } }catch (PaymentOrderException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0001", "订单查询异常！"); }catch (DataCheckFailedException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0002", "校验快捷支付密码异常！"); } catch (PaymentQuickPayException e)
	 * { e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0003", "快捷快捷支付异常！"); } catch (PaymentRouterException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0004", "路由信息异常！"); }catch(Exception e){
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP00013", "支付异常！"); } return resultBean; }
	 */
	/*
	 * @Override public ResultBean pay(PayBean bean, String memberId, String
	 * smsCode, String payPassword) {
	 * com.zlebank.zplatform.payment.quickpay.bean.PayBean payBean =null;
	 * ResultBean resultBean = null; try { if(bean == null ||
	 * bean.getTn()==null||payPassword==null||smsCode==null){ return new
	 * ResultBean("BP0000", "参数不能为空！"); } String phone =null;
	 * if(StringUtil.isNotEmpty(bean.getBindId())){ QuickpayCustBean quickPay
	 * =this.memberBankCardService.getMemberBankCardById(Long.valueOf(bean.
	 * getBindId())); if(quickPay == null){ return new ResultBean("BP0014",
	 * "查询不到卡信息"); } bean = toPayBean(bean,quickPay); phone=quickPay.getPhone();
	 * }else{ phone=bean.getPhone(); } //校验短信 int vcode=0; try { vcode
	 * =this.smsService.verifyCode(phone, bean.getTn(), smsCode); } catch
	 * (Exception e) { e.getMessage(); return new ResultBean("BP0005",
	 * "查询短信失败，请重新获取短信验证码"); } SmsValidateEnum valEnum
	 * =SmsValidateEnum.fromValue(String.valueOf(vcode)); if(valEnum !=
	 * SmsValidateEnum.SV1){ return new ResultBean("BP0005", valEnum.getMsg());
	 * } //根据TN校验订单信息 OrderResultBean orderBean; orderBean =
	 * this.queryService.queryOrderByTN(bean.getTn()); if(orderBean == null){
	 * return new ResultBean("BP0006","查询订单失败"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.SUCCESS)){ return
	 * new ResultBean("BP0007", "发送短信失败！此交易已支付"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.PAYING)){ return new
	 * ResultBean("BP0008", "发送短信失败！此交易正支付中"); }else
	 * if(orderBean.getOrderStatus().equals(OrderStatusEnum.FAILED)){ return new
	 * ResultBean("BP009", "发送短信失败！此交易已超时"); } //校验会员信息 PoMemberBean member =
	 * memberService.getMbmberByMemberId(memberId, MemberType.INDIVIDUAL); if
	 * (member == null) {// 资金账户不存在 return new ResultBean("BP0010", "查询会员失败"); }
	 * MemberBean memberBean = new MemberBean();
	 * memberBean.setLoginName(member.getLoginName());
	 * memberBean.setInstiId(member.getInstiId());
	 * memberBean.setPhone(member.getPhone());
	 * memberBean.setPaypwd(payPassword); boolean pwdFlag =
	 * this.memberOperationService.verifyPayPwd(MemberType.INDIVIDUAL,
	 * memberBean); if(!pwdFlag){ return new ResultBean("BP0011", "支付密码校验失败"); }
	 * //支付 payBean =
	 * BeanCopyUtil.copyBean(com.zlebank.zplatform.payment.quickpay.bean.PayBean
	 * .class, bean); com.zlebank.zplatform.payment.commons.bean.ResultBean
	 * result =this.quickPayService.pay(payBean); if(result!=null){
	 * if(result.isResultBool()){ resultBean=new
	 * ResultBean(OrderStatusEnum.SUCCESS.getStatus()); }else{ resultBean=
	 * BeanCopyUtil.copyBean(ResultBean.class,result); } }else{ resultBean = new
	 * ResultBean("BP0012", "快捷支付失败！"); } }catch (PaymentOrderException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0001", "订单查询异常！"); }catch (DataCheckFailedException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0002", "校验支付密码异常！"); } catch (PaymentQuickPayException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0003", "快捷支付异常！"); } catch (PaymentRouterException e) {
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP0004", "路由信息异常！"); }catch(Exception e){
	 * e.printStackTrace(); log.error(e.getMessage()); resultBean = new
	 * ResultBean("BP00013", "支付异常！"); } return resultBean; }
	 */

	@SuppressWarnings("unused")
	private PayBean toPayBean(PayBean bean, QuickpayCustBean quickPay) {
		if (bean == null) {
			bean = new PayBean();
		}
		bean.setCardKeeper(quickPay.getAccname());
		bean.setCardNo(quickPay.getCardno());
		bean.setCardType(quickPay.getCardtype());
		bean.setCertNo(quickPay.getIdnum());
		bean.setCvn2(quickPay.getCvv2());
		bean.setExpired(quickPay.getValidtime());
		bean.setPhone(quickPay.getPhone());
		bean.setBankCode(quickPay.getBankcode());
		return bean;
	}

}
