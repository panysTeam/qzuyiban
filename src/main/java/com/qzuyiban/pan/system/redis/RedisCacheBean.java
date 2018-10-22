package com.qzuyiban.pan.system.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.qzuyiban.pan.system.utils.JsonUtil;



@Component
public class RedisCacheBean {
    
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 把对象放入redis
     */
    public void set(String key,Object o,int expireTime){
        redisTemplate.opsForValue().set(key,JsonUtil.toJSONString(o).toString());
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }
    /**
     * 获取对象
     */
    public String get(String key){
        Object text = redisTemplate.opsForValue().get(key);
        return text == null ? null : text.toString();
    }
    
    /**
     * 获取对象,转换成制定类型
     */
    public Object get(String key,Class clazz){
        String text = get(key);
        if(clazz.getName().equals("java.lang.String" )) {//如果redis中存的是字符串，直接返回，不转换成对像
        	return text;
        }
        Object result=JsonUtil.parseObject(text, clazz);
        return result;
    }
    
    /**
     * 从Hash中删除对象
     */
    public void del(String key){
        redisTemplate.delete(key);
    }
    
}