package com.tyron.o2o.service;

/**
 * @Description: 缓存服务接口
 *
 * @author 
 * @date 
 */
public interface CacheService {

	/**
	 * 依据key前缀匹配原则删除缓存数据
	 * 
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);

}
