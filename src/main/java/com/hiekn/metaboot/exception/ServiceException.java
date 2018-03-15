package com.hiekn.metaboot.exception;


public class ServiceException extends BaseException{
	
	private ServiceException(ErrorCodes code) {
		super(code);
	}

	public static ServiceException newInstance(){
		return newInstance(ErrorCodes.SERVICE_ERROR);
	}
	
	public static ServiceException newInstance(ErrorCodes code){
		return new ServiceException(code);
	}

}
