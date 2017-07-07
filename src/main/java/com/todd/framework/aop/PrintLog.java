package com.todd.framework.aop;

import java.util.Date;

public class PrintLog {
	
	public void afterPrintLog(){
		System.out.println(new Date()+"日志：执行之后");
	}
	public void beforePrintLog(){
		System.out.println(new Date()+"日志：执行之前");
	}
	
}
