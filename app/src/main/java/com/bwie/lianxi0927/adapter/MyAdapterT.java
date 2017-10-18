package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyAdapterT extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> list;
    private String[] title={"商品","详情","评价"};
    public MyAdapterT(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    public MyAdapterT(FragmentManager fm) {
        super(fm);
    }




    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    //重写方法实现tablayout和viewpager联动
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
