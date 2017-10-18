package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.Fenlei;
import com.bwie.lianxi0927.fragment.FenleiFragment;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/7.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyListViewAdapter extends BaseAdapter{
    private Context context;
    List<Fenlei.DataBean> list;

    public MyListViewAdapter(Context context,  List<Fenlei.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.listview_item,null);
        TextView tv = view.findViewById(R.id.listview_item);
        tv.setText(list.get(i).getName());
        if(i == FenleiFragment.currPos){
            tv.setTextColor(Color.RED);
        }else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }
}
