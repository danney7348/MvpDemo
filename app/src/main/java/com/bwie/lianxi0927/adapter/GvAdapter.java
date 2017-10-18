package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.MiddleBean;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/9/29.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GvAdapter extends BaseAdapter{
    private Context context;
    private List<MiddleBean> list;

    public GvAdapter(Context context, List<MiddleBean> list) {
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
        view = View.inflate(context, R.layout.gv_item,null);
        ImageView gv = view.findViewById(R.id.iv_gv);
        TextView tv_gv = view.findViewById(R.id.tv_gv);
        Glide.with(context).load(list.get(i).getImg()).into(gv);
        tv_gv.setText(list.get(i).getName());
        return view;
    }
}
