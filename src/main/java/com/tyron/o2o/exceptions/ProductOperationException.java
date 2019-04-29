package com.tyron.o2o.exceptions;

/**
 * @Description: 商品操作异常
 *
 * @author: 
 * @date: 
 */
public class ProductOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductOperationException(String msg) {
		super(msg);
	}
}
