package com.tyron.o2o.exceptions;

/**
 * @Description: 店铺操作异常
 *
 * @author 
 * @date 
 */
public class ShopOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}
