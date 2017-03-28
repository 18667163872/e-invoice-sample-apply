package com.wosai.e.invoice.sample.apply.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.wosai.e.invoice.sample.apply.exception.SignException;

public class SignUtil {
	
	/**
	 * MD5签名加密
	 * @param map
	 * @return
	 * @throws SignException
	 */
	public static String SignMD5(Map<String,Object> map) throws SignException{
		//清除空的值
		Iterator<String> keyIt = map.keySet().iterator();
		while(keyIt.hasNext()){
			String key = keyIt.next();
			Object value = map.get(key);
			if(value==null){
				map.remove(key);
			}
		}
		//组成数组
		String[] items = new String[map.size()];
		StringBuilder source=new StringBuilder();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		int i=0;
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			items[i] = String.format("[0]=[1]", entry.getValue(),entry.getKey());
			i++;
		}
		//排序
		Arrays.sort(items);
		//拼接字符串
		for(String item:items){
			if(source.length()>0){
				source.append("&");
			}
			source.append(item);
		}
		try {
			//MD5
			return MD5Util.Bit32(source.toString()).toLowerCase();
		} catch (Exception e) {
			throw new SignException("",e);
		}
	}
}
