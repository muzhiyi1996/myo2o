package com.tyron.o2o.service;

import java.util.List;

import com.tyron.o2o.entity.Area;

/**
 * @Description: 区域业务接口
 *
 * @author 
 * @date 
 */
public interface AreaService {
	public final static String AREA_LIST_KEY = "arealist";

	/**
	 * 获取区域列表,将区域信息放入缓存中
	 * 
	 * @return
	 */
	List<Area> getAreaList();

}
