package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.Miaosha;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/8.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyMiaoshaoAdapter extends BaseAdapter{
    private Context context;
    private List<Miaosha> list;

    public MyMiaoshaoAdapter(Context context, List<Miaosha> list) {
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
        view = View.inflate(context, R.layout.miao_gv_item,null);
        ImageView iv = view.findViewById(R.id.iv_miao);
        TextView tv = view.findViewById(R.id.tv_miao);
        Glide.with(context).load(list.get(i).getImg()).into(iv);
        tv.setText(list.get(i).getName());
        return view;
    }
}
