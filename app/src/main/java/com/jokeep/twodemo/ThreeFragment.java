package com.jokeep.twodemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wbq501 on 2015-12-17 16:46.
 * demo
 */
public class ThreeFragment extends Fragment implements AbsListView.OnScrollListener {
    View rootView;
    Thread thread;
    LinearLayout loadingimg,fragment_three;
    ListView threelist;
    List<String> msg = new ArrayList<String>();
    TwoAdapter adapter;
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置
    int pagerheadtop,pagerheadbuttom;
    TextView pagerhead;
    private boolean hidehead = false;
    private boolean upglide = true;
    private boolean upi = false;
    private boolean downi = false;
    DisplayMetrics dm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.three, container, false);//关联布局文件
        init();
        return rootView;
    }

    private void init() {
        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        loadingimg = (LinearLayout) rootView.findViewById(R.id.three_loading);
        fragment_three = (LinearLayout) rootView.findViewById(R.id.fragment_three);
        threelist = (ListView) rootView.findViewById(R.id.threelist);
        pagerhead = (TextView) rootView.findViewById(R.id.pagerhead);
        List<String> msg = new ArrayList<String>();
        for (int i=1;i<=100;i++){
            msg.add("测试2222222"+i);
        }
        adapter = new TwoAdapter(getContext(),msg);
        threelist.setAdapter(adapter);
        thread = new Thread(){
            @Override
            public void run() {
                try {
                    thread.sleep(2000);
                    Message msgMessage=new Message();
                    msgMessage.arg1=1;
                    handler.sendMessage(msgMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        thread.start();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                loadmsg();
            }
        }
    };
    private void loadmsg() {
        loadingimg.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
        if (hidehead){
            pagerhead.setVisibility(View.GONE);
        }else {
            pagerhead.setVisibility(View.VISIBLE);
        }
        fragment_three.setVisibility(View.VISIBLE);
        AnimatorSet anim = new AnimatorSet();
        ObjectAnimator objectanim = ObjectAnimator.ofFloat(fragment_three, "Y", dm.heightPixels, 0);
        anim.play(objectanim);
        anim.setDuration(500);
        anim.start();
//        HuanBaoActivty huanbao = (HuanBaoActivty) getActivity();
//        huanbao.upanim();
        threelist.setOnScrollListener(this);
        rigestBroadcast();
    }
    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(receiver);
        super.onDestroy();
    }
    private void rigestBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.wbq.one");
        filter.addAction("com.wbq.one.down");
        filter.addAction("com.wbq.two");
        filter.addAction("com.wbq.two.down");
        getActivity().registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.wbq.two".equals(action)||"com.wbq.one".equals(action)){
                pagerhead.setVisibility(View.GONE);
                hidehead = true;
            }else if ("com.wbq.two.down".equals(action)||"com.wbq.one.down".equals(action)){
                pagerhead.setVisibility(View.VISIBLE);
                hidehead = false;
            }
        }
    };

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                scrollFlag = false;
                // 判断滚动到顶部
                if (threelist.getFirstVisiblePosition() == 0) {
                    if (!upglide){
                        pagerhead.setVisibility(View.VISIBLE);
                        hidehead = false;
                        FrameLayoutActivity huanbao = (FrameLayoutActivity) getActivity();
                        huanbao.downanim();
                        upglide = true;
                    }
                }
                // 判断滚动到底部
                if (threelist.getLastVisiblePosition() == (threelist
                        .getCount() - 1)) {
                }
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                scrollFlag = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                scrollFlag = false;
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!scrollFlag) {
            if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
                if (upglide){
                    upglide = false;
                    pagerhead.setVisibility(View.GONE);
                    hidehead = true;
                    FrameLayoutActivity huanbao = (FrameLayoutActivity) getActivity();
                    huanbao.upanim();
                }
            } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑

            } else if (firstVisibleItem == 0){
                if (!upglide){
                    pagerhead.setVisibility(View.VISIBLE);
                    hidehead = false;
                    FrameLayoutActivity huanbao = (FrameLayoutActivity) getActivity();
                    huanbao.downanim();
                    upglide = true;
                }
            }
        }
    }
}
