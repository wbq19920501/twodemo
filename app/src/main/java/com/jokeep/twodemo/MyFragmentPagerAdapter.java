package com.jokeep.twodemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wbq501 on 2015-12-17 17:31.
 * demo
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    /**
     * 判断出去的view是否等于进来的view 如果为true直接复用
     */
//    @Override
//    public boolean isViewFromObject(View arg0, Object arg1) {
//        return arg0 == arg1;
//    }

    /**
     * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
     */
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(list.get(position));
//    }
    /**
     * 创建一个view
     */
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(list.get(position));
//        return list.get(position);
//    }
}
