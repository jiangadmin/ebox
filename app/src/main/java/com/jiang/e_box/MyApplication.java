package com.jiang.e_box;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * Created by  jiang
 * on 2017/5/5.
 * Email: www.fangmu@qq.com
 * Phone：186 6120 1018
 * Purpose:
 * update：
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    public static final String Mi_Push_APP_ID = "2882303761517573968";
    public static final String Mi_Push_APP_KEY = "5711757355968";
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化push推送服务
        if (shouldInit()) {
//            EMOptions emOptions = new EMOptions();
//            emOptions.setMipushConfig(Mi_Push_APP_ID, Mi_Push_APP_KEY);
            MiPushClient.registerPush(this, Mi_Push_APP_ID, Mi_Push_APP_KEY);
            Log.e(TAG, "初始化小米推送");
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
