/**
 * DataHandle.java
 * Created at 2018年3月14日
 * Created by FPM0284
 * Copyright (C) 2014-2017 FNConn, All rights reserved.
 */
package com.check.spider;

import java.util.Map;

/**
 * <p>ClassName: DataHandle</p>
 * <p>Description: 数据处理</p>
 * <p>Author: FPM0284</p>
 * <p>Date: 2018年1月12日</p>
 */
public class DataHandle {

    /**
     * <p>Description: 获取td内容</p>
     * @param ele td的标签
     * @return td的内容
     */
    public String getTd(String ele) {
        String tdEle = ele;
        int firt = tdEle.indexOf(">");
        int last = tdEle.lastIndexOf("<");
        String substring = tdEle.substring(firt + 1, last);
        return substring;
    }

    /**
     * <p>Description: 设置参数</p>
     * @param paramsf 参数1
     * @param paramss 参数2 
     * @param webPath 页面路径
     * @param searchName 查询参数
     * @param paramMap 参数集合
     * @return map
     */
    public Map<String, String> setParams(String paramsf, String paramss, String webPath, String searchName,
            Map<String, String> paramMap) {
        if (paramsf.contains("案号")) {
            paramMap.put("case_Num", paramss);
        }
        if (paramsf.contains("被告") || paramsf.contains("被上诉人")) {
            paramMap.put("respondent", paramss);
        }
        if (paramsf.contains("原告") || paramsf.contains("上诉人") && !paramsf.contains("被上诉人") && !paramsf.contains("被告")) {
            paramMap.put("accuser", paramss);
        }
        /*
         * if (params1.contains("立案时间")) { paramMap.put("register_case_time",
         * params2); }
         */
        /*
         * if (params1.contains("开庭时间")) { paramMap.put("open_court_time",
         * params2); }
         */
        if (paramsf.contains("结案时间")) {
            paramMap.put("close_case_time", paramss);
        }
        /*
         * if (params1.contains("案件状态")) { paramMap.put("case_status", params2);
         * '结案','归档','审理','未归档' }
         */
        if (paramsf.contains("案件状态")) {
            if (paramss.contains("结案")) {
                paramMap.put("case_status", "结案");
            }
            if (paramss.contains("归档")) {
                paramMap.put("case_status", "归档");
            }
            if (paramss.contains("审理")) {
                paramMap.put("case_status", "审理");
            }
            if (paramss.contains("未归档")) {
                paramMap.put("case_status", "未归档");
            }
        }
        /*
         * if (params1.contains("原审")||params1.contains("一审")) {
         * paramMap.put("judge_person_first", params2); } if
         * (params1.contains("再审")||params1.contains("二审")) {
         * paramMap.put("judge_person_second", params2); } if
         * (params1.contains("再审")||params1.contains("二审")) {
         * paramMap.put("judge_person_third", params2); }
         */
        /*
         * if (params1.contains("法官")||params1.contains("法官")) {
         * paramMap.put("judge_person_first", params2); }
         */
        if (paramsf.contains("申请人")) {
            paramMap.put("accuser", paramss);
        }
        if (paramsf.contains("被执行人")) {
            paramMap.put("respondent", paramss);
        }
        paramMap.put("mesg_orign", webPath);
        /* paramMap.put("search_param", searchName); */
        return paramMap;
    }

}
