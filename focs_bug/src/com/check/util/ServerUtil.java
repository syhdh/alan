package com.check.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.check.spider.DataHandle;

/**
 * <p>ClassName: PathUtil</p>
 * <p>Description: ץȡ���������</p>
 * <p>Author: FPM0284</p>
 * <p>Date: 2018��1��12��</p>
 */
public class ServerUtil {

    /**
     * <p>Description: ץȡ���������</p>
     */
    public Map<Integer, List<String>> getAgentIp() {
        System.out.println("��ȡ���´��������������");
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        DataHandle handle = new DataHandle();
        //������Ѵ���IP
        //���������
        /*String url1 = "http://www.xicidaili.com/nn/";
        String url2 = "http://www.xicidaili.com/nn/2";
        String url3 = "http://www.xicidaili.com/nn/3";*/
        
        String url1 = "http://www.xicidaili.com/nt/";
        String url2 = "http://www.xicidaili.com/nt/2";
        String url3 = "http://www.xicidaili.com/nt/3";
        //String url4 = "http://www.xicidaili.com/nn/4";
        //ʹ��jsoupץȡip�Ͷ˿ڣ�����http��https�����Լ����ʱ��
        try {
            Document document1 = Jsoup.connect(url1).get();
            Document document2 = Jsoup.connect(url2).get();
            Document document3 = Jsoup.connect(url3).get();
            List<Document> listele = new ArrayList<Document>();
            listele.add(document1);
            listele.add(document2);
            listele.add(document3);
            //ץȡ��ip�б����ݼ���
            Integer index = 0;
            for (int w = 0; w < listele.size(); w++) {
                Document document = listele.get(w);
                Elements trList = document.getElementsByTag("tr");
                for (int i = 1; i < trList.size(); i++) {
                    index++;
                    List<String> list = new ArrayList<String>();
                    Element element = trList.get(i);
                    Elements tdList = element.getElementsByTag("td");
                    for (int j = 0; j < tdList.size(); j++) {
                        switch (j) {
                        case 1:
                            //������ip��ַ
                            Element ip = tdList.get(j);
                            String td = handle.getTd(ip.toString());
                            list.add(td);
                            break;
                        case 2:
                            //�������˿�
                            Element port = tdList.get(j);
                            String td2 = handle.getTd(port.toString());
                            list.add(td2);
                            break;
                        case 4:
                            //�Ƿ�������ʽ
                            Element lose = tdList.get(j);
                            String td3 = handle.getTd(lose.toString());
                            list.add(td3);
                            break;
                        case 5:
                            //��������
                            Element requestKind = tdList.get(j);
                            String td4 = handle.getTd(requestKind.toString());
                            list.add(td4);
                            break;
                        case 8:
                            //���������ʱ��
                            Element aliveTime = tdList.get(j);
                            String td5 = handle.getTd(aliveTime.toString());
                            list.add(td5);
                            break;
                        case 9:
                            //����ʱ��
                            Element freshTime = tdList.get(j);
                            String td6 = handle.getTd(freshTime.toString());
                            list.add(td6);
                            break;
                        default:
                            break;
                        }
                    }
                    map.put(index, list);
                }
            }
        } catch (Exception e) {
            System.err.println("ץȡ���������ʧ�ܣ�����");
            e.printStackTrace();
            return map;
        }
        return map;
    }

    /**
     * <p>Description: �����ȡ���������</p>
     * @return ����һ���ʺϵķ�������Ϣ����
     */
    public List<List<String>> getServer() {
        List<List<String>> list = new ArrayList<List<String>>();
        ServerUtil serverUtil = new ServerUtil();
        Map<Integer, List<String>> agentIp = serverUtil.getAgentIp();
        for (Integer index : agentIp.keySet()) {
            List<String> list2 = agentIp.get(index);
            String dayStr = list2.get(4);
            if (dayStr.endsWith("��")) {
                int indexOf = dayStr.indexOf("��");
                if (indexOf != -1) {
                    String day = dayStr.substring(0, indexOf);
                    if (Integer.parseInt(day) > 1) {
                        list.add(list2);
                    }
                }
            } else if (dayStr.endsWith("Сʱ")) {
                int indexOf = dayStr.indexOf("Сʱ");
                if (indexOf != -1) {
                    String day = dayStr.substring(0, indexOf);
                    if (Integer.parseInt(day) > 10) {
                        list.add(list2);
                    }
                }
            }
        }
        return list;
    }
    
    
    //���һ��http����ķ�����
    public List<String> getHttpServer(){
    	ServerUtil serverUtil = new ServerUtil();
    	List<List<String>> server = serverUtil.getServer();
    	for (int i = 0; i < server.size(); i++) {
    		List<String> list = server.get(i);
    		String postType = list.get(3);
    		if ("http".equals(postType)||"HTTP".equals(postType)) {
				return list;
			}
			
		}
    	
    	return null;
    }
    
    //���һ��https
    public List<String> getHttpsServer(){
    	ServerUtil serverUtil = new ServerUtil();
    	List<List<String>> server = serverUtil.getServer();
    	for (int i = 0; i < server.size(); i++) {
    		List<String> list = server.get(i);
    		String postType = list.get(3);
    		//String postType1 = list.get(2);
    		if ("https".equals(postType)||"HTTPS".equals(postType)) {
				return list;
			}
			
		}
    	
    	return null;
    }
    
    
    public static void main(String[] args) {
        ServerUtil server = new ServerUtil();
        System.out.println(server.getHttpServer().toString());
    }
}
