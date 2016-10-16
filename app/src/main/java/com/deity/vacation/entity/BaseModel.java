package com.deity.vacation.entity;

import android.content.Context;

import com.deity.vacation.data.DTApplication;

/**
 * Model基类
 * Created by Deity on 2016/10/16.
 */

public class BaseModel {

    public int CODE_NULL=1000;
    public static int CODE_NOT_EQUAL=1001;

    public static final int DEFAULT_LIMIT=20;

    public Context getContext(){
        return DTApplication.INSTANCE();
    }
}
