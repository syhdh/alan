package com.check.spider;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;

public class CountThread2 implements Runnable{

	@Override
	public void run() {
		System.out.println("������������");
		System.setProperty("http.maxRedirects", "50");
        System.getProperties().setProperty("proxySet", "true");
        // ��������ã�ֻҪ����IP�ʹ���˿���ȷ,�������Ҳ����
        String ip = "139.224.138.106";
        System.getProperties().setProperty("http.proxyHost", ip);
        System.getProperties().setProperty("http.proxyPort", "80");
				// ���ò�ѯ����
				List<String> dataList = new ArrayList<String>();
				dataList.add("����");
				dataList.add("�߾�");
				dataList.add("����");
				/*dataList.add("���ֹɷ�");
				dataList.add("�����ڿƴ�");
				dataList.add("�������");
				dataList.add("����ֽҵ");
				dataList.add("�������");
				dataList.add("��������");
				dataList.add("�˼�");
				dataList.add("����");
				dataList.add("����");
				dataList.add("�ǰͿ�");*/
				
				
				
				//�����������
				SZ sz = new SZ();
				WebClient webClient = sz.setParams();
				String picSavePath2 = "D:\\work\\pic1.png";
				String picType1 = "n4";
				//���û��
				String appcode = "fsadfsafsa";

				
				//���ò�ѯ����
				SZ.getCaseBook(dataList, webClient, picSavePath2, picType1,appcode);
				System.out.println("ץȡ���");
	}

}
