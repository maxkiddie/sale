/**
 * 
 */
package com.ydy.service.redis.impl;

import com.ydy.service.redis.RedisService;

/**
 * @author xuzhaojie
 *
 *         2018年8月2日 下午8:51:30
 */
//@Service
public class RedisServiceImpl implements RedisService {
//	private final static Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//
//	public void setString(String key, String value) {
//		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
//		cliet.set(key, value);
//		log.debug("redis-[setString]>" + key + ":" + value);
//	}
//
//	public void setString(String key, String value, Integer second) {
//		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
//		cliet.set(key, value, second, TimeUnit.SECONDS);
//		log.debug("redis-[setExpire]>" + key + ":" + value + "--" + second);
//	}
//
//	public String getString(String key) {
//		ValueOperations<String, String> cliet = redisTemplate.opsForValue();
//		String value = cliet.get(key);
//		log.debug("redis-[getString]>" + key + ":" + value);
//		return value;
//	}
//
//	public boolean hasKey(String key) {
//		boolean flag = redisTemplate.hasKey(key);
//		log.debug("redis-[hasKey]>" + key + ":" + flag);
//		return flag;
//	}
//
//	public void deleteKey(String key) {
//		redisTemplate.delete(key);
//		log.debug("redis-[deleteKey]>" + key);
//	}
//
//	public void setCode(String key, String value, Integer second) {
//		setString(RedisConstant.CODE_PRE + key, value, second);
//	}
//
//	public void delCode(String key) {
//		deleteKey(RedisConstant.CODE_PRE + key);
//	}
//
//	public String getCode(String key) {
//		return getString(RedisConstant.CODE_PRE + key);
//	}
//
//	public void setThirdpartySystem(String systemName, String value) {
//		setString(RedisConstant.THIRDPARTY_PRE + systemName, value, RedisConstant.THIRDPARTY_SYSTEM_CACHE_EXPIRE_TIME);
//	}
//
//	public void setThirdpartySystem(String systemName, String value, Integer second) {
//		setString(RedisConstant.THIRDPARTY_PRE + systemName, value, second);
//	}
//
//	public void delThirdpartySystem(String systemName) {
//		redisTemplate.delete(RedisConstant.THIRDPARTY_PRE + systemName);
//	}
//
//	public String getThirdpartySystem(String systemName) {
//		return getString(RedisConstant.THIRDPARTY_PRE + systemName);
//	}
}
