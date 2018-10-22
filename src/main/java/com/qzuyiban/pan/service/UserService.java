package com.qzuyiban.pan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.qzuyiban.pan.dao.UserDao;
import com.qzuyiban.pan.system.redis.CacheableOwn;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
//	@Cacheable(value="people",key ="'userId'")//缓存，除了第一次外，都是取缓存的值(springboot自身的)
//	@CachePut(value="people",key="'userId'")每次都执行方法，根据返回结果写入缓存中(springboot自身的)
	@CacheableOwn(key="'user'")//自己封装，根据aop横切面原理
	public Map<String, Object> find() {
		List<Map<String,Object>> listUser=userDao.find();
		Map<String,Object> resultUser=new HashMap<String,Object>();
		resultUser.put("data", listUser);
		return resultUser;
	}
	
	
}
