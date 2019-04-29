package com.tyron.o2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyron.o2o.BaseTest;
import com.tyron.o2o.entity.Area;
import com.tyron.o2o.entity.PersonInfo;
import com.tyron.o2o.entity.Shop;
import com.tyron.o2o.entity.ShopCategory;
import com.tyron.o2o.enums.EnableStatusEnum;

/**
 * @Description: 测试店铺接口
 *
 * @author tyronchen
 * @date 2018年3月31日
 */
public class ShopDaoTest extends BaseTest {

	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testSelectShopByUserId() {
		int id = 1;
		Shop shop = shopDao.selectShopByUserId(id);
		System.out.println(shop);
	}

	@Test
	@Ignore
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(4L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.selectShopList(shopCondition, 0, 3);
		System.out.println("查询店铺列表的大小：" + shopList.size());
		int shopCount = shopDao.selectShopCount(shopCondition);
		System.out.println("店铺列表总数大小：" + shopCount);
	}

	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		ShopCategory shopCategory = new ShopCategory();
		Area area = new Area();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setArea(area);
		shop.setShopAddr("test");
		shop.setShopName("test店铺");
		shop.setShopDesc("test");
		shop.setShopImg("test");
		shop.setPhone("test");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
		shop.setAdvice("审核中");
		int effectNum = shopDao.insertShop(shop);
		System.out.println("effectNum：" + effectNum);
	}

	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopAddr("test@111");
		shop.setShopName("@test@111");
		shop.setShopDesc("test111");
		shop.setShopImg("test111");
		shop.setPhone("test111");
		shop.setLastEditTime(new Date());
		int effectNum = shopDao.updateShop(shop);
		System.out.println("effectNum：" + effectNum);
	}

	@Test
	@Ignore
	public void testQueryShop() {
		Shop shop = shopDao.selectByShopId(1);
		System.out.println("areaName:" + shop.getArea().getAreaName());
		System.out.println("shopCategoryName:" + shop.getShopCategory().getShopCategoryName());
	}
}
