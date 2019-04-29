package com.tyron.o2o.web.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyron.o2o.dto.ShopExecution;
import com.tyron.o2o.entity.PersonInfo;
import com.tyron.o2o.entity.Shop;
import com.tyron.o2o.entity.User;
import com.tyron.o2o.enums.OperationStatusEnum;
import com.tyron.o2o.enums.ShopStateEnum;
import com.tyron.o2o.service.ShopService;
import com.tyron.o2o.service.UserService;
import com.tyron.o2o.util.CodeUtil;
import com.tyron.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/getuser",method=RequestMethod.POST)
	@ResponseBody
	public User selectUser(HttpServletRequest request) {
		String name = HttpServletRequestUtil.getString(request, "name");
		return userService.selectUser(name);
		
	}
	@RequestMapping(value="/getuserbyid",method=RequestMethod.POST)
	@ResponseBody
	public User selectUserById(HttpServletRequest request) {
		int id = HttpServletRequestUtil.getInt(request, "id");
		return userService.selectUserById(id);
		
	}
	
	@RequestMapping(value="/insertuser",method=RequestMethod.POST)
	@ResponseBody
	public int insertUser(HttpServletRequest request) {
		User user = new User();
		user.setAddr(HttpServletRequestUtil.getString(request, "addr"));
		user.setAge(HttpServletRequestUtil.getInt(request, "age"));
		user.setGender(HttpServletRequestUtil.getString(request, "gender"));
		user.setPassword(HttpServletRequestUtil.getString(request, "password"));
		user.setPhone(HttpServletRequestUtil.getInt(request, "phone"));
		user.setUserName(HttpServletRequestUtil.getString(request, "name"));
		user.setIdentity(2);
		return userService.insertUser(user);
			
	}
	@RequestMapping(value="/updateuser",method=RequestMethod.POST)
	@ResponseBody
	public int updateUser(HttpServletRequest request) {
		User user = new User();
		user.setAddr(HttpServletRequestUtil.getString(request, "addr"));
		user.setAge(HttpServletRequestUtil.getInt(request, "age"));
		user.setGender(HttpServletRequestUtil.getString(request, "gender"));
		user.setPassword(HttpServletRequestUtil.getString(request, "password"));
		user.setPhone(HttpServletRequestUtil.getInt(request, "phone"));
		user.setUserName(HttpServletRequestUtil.getString(request, "name"));	
		int i = userService.updateUser(user);
		
		return i;
	}
	
	@RequestMapping(value="/getuserlist",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getListUser(HttpServletRequest request){
		List<User> list = userService.getUserList();
		return list;
		
	}
	@RequestMapping(value="/getshopbyuserid",method=RequestMethod.POST)
	@ResponseBody
	public Shop getShopByUserId(HttpServletRequest request){
		Shop shop = new Shop();
		int id = HttpServletRequestUtil.getInt(request, "id");
		shop = shopService.selectShopByUserId(id);
		return shop;
	}
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		

		// 1、接受并转化相应参数，包括店铺信息及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 使用jackson-databind-->https://github.com/FasterXML/jackson-databind将json转换为pojo
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse（创建一次，可重用）
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 获取图片文件流
		MultipartHttpServletRequest multipartRequest = null;
		MultipartFile shopImg = null;
		MultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			shopImg = (MultipartFile) multipartRequest.getFile("shopImg");
		}
		if (shopImg == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStatusEnum.PIC_EMPTY.getStateInfo());
			return modelMap;
		}

		// 2、注册店铺，尽量不要依靠前端信息
		if (shop != null) {
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			owner.setUserId(1L);
			shop.setOwner(owner);
			ShopExecution se = shopService.addShop(shop, shopImg);
			if (se.getState() == ShopStateEnum.CHECK.getState()) {
				modelMap.put("success", true);

				// 注册成功，将店铺列表存入到session中
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
				if (shopList == null || shopList.isEmpty()) {
					shopList = new ArrayList<>();
				}
				shopList.add(shop);
				request.getSession().setAttribute("shopList", shopList);

			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", se.getStateInfo());
			}
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopStateEnum.NULL_SHOP_INFO.getStateInfo());
			return modelMap;
		}
	}
	
}
