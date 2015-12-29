package com.jokeep.twodemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import static android.support.v4.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

/**
 * Created by wbq501 on 2015-12-22 14:55.
 * demo
 */
public class FrameLayoutActivity extends FragmentActivity implements View.OnTouchListener,GestureDetector.OnGestureListener{
    ImageView imganim;
    TextView henxian,henxian1,henxian_1;
    LinearLayout donghua1,textanim,xian;
    FrameLayout item0,item1,item2;
    FrameLayout bj,xianfram,textfram,imgfram,caidanfram;

    int caidanframtop,caidanframbottom;
    int imganimtop,imganimbottom,imganimleft,imganimright;

    /**
     * changanim 改变动画的值
     */
    int changanim = 1;
    ViewPager pager;
    int changpager = 1;
    int pagertop,bjbottom;

    private boolean huadongchangpager = true;
    private ArrayList<Fragment> fragmentList;

    int item0left,item1left,item2left;
    int henxianleft,henxian_1left,henxian1left;
    int item0right,item1right,item2right;
    int henxianright,henxian_1right,henxian1right;
    int item0top,item0buttom,item1top,item1buttom,item2top,item2buttom;
    int changy = 30;
    int pingmuw,pingmuh;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;


    private static final int FLING_MIN_DISTANCE = 30;
    private static final int FLING_MIN_VELOCITY = 0;
    GestureDetector mGestureDetector;
    private boolean upchang = false;//向上改变
    private boolean animend = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentlayout);
        init();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        pingmuw = dm.widthPixels;
        pingmuh = dm.heightPixels;
        menudrawlayout();
        onclickitem();
    }

    private void onclickitem() {
        item0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upchang) {
                    onclickchangepager(v);
                } else {
                    leftrightclick(v);
                }
            }
        });
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upchang) {
                    onclickchangepager(v);
                } else {
                    leftrightclick(v);
                }
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upchang) {
                    onclickchangepager(v);
                } else {
                    leftrightclick(v);
                }
            }
        });
    }

    private void onclickchangepager(View v) {
        getzuobiao();
        Toast.makeText(FrameLayoutActivity.this,v.getId()+"----"+changanim,Toast.LENGTH_SHORT).show();
        //0 个人应用 1 业务系统 2 日常办公
        if (v.getId()==R.id.item2){
            onclickchangevalues(2);
        }else if(v.getId()==R.id.item0){
            onclickchangevalues(0);
        }else if (v.getId()==R.id.item1){
            onclickchangevalues(1);
        }
    }

    private void onclickchangevalues(int i) {
        pager.setCurrentItem(i);
    }

    private void leftrightclick(View v) {
        getzuobiao();
        Toast.makeText(FrameLayoutActivity.this,v.getId()+"----"+changanim,Toast.LENGTH_SHORT).show();
        if(changanim==0&&v.getId()==R.id.item2)return;
        if(changanim==1&&v.getId()==R.id.item1)return;
        if(changanim==2&&v.getId()==R.id.item0)return;

        //0 个人应用 1 业务系统 2 日常办公
        switch (changanim)
        {
            case 0:
               if(v.getId()==R.id.item1) {
                   onclickanimleft();//左转
               }else if(v.getId()==R.id.item2){
//                   onclickanimright();//右转
               }else if (v.getId()==R.id.item0){
                   onclickanimright();//
               }
                break;
            case 1:
                if(v.getId()==R.id.item1) {

                }else if(v.getId()==R.id.item2){
                    onclickanimright();//右转
                }else if (v.getId()==R.id.item0){
                    onclickanimleft();//左转
                }
                break;
            case 2:
                if(v.getId()==R.id.item1) {
                    onclickanimright();//右转
                }else if(v.getId()==R.id.item2){
                    onclickanimleft();//左滑
                }else if (v.getId()==R.id.item0){

                }
                break;
        }
    }

    private void menudrawlayout() {
        final DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawerlayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);
        TextView leftmenubtn = (TextView) findViewById(R.id.leftmenubtn);
        TextView rightmenubtn = (TextView) findViewById(R.id.rightmenubtn);
        leftmenubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.LEFT);
            }
        });
        TextView ceshibtn = (TextView) drawerlayout.findViewById(R.id.ceshibtn);
        ceshibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FrameLayoutActivity.this, "左边的点击事件", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        getzuobiao();
        if (e1.getX()-e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling left
            Toast.makeText(this, "向左手势" + changanim, Toast.LENGTH_SHORT).show();
//            if (animend){
                if (upchang){

                }else {
                    onclickanimright();
                }
//            }
        } else if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling right
            Toast.makeText(this, "向右手势" + changanim, Toast.LENGTH_SHORT).show();
