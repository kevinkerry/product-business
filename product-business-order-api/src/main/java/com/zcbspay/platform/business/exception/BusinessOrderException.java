package com.zcbspay.platform.business.exception;


public class BusinessOrderException extends AbstractDescException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public BusinessOrderException() {
		super();
	}
	public BusinessOrderException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }

	public BusinessOrderException(String code) {
		super();
		this.code = code;
	}


	@Override
	public String getCode() {
		return this.code;
	}

}
