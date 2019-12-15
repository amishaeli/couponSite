package com.asaf.couponsite.cache;


public interface ICacheController {

	public Object get(String key);
	
	public void put(String key, Object value);

}
