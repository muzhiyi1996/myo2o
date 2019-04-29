package com.tyron.o2o.exceptions;

/**
 * @Description: 商品类别操作异常
 *
 * @author: 
 * @date: 
 */
public class ProductCategoryOperationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
