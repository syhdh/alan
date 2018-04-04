/**
 * TimeRand.java
 * Created at 2018年3月14日
 * Created by FPM0284
 * Copyright (C) 2014-2017 FNConn, All rights reserved.
 */
package com.check.spider;

import java.util.Random;

/**
 * <p>ClassName: TimeRand</p>
 * <p>Description: 随机获取爬虫停顿时间</p>
 * <p>Author: FPM0284</p>
 * <p>Date: 2018年1月11日</p>
 */
public class TimeRand {

    /**
     * <p>Description: 大于3秒小于5秒的停顿</p>
     * @return 停顿时长
     */
    public int getTimeOverThree() {
        int max = 5000;
        int min = 3000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: 大于5秒小于8秒的停顿</p>
     * @return 停顿时长
     */
    public int getTimeOverFive() {
        int max = 8000;
        int min = 5000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: 大于7秒小于10秒的停顿</p>
     * @return 停顿时长
     */
    public int getTimeOverSeven() {
        int max = 10000;
        int min = 7000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: 获取3秒内的随机数据</p>
     * @return 停顿时长
     */
    public int getTimeInThree() {
        int max = 3000;
        int min = 1000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: 获取1秒以内的随机数据</p>
     * @return 停顿时长
     */
    public int getTimeInSecond() {
        int max = 1000;
        int min = 300;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
