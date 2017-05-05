package com.jiang.e_box;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Bitmap bitmap;
    String imageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.action_image);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        initeven();
    }

    private void initeven() {
        if (SharedPreferencesUtil.getInstance(this).getbooleanValue("新图")){

        }else {

        }
        bitmap = ImageLoader.getInstance().loadImageSync("http://192.168.100.122/images/图片二.jpg");
        saveBitmap();
        ImageLoader.getInstance().displayImage("http://192.168.100.122/images/图片二.jpg",imageView);
//        ImageLoader.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493981810740&di=151c1d23fef6cf7447ee04ba8ff7bda1&imgtype=0&src=http%3A%2F%2Fimg.sj33.cn%2Fuploads%2Fallimg%2F201302%2F1-130201105055.jpg", imageView);

    }

    /**
     * 保存方法
     */
    public void saveBitmap() {
        File f = new File("/sdcard/namecard/", "E_BOX_SHOW");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class getImg extends AsyncTask<String ,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
