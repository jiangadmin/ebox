package com.jiang.e_box.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.jiang.e_box.Application.MyApplication;
import com.jiang.e_box.R;
import com.jiang.e_box.Utils.SharedPreferencesUtil;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    static Activity activity;
    static ImageView imageView;
    static String imageName;
    static String mFilePath;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        imageView = (ImageView) findViewById(R.id.action_image);
        MiPushClient.setUserAccount(this, "ebox1", null);

        mFilePath = Environment.getExternalStorageDirectory().getPath() + "/DCIM/";

        initeven();
    }



    public static void initeven() {
        Log.e(TAG, "initeven");
        if (SharedPreferencesUtil.getInstance(activity).getbooleanValue("新图")) {
            Log.e(TAG, "有新图");
            new GetImg().execute();
        } else {
            Log.e(TAG, "没有新图" + mFilePath + imageName + ".png");
            Bitmap bitmap = getDiskBitmap(mFilePath + SharedPreferencesUtil.getInstance(activity).getStringValue("文件名") + ".png");

            imageView.setImageBitmap(bitmap);
        }

    }

    /**
     * 更新图片
     */
    public static void UpdateImg() {
        Log.e(TAG, "UpdateImg: 接收到更新图片指令");
        new GetImg().execute();
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap bitmap) {
        Log.e(TAG, "saveBitmap: 保存图片");
        File f = new File(mFilePath, MyApplication.ImageName + ".png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Log.e(TAG, "saveBitmap: 保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "saveBitmap: 保存失败");
        } catch (IOException e) {
            Log.e(TAG, "saveBitmap: 保存失败");
            e.printStackTrace();
        }

    }

    /**
     * 获取网络图片 并保存
     */
    public static class GetImg extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //加载一个网络图片
                InputStream is = new URL(MyApplication.ImageUrl).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                saveBitmap(bitmap);
                return "Success";
            } catch (Exception e) {
                Log.e(TAG, "doInBackground: " + e);
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (TextUtils.isEmpty(s)) {
                Log.e(TAG, "onPostExecute: 更新失败");
            } else {
                Log.e(TAG, "onPostExecute: 更新完成");
                SharedPreferencesUtil.getInstance(activity).putValue("新图", false);
                initeven();
            }


        }
    }

    /**
     * 获取本地图片
     *
     * @param pathString 图片地址
     * @return
     */
    private static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
        }

        return bitmap;
    }
}
