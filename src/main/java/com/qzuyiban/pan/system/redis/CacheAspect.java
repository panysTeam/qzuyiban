package com.qzuyiban.pan.system.redis;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.qzuyiban.pan.system.utils.JsonUtil;


@Component
@Aspect
public class CacheAspect {
	
	private static final Logger logger  =  LoggerFactory.getLogger(CacheAspect.class);

	@Autowired
	RedisCacheBean redis;

	/**
	 * 定义缓存逻辑
	 */
	@Around("@annotation(com.qzuyiban.pan.system.redis.CacheableOwn)")
	public Object cache(ProceedingJoinPoint pjp) {
		Object result = null;
		Boolean cacheEnable = true;
		// 判断是否开启缓存
		if (!cacheEnable) {
			try {
				result = pjp.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			return result;
		}

		Method method = getMethod(pjp);

		CacheableOwn cacheable = method.getAnnotation(CacheableOwn.class);

		String fieldKey = parseKey(cacheable.key(), method, pjp.getArgs());

		// 获取方法的返回类型,让缓存可以返回正确的类型
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

		// 使用redis 的hash进行存取，易于管理
		result = redis.get(fieldKey, returnType);

		if (result == null) {
			try {
				result = pjp.proceed();
				redis.set(fieldKey, JsonUtil.toJSONString(result),cacheable.expireTime());
			} catch (Throwable e) {
				logger.error(e.getMessage(),e);
			}
		}
		return result;

	}

	/** * 定义清除缓存逻辑 */
	/*
	 * @Around(value = "@annotation(org.myshop.cache.annotation.CacheEvict)")
	 * public Object evict(ProceedingJoinPoint pjp) { //
	 * 和cache类似，使用Jedis.hdel()删除缓存即可... try { return pjp.proceed(); } catch
	 * (Throwable e) { e.printStackTrace(); } return null; }
	 */

	/**
	 * 获取被拦截方法对象 MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象 而缓存的注解在实现类的方法上
	 * 所以应该使用反射获取当前对象的方法对象
	 */
	public Method getMethod(ProceedingJoinPoint pjp) {
		//String targetName = pjp.getTarget().getClass().toString();
		String methodName = pjp.getSignature().getName();
		Object[] arguments = pjp.getArgs();
		Method[] methods = pjp.getTarget().getClass().getMethods();

		Method result = null;
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					result = m;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * 获取缓存的key key 定义在注解上，支持SPEL表达式
	 * 
	 * @param pjp
	 * @return
	 */
	private String parseKey(String key, Method method, Object[] args) {

		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);

		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}
}