package com.deity.vacation.data;

import android.app.Application;

import com.deity.vacation.base.UniversalImageLoader;
import com.deity.vacation.handler.DTMessageHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/**
 * Vocation application
 * Created by Deity on 2016/10/16.
 */

public class DTApplication extends Application {
    private static DTApplication INSTANCE;
    public static DTApplication INSTANCE(){
        return INSTANCE;
    }
    private void setInstance(DTApplication app) {
        setBmobIMApplication(app);
    }
    private static void setBmobIMApplication(DTApplication a) {
        DTApplication.INSTANCE = a;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            //im初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new DTMessageHandler(this));
        }
        //uil初始化
        UniversalImageLoader.initImageLoader(this);
    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
