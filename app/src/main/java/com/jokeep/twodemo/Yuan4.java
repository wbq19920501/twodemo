package com.jokeep.twodemo;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wbq501 on 2015-12-25 16:19.
 * twodemo
 */
public class Yuan4 extends Activity{
    RoundImg img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuanimg);
        img = (RoundImg) findViewById(R.id.yuanimg);
        img.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        new Thread(){
            @Override
            public void run() {
                PostExecutor postExecutor = PostExecutor.getInstance("http://112.74.92.167/mo");
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("method", "login");
                data.put("card_no", "123456x");
                data.put("password", "111111");
                try {
                    System.out.println(postExecutor.postFor(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }
}
