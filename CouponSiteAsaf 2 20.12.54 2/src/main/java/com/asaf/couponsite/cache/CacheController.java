package com.asaf.couponsite.cache;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheController implements ICacheController{

	// This class serves as a basis for a clustered cache mechanism
	private Map<String, Object> cacheMap;

	public CacheController() {
		this.cacheMap = new HashMap<String, Object>();
	}

	public Object get(String key) {
		return this.cacheMap.get(key);
	}

	public void put(String key, Object value) {
		this.cacheMap.put(key, value);
	}


}

//@Controller
//public class ChacheController implements ICacheController{
//
//	// This class serves as a basis for a clustered cache mechanism
//	private Map<Long, Object> cacheMap;
//
//	public ChacheController() {
//		this.cacheMap = new HashMap<Long, Object>();
//	}
//
//	public Object get(Long key) {
//		return this.cacheMap.get(key);
//	}
//
//	public void put(Long key, Object value) {
//		this.cacheMap.put(key, value);
//	}
//
//
//}
