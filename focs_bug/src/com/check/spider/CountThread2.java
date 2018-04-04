package com.check.spider;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;

public class CountThread2 implements Runnable{

	@Override
	public void run() {
		System.out.println("深圳爬虫启动");
		System.setProperty("http.maxRedirects", "50");
        System.getProperties().setProperty("proxySet", "true");
        // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
        String ip = "139.224.138.106";
        System.getProperties().setProperty("http.proxyHost", ip);
        System.getProperties().setProperty("http.proxyPort", "80");
				// 设置查询对象
				List<String> dataList = new ArrayList<String>();
				dataList.add("王永");
				dataList.add("高菊");
				dataList.add("李民");
				/*dataList.add("宝钢股份");
				dataList.add("江苏亿科达");
				dataList.add("汇金智融");
				dataList.add("晨鸣纸业");
				dataList.add("漳泽电力");
				dataList.add("上汽大众");
				dataList.add("宜家");
				dataList.add("永辉");
				dataList.add("华联");
				dataList.add("星巴克");*/
				
				
				
				//设置爬虫参数
				SZ sz = new SZ();
				WebClient webClient = sz.setParams();
				String picSavePath2 = "D:\\work\\pic1.png";
				String picType1 = "n4";
				//这个没用
				String appcode = "fsadfsafsa";

				
				//调用查询方法
				SZ.getCaseBook(dataList, webClient, picSavePath2, picType1,appcode);
				System.out.println("抓取完毕");
	}

}
