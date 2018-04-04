/**
 * DataHandle.java
 * Created at 2018��3��14��
 * Created by FPM0284
 * Copyright (C) 2014-2017 FNConn, All rights reserved.
 */
package com.check.spider;

import java.util.Map;

/**
 * <p>ClassName: DataHandle</p>
 * <p>Description: ���ݴ���</p>
 * <p>Author: FPM0284</p>
 * <p>Date: 2018��1��12��</p>
 */
public class DataHandle {

    /**
     * <p>Description: ��ȡtd����</p>
     * @param ele td�ı�ǩ
     * @return td������
     */
    public String getTd(String ele) {
        String tdEle = ele;
        int firt = tdEle.indexOf(">");
        int last = tdEle.lastIndexOf("<");
        String substring = tdEle.substring(firt + 1, last);
        return substring;
    }

    /**
     * <p>Description: ���ò���</p>
     * @param paramsf ����1
     * @param paramss ����2 
     * @param webPath ҳ��·��
     * @param searchName ��ѯ����
     * @param paramMap ��������
     * @return map
     */
    public Map<String, String> setParams(String paramsf, String paramss, String webPath, String searchName,
            Map<String, String> paramMap) {
        if (paramsf.contains("����")) {
            paramMap.put("case_Num", paramss);
        }
        if (paramsf.contains("����") || paramsf.contains("��������")) {
            paramMap.put("respondent", paramss);
        }
        if (paramsf.contains("ԭ��") || paramsf.contains("������") && !paramsf.contains("��������") && !paramsf.contains("����")) {
            paramMap.put("accuser", paramss);
        }
        /*
         * if (params1.contains("����ʱ��")) { paramMap.put("register_case_time",
         * params2); }
         */
        /*
         * if (params1.contains("��ͥʱ��")) { paramMap.put("open_court_time",
         * params2); }
         */
        if (paramsf.contains("�᰸ʱ��")) {
            paramMap.put("close_case_time", paramss);
        }
        /*
         * if (params1.contains("����״̬")) { paramMap.put("case_status", params2);
         * '�᰸','�鵵','����','δ�鵵' }
         */
        if (paramsf.contains("����״̬")) {
            if (paramss.contains("�᰸")) {
                paramMap.put("case_status", "�᰸");
            }
            if (paramss.contains("�鵵")) {
                paramMap.put("case_status", "�鵵");
            }
            if (paramss.contains("����")) {
                paramMap.put("case_status", "����");
            }
            if (paramss.contains("δ�鵵")) {
                paramMap.put("case_status", "δ�鵵");
            }
        }
        /*
         * if (params1.contains("ԭ��")||params1.contains("һ��")) {
         * paramMap.put("judge_person_first", params2); } if
         * (params1.contains("����")||params1.contains("����")) {
         * paramMap.put("judge_person_second", params2); } if
         * (params1.contains("����")||params1.contains("����")) {
         * paramMap.put("judge_person_third", params2); }
         */
        /*
         * if (params1.contains("����")||params1.contains("����")) {
         * paramMap.put("judge_person_first", params2); }
         */
        if (paramsf.contains("������")) {
            paramMap.put("accuser", paramss);
        }
        if (paramsf.contains("��ִ����")) {
            paramMap.put("respondent", paramss);
        }
        paramMap.put("mesg_orign", webPath);
        /* paramMap.put("search_param", searchName); */
        return paramMap;
    }

}
