package com.check.test;

import com.check.spider.CountThread2;



/**
 * <p>
 * ClassName: SearchData
 * </p>
 * <p>
 * Description: ��Ҫ��ѯ������
 * </p>
 * <p>
 * Author: FPM0284
 * </p>
 * <p>
 * Date: 2018��1��11��
 * </p>
 */
public class SearchData {

	public static void main(String[] args) {
	    System.setProperty("http.maxRedirects", "50");
        System.getProperties().setProperty("proxySet", "true");
        // ��������ã�ֻҪ����IP�ʹ���˿���ȷ,�������Ҳ����
        String ip = "139.224.138.106";
        System.getProperties().setProperty("http.proxyHost", ip);
        System.getProperties().setProperty("http.proxyPort", "80");
		/*CountThread count = new CountThread();
		Thread th1 = new Thread(count);
		th1.start();*/
		
		
		CountThread2 count2 = new CountThread2();
		Thread th2 = new Thread(count2);
		th2.start();
	}
}
