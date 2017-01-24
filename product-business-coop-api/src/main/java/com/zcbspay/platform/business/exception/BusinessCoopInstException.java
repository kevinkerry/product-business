package com.zcbspay.platform.business.exception;


public class BusinessCoopInstException extends AbstractDescException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public BusinessCoopInstException() {
		super();
	}
	public BusinessCoopInstException(String code,Object ... para ) {
        this.params = para;
        this.code = code;
    }

	public BusinessCoopInstException(String code) {
		super();
		this.code = code;
	}


	@Override
	public String getCode() {
		return this.code;
	}

}
