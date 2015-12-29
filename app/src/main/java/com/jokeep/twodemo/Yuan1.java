package com.jokeep.twodemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by wbq501 on 2015-12-25 10:26.
 * twodemo
 */
public class Yuan1 extends Activity implements RoundSpinView.onRoundSpinViewListener {
    private RoundSpinView rsv_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuan1);
        initView();
    }
    private void initView(){
        rsv_test = (RoundSpinView)this.findViewById(R.id.rsv_test);
        rsv_test.setOnRoundSpinViewListener(Yuan1.this);
    }

    @Override
    public void onSingleTapUp(int position) {
        // TODO Auto-generated method stub
        switch (position) {
            case 0:
                Toast.makeText(Yuan1.this, "place:0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(Yuan1.this, "place:1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(Yuan1.this, "place:2", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
