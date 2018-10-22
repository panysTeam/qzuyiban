package com.qzuyiban.pan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;

import com.qzuyiban.pan.system.redis.CacheableOwn;
//@Mapper
//@CacheConfig(cacheNames = "users")
public interface UserDao {

//	@Cacheable(key ="user") 
	public List<Map<String, Object>> find();

	
}
