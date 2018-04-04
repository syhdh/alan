/**
 * TimeRand.java
 * Created at 2018��3��14��
 * Created by FPM0284
 * Copyright (C) 2014-2017 FNConn, All rights reserved.
 */
package com.check.spider;

import java.util.Random;

/**
 * <p>ClassName: TimeRand</p>
 * <p>Description: �����ȡ����ͣ��ʱ��</p>
 * <p>Author: FPM0284</p>
 * <p>Date: 2018��1��11��</p>
 */
public class TimeRand {

    /**
     * <p>Description: ����3��С��5���ͣ��</p>
     * @return ͣ��ʱ��
     */
    public int getTimeOverThree() {
        int max = 5000;
        int min = 3000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: ����5��С��8���ͣ��</p>
     * @return ͣ��ʱ��
     */
    public int getTimeOverFive() {
        int max = 8000;
        int min = 5000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: ����7��С��10���ͣ��</p>
     * @return ͣ��ʱ��
     */
    public int getTimeOverSeven() {
        int max = 10000;
        int min = 7000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: ��ȡ3���ڵ��������</p>
     * @return ͣ��ʱ��
     */
    public int getTimeInThree() {
        int max = 3000;
        int min = 1000;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * <p>Description: ��ȡ1�����ڵ��������</p>
     * @return ͣ��ʱ��
     */
    public int getTimeInSecond() {
        int max = 1000;
        int min = 300;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
