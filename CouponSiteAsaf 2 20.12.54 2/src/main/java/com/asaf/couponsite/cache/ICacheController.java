package com.asaf.couponsite.cache;



public interface ICacheController {

	public Object get(String key);

	public void put(String key, Object value);

}


//public interface ICacheController {
//
//	public Object get(Long key);
//
//	public void put(Long key, Object value);
//
//
//}
