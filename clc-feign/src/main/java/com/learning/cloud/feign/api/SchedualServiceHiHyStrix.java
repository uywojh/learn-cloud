package com.learning.cloud.feign.api;

import org.springframework.stereotype.Service;

@Service
public class SchedualServiceHiHyStrix implements SchedualServiceHi{

	@Override
	public String sayHiFromClientOne(String name) {
		// TODO Auto-generated method stub
//		 throw new NullPointerException(" String sayHiFromClientOne() 服务不可用。。");
		return "hello fallback!";
	}
}
