package com.jiang.e_box.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jiang.e_box.Activity.MainActivity;

/**
 * @author 编写人： xiaox
 * @date 创建时间： 2016/8/20
 * @Description 功能描述： 该类用来实现该app开机自动运行
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

    /**
     * demo2: 可以实现开机自动打开软件并运行。
     */
    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.d("XRGPS", "BootReceiver.onReceive: " + intent.getAction());
//        System.out.println("自启动程序即将执行");
//        //MainActivity就是开机显示的界面
//        Intent mBootIntent = new Intent(context, MainActivity.class);
//        //下面这句话必须加上才能开机自动运行app的界面
//        mBootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(mBootIntent);

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent sayHelloIntent=new Intent(context,MainActivity.class);
            sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(sayHelloIntent);
        }

    }
}  