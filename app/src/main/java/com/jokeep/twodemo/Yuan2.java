package com.jokeep.twodemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by wbq501 on 2015-12-25 11:21.
 * twodemo
 */
public class Yuan2 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RoundSpinView2(getApplicationContext(), 300, 300, 200));
    }
}