//            if (animend){
                if (upchang){

                }else {
                    onclickanimleft();
                }
//            }
        }
        return false;
    }

    /**
     * onclickanimleft 左转
     */
    private void onclickanimleft() {
        imgrightxianleft(changanim);
        animtextright(changanim);
        changanim +=1;
        if (changanim == 3)
            changanim = 0;
        changpager(changanim);
    }

    /**
     * 右转
     */
    private void onclickanimright() {
        imgleftxianright(changanim);
        animtextleft(changanim);
        changanim -=1;
        if (changanim == -1)
            changanim = 2;
        changpager(changanim);
    }

    private void changpager(int i) {
        huadongchangpager = false;
        switch (changanim){
            case 0:
                i = 2;
                break;
            case 1:
                i = 1;
                break;
            case 2:
                i = 0;
                break;
        }
        changpager = i;
        pager.setCurrentItem(i);
        huadongchangpager = true;
    }
    public void upanim() {
        animend = false;
        getzuobiao();
        int changy = caidanframbottom - caidanframtop;
        AnimatorSet anim = new AnimatorSet();
        ObjectAnimator oaimgy = ObjectAnimator.ofFloat(imgfram, "Y", 0, -changy);
        ObjectAnimator oaalphay = ObjectAnimator.ofFloat(imgfram, "alpha", 1f, 0f);
        ObjectAnimator oatexty = ObjectAnimator.ofFloat(textfram, "Y", 0, -changy);
        ObjectAnimator oaitem1y = null;
        switch (changanim){
            case 0:
                oaitem1y = ObjectAnimator.ofFloat(item2, "Y", item1top, -item0top);
                break;
            case 1:
                oaitem1y = ObjectAnimator.ofFloat(item1, "Y", item1top, -item0top);
                break;
            case 2:
                oaitem1y = ObjectAnimator.ofFloat(item0, "Y", item1top, -item0top);
                break;
        }
        ObjectAnimator oaxiany = ObjectAnimator.ofFloat(xianfram, "Y", 0, -(changy + (item1top - item0top)));
        ObjectAnimator oabjy = ObjectAnimator.ofFloat(bj, "Y", 0, -(bjbottom - pagertop));
        ObjectAnimator oapagey = ObjectAnimator.ofFloat(pager, "Y", pagertop, -changy);
        anim.setDuration(1000);

        anim.playTogether(oaimgy, oaalphay, oatexty, oaxiany, oabjy,oaitem1y);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                upchang = true;
                bj.setBackgroundColor(Color.BLUE);
//                twoFragment.changlist();
                Intent intent = new Intent();
                switch (changanim){
                    case 0:
                        intent.setAction("com.wbq.three");
                        break;
                    case 1:
                        intent.setAction("com.wbq.two");
                        break;
                    case 2:
                        intent.setAction("com.wbq.one");
                        break;
                }
                sendBroadcast(intent);
                animend = true;
                super.onAnimationEnd(animation);
            }
        });
    }
    public void downanim() {
        animend = false;
        getzuobiao();
        int changy = caidanframbottom - caidanframtop;
        AnimatorSet anim = new AnimatorSet();
        ObjectAnimator oaimgy = ObjectAnimator.ofFloat(imgfram, "Y", -changy, 0);
        ObjectAnimator oaalphay = ObjectAnimator.ofFloat(imgfram, "alpha", 0f, 1f);
        ObjectAnimator oatexty = ObjectAnimator.ofFloat(textfram, "Y", -changy, 0);
        ObjectAnimator oaitem1y = null;
        switch (changanim){
            case 0:
                oaitem1y = ObjectAnimator.ofFloat(item2, "Y", item0top, item1top);
                break;
            case 1:
                oaitem1y = ObjectAnimator.ofFloat(item1, "Y", item0top, item1top);
                break;
            case 2:
                oaitem1y = ObjectAnimator.ofFloat(item0, "Y", item0top, item1top);
                break;
        }
        ObjectAnimator oaxiany = ObjectAnimator.ofFloat(xianfram, "Y", -(changy + (item1top - item0top)), 0);
        ObjectAnimator oabjy = ObjectAnimator.ofFloat(bj, "Y", -(bjbottom - pagertop), 0);
        ObjectAnimator oapagey = ObjectAnimator.ofFloat(pager, "Y", pagertop, -changy);
        anim.setDuration(1000);
        anim.playTogether(oaimgy, oaalphay, oatexty, oaxiany, oabjy,oaitem1y);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                upchang = false;
                bj.setBackground(getResources().getDrawable(R.drawable.ic_head_bg1));
//                twoFragment.changlist();
                Intent intent = new Intent();
                switch (changanim){
                    case 0:
                        intent.setAction("com.wbq.three.down");
                        break;
                    case 1:
                        intent.setAction("com.wbq.two.down");
                        break;
                    case 2:
                        intent.setAction("com.wbq.one.down");
                        break;
                }
                sendBroadcast(intent);
                animend = true;
                super.onAnimationEnd(animation);
            }
        });
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        }
    };
    private void getzuobiao(){
        caidanframtop = caidanfram.getTop();
        caidanframbottom = caidanfram.getBottom();

        imganimtop = imganim.getTop();
        imganimbottom = imganim.getBottom();
        imganimleft = imganim.getLeft();
        imganimright = imganim.getRight();

        pagertop = pager.getTop();
        bjbottom = bj.getBottom();

        item0left = item0.getLeft();
        item1left = item1.getLeft();
        item2left = item2.getLeft();
        henxianleft = henxian.getLeft();
        henxian_1left = henxian_1.getLeft();
        henxian1left = henxian1.getLeft();
        imganimleft = imganim.getLeft();
        item0right = item0.getRight();
        item1right = item1.getRight();
        item2right = item2.getRight();
        henxianright = henxian.getRight();
        henxian_1right = henxian_1.getRight();
        henxian1right = henxian1.getRight();
        imganimright = imganim.getRight();
        imganimtop = imganim.getTop();
        item0top = item0.getTop();
        item0buttom = item0.getBottom();
        item1top = item1.getTop();
        item1buttom = item1.getBottom();
        item2top = item2.getTop();
        item2buttom = item2.getBottom();
    }
    private void animtextleft(int i) {
        final AnimatorSet animtext0 = new AnimatorSet();
        final AnimatorSet animtext1 = new AnimatorSet();
        final AnimatorSet animtext2 = new AnimatorSet();
        ObjectAnimator text0x = null;
        ObjectAnimator text0y = null;
        ObjectAnimator text1x = null;
        ObjectAnimator text1y = null;
        ObjectAnimator text2x = null;
        ObjectAnimator text2y = null;
        switch (i){
            case 0:
                text0x = ObjectAnimator.ofFloat(item1, "X", item0left, item0left);
                text0y = ObjectAnimator.ofFloat(item1, "Y", item0top, -changy);
                text1x = ObjectAnimator.ofFloat(item2, "X", item1left, item0left);
                text1y = ObjectAnimator.ofFloat(item2, "Y", item1top, item0top);
                text2x = ObjectAnimator.ofFloat(item0, "X", item2left, item1left);
                text2y = ObjectAnimator.ofFloat(item0, "Y", item2top, item1top);
                animtext0.playTogether(text0x, text0y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item1, "X", item2left, item2left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item1, "Y", -changy, item2top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text2x,text2y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
            case 1:
                text0x = ObjectAnimator.ofFloat(item0, "X", item0left, item0left);
                text0y = ObjectAnimator.ofFloat(item0, "Y", item0top, -changy);
                text1x = ObjectAnimator.ofFloat(item1, "X", item1left, item0left);
                text1y = ObjectAnimator.ofFloat(item1, "Y", item1top, item0top);
                text2x = ObjectAnimator.ofFloat(item2, "X", item2left, item1left);
                text2y = ObjectAnimator.ofFloat(item2, "Y", item2top, item1top);
                animtext0.playTogether(text0x, text0y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item0, "X", item2left, item2left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item0, "Y", -changy, item2top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text2x,text2y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
            case 2:
                text0x = ObjectAnimator.ofFloat(item2, "X", item0left, item0left);
                text0y = ObjectAnimator.ofFloat(item2, "Y", item0top, -changy);
                text1x = ObjectAnimator.ofFloat(item0, "X", item1left, item0left);
                text1y = ObjectAnimator.ofFloat(item0, "Y", item1top, item0top);
                text2x = ObjectAnimator.ofFloat(item1, "X", item2left, item1left);
                text2y = ObjectAnimator.ofFloat(item1, "Y", item2top, item1top);
                animtext0.playTogether(text0x, text0y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item2, "X", item2left, item2left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item2, "Y", -changy, item2top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text2x,text2y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
        }
    }
    private void imgleftxianright(int i) {
        switch (i){
            case 0:
                imganimleft(i);
                break;
            case 1:
                imganimleft(i);
                break;
            case 2:
                imganimleft(i);
                break;
        }
        ObjectAnimator oaxian = ObjectAnimator.ofFloat(henxian, "X", henxian.getLeft() - 100, henxian.getLeft());
        AnimatorSet asxian = new AnimatorSet();
        asxian.play(oaxian);
        asxian.setDuration(500);
        asxian.start();
    }
    private void imganimleft(final int i) {
        ObjectAnimator imgleft1_0 = ObjectAnimator.ofFloat(imganim, "X", imganimleft, -(imganimright - imganimleft));
        ObjectAnimator imgout = ObjectAnimator.ofFloat(imganim, "alpha", 1f, 0f);
        AnimatorSet imganim1_0 = new AnimatorSet();
        imganim1_0.playTogether(imgleft1_0, imgout);
        imganim1_0.setDuration(300);
        imganim1_0.start();
        imganim1_0.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator imgleft2_1 = null;
                ObjectAnimator imgint = null;
                switch (i) {
                    case 0:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text1));
                        break;
                    case 1:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text_1));
                        break;
                    case 2:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text_0));
                        break;
                }
                imgleft2_1 = ObjectAnimator.ofFloat(imganim, "X", pingmuw, imganimleft);
                imgint = ObjectAnimator.ofFloat(imganim, "alpha", 0f, 1f);
                AnimatorSet imganim2_1 = new AnimatorSet();
                imganim2_1.playTogether(imgleft2_1, imgint);
                imganim2_1.setDuration(300);
                imganim2_1.start();
                super.onAnimationEnd(animation);
            }
        });
    }
    private void imganimright(final int i) {
        ObjectAnimator imgright1_2 = ObjectAnimator.ofFloat(imganim, "X", imganimright, pingmuw + (imganimright - imganimleft));
        ObjectAnimator imgout = ObjectAnimator.ofFloat(imganim, "alpha", 1f, 0f);
        AnimatorSet imganim1_2 = new AnimatorSet();
        imganim1_2.playTogether(imgright1_2, imgout);
        imganim1_2.setDuration(300);
        imganim1_2.start();
        imganim1_2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator imgleft0_1 = null;
                ObjectAnimator imgint = null;
                switch (i) {
                    case 0:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text_0));
                        break;
                    case 1:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text1));
                        break;
                    case 2:
                        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text_1));
                        break;
                }
                imgleft0_1 = ObjectAnimator.ofFloat(imganim, "X", -(imganimright - imganimleft), imganimleft);
                imgint = ObjectAnimator.ofFloat(imganim, "alpha", 0f, 1f);
                AnimatorSet imganim0_1 = new AnimatorSet();
                imganim0_1.playTogether(imgleft0_1, imgint);
                imganim0_1.setDuration(300);
                imganim0_1.start();
                super.onAnimationEnd(animation);
            }
        });
    }
    private void imgrightxianleft(int i) {
        switch (i){
            case 0:
                imganimright(i);
                break;
            case 1:
                imganimright(i);
                break;
            case 2:
                imganimright(i);
                break;
        }
        ObjectAnimator oaxian = ObjectAnimator.ofFloat(henxian, "X", henxian.getLeft() + 100, henxian.getLeft());
        AnimatorSet asxian = new AnimatorSet();
        asxian.play(oaxian);
        asxian.setDuration(500);
        asxian.start();
    }
    private void animtextright(int i) {
        final AnimatorSet animtext0 = new AnimatorSet();
        final AnimatorSet animtext1 = new AnimatorSet();
        final AnimatorSet animtext2 = new AnimatorSet();
        ObjectAnimator text0x = null;
        ObjectAnimator text0y = null;
        ObjectAnimator text1x = null;
        ObjectAnimator text1y = null;
        ObjectAnimator text2x = null;
        ObjectAnimator text2y = null;
        switch (i){
            case 2:
                text0x = ObjectAnimator.ofFloat(item2, "X", item0left, item1left);
                text0y = ObjectAnimator.ofFloat(item2, "Y", item0top, changy);
                text1x = ObjectAnimator.ofFloat(item0, "X", item1left, item2left);
                text1y = ObjectAnimator.ofFloat(item0, "Y", item1top, item2top);
                text2x = ObjectAnimator.ofFloat(item1, "X", item2left, item2left);
                text2y = ObjectAnimator.ofFloat(item1, "Y", item2top, -changy);
                animtext0.playTogether(text2x, text2y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item1, "X", item0left, item0left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item1, "Y", -changy, item0top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text0x,text0y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
            case 1:
                text0x = ObjectAnimator.ofFloat(item0, "X", item0left, item1left);
                text0y = ObjectAnimator.ofFloat(item0, "Y", item0top, changy);
                text1x = ObjectAnimator.ofFloat(item1, "X", item1left, item2left);
                text1y = ObjectAnimator.ofFloat(item1, "Y", item1top, item2top);
                text2x = ObjectAnimator.ofFloat(item2, "X", item2left, item2left);
                text2y = ObjectAnimator.ofFloat(item2, "Y", item2top, -changy);
                animtext0.playTogether(text2x, text2y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item2, "X", item0left, item0left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item2, "Y", -changy, item0top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text0x,text0y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
            case 0:
                text0x = ObjectAnimator.ofFloat(item1, "X", item0left, item1left);
                text0y = ObjectAnimator.ofFloat(item1, "Y", item0top, changy);
                text1x = ObjectAnimator.ofFloat(item2, "X", item1left, item2left);
                text1y = ObjectAnimator.ofFloat(item2, "Y", item1top, item2top);
                text2x = ObjectAnimator.ofFloat(item0, "X", item2left, item2left);
                text2y = ObjectAnimator.ofFloat(item0, "Y", item2top, -changy);
                animtext0.playTogether(text2x, text2y);
                animtext0.setDuration(300);
                animtext0.start();
                animtext0.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ObjectAnimator textcopyx = ObjectAnimator.ofFloat(item0, "X", item0left, item0left);
                        ObjectAnimator textcopyy = ObjectAnimator.ofFloat(item0, "Y", -changy, item0top);
                        AnimatorSet animcopy = new AnimatorSet();
                        animcopy.playTogether(textcopyx, textcopyy);
                        animcopy.setDuration(300);
                        animcopy.start();
                    }
                });
                animtext1.playTogether(text1x, text1y);
                animtext1.setDuration(600);
                animtext1.start();
                animtext2.playTogether(text0x,text0y);
                animtext2.setDuration(600);
                animtext2.start();
                break;
        }
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {

    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    private void init() {
        mGestureDetector = new GestureDetector(this);
        imganim = (ImageView) findViewById(R.id.imganim);
        imganim.setImageDrawable(getResources().getDrawable(R.drawable.text_0));
        henxian = (TextView) findViewById(R.id.henxian);
        henxian_1 = (TextView) findViewById(R.id.henxian_1);
        henxian1 = (TextView) findViewById(R.id.henxian1);

        donghua1 = (LinearLayout) findViewById(R.id.donghua1);
        textanim = (LinearLayout) findViewById(R.id.textanim);
        xian = (LinearLayout) findViewById(R.id.xian);

        item0 = (FrameLayout) findViewById(R.id.item0);
        item1 = (FrameLayout) findViewById(R.id.item1);
        item2 = (FrameLayout) findViewById(R.id.item2);

        bj = (FrameLayout) findViewById(R.id.bj);
        xianfram = (FrameLayout) findViewById(R.id.xianfram);
        textfram = (FrameLayout) findViewById(R.id.textfram);
        imgfram = (FrameLayout) findViewById(R.id.imgfram);
        caidanfram = (FrameLayout) findViewById(R.id.caidanfram);
        xianfram.setOnTouchListener(this);
        xianfram.setLongClickable(true);
        imgfram.setOnTouchListener(this);
        imgfram.setLongClickable(true);
        textfram.setOnTouchListener(this);
        textfram.setLongClickable(true);

        pager = (ViewPager) findViewById(R.id.pager);
        fragmentList = new ArrayList<Fragment>();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        pager.setCurrentItem(1);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (upchang){
                    getzuobiao();
                    switch (changanim){
                        case 0:
                            onclickheadanim0(position);
                            break;
                        case 1:
                            onclickheadanim1(position);
                            break;
                        case 2:
                            onclickheadanim2(position);
                            break;
                    }
                    changpager = position;
                }else {
                    if (huadongchangpager){
                        Toast.makeText(FrameLayoutActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                        getzuobiao();
                        switch (position){
                            case 0:
                                imgrightxianleft(1);
                                animtextright(1);
                                changanim +=1;
                                break;
                            case 1:
                                int i = changpager - position;
                                switch (i){
                                    case 0:
                                        break;
                                    case 1:
                                        imgrightxianleft(changanim);
                                        animtextright(changanim);
                                        changanim +=1;
                                        if (changanim == 3)
                                            changanim = 0;
                                        break;
                                    case -1:
                                        imgleftxianright(changanim);
                                        animtextleft(changanim);
                                        changanim -=1;
                                        if (changanim == -1)
                                            changanim = 2;
                                        break;
                                }
                                break;
                            case 2:
                                imgleftxianright(1);
                                animtextleft(1);
                                changanim -=1;
                                break;
                        }
                        changpager = position;
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onclickheadanim2(int position) {
        switch (position){
            case 0:
                int i = changpager - position;
                switch (i){
                    case 1:
                        uphenxian2_1();
                        break;
                    case 2:
                        uphenxian0_1();
                        break;
                }
                break;
            case 1:
                uphenxian1_2();
                break;
            case 2:
                uphenxian1_0();
                break;
        }
    }

    private void onclickheadanim0(int position) {
        switch (position){
            case 0:
                uphenxian1_2();
                break;
            case 1:
                uphenxian1_0();
                break;
            case 2:
                int i = changpager - position;
                switch (i){
                    case -2:
                        uphenxian2_1();
                        break;
                    case -1:
                        uphenxian0_1();
                        break;
                }
                break;
        }
    }

    private void onclickheadanim1(int position) {
        switch (position){
            case 0:
                uphenxian1_0();
                break;
            case 1:
                int i = changpager - position;
                switch (i){
                    case 1:
                        uphenxian2_1();
                        break;
                    case -1:
                        uphenxian0_1();
                        break;
                }
                break;
            case 2:
                uphenxian1_2();
                break;
        }
    }

    private void uphenxian0_1() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(henxian, "X", henxian_1left, henxianleft);
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void uphenxian2_1() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(henxian, "X", henxian1left, henxianleft);
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void uphenxian1_2() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(henxian, "X", henxianleft, henxian1left);
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    private void uphenxian1_0() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(henxian, "X", henxianleft, henxian_1left);
        animatorSet.play(objectAnimator);
        animatorSet.setDuration(300);
        animatorSet.start();
    }
}
