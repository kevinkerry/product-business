package com.zcbspay.platform.business.exception;


public class BusinessPayException extends AbstractDescException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public BusinessPayException() {
		super();
	}
	public BusinessPayException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }

	public BusinessPayException(String code) {
		super();
		this.code = code;
	}


	@Override
	public String getCode() {
		return this.code;
	}

}
