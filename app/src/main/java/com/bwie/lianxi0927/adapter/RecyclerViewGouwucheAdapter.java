package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.GetCartsData;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class RecyclerViewGouwucheAdapter extends RecyclerView.Adapter<RecyclerViewGouwucheAdapter.ViewHolder>{

    private Context context;
    private List<GetCartsData.DataBean> list;

    public RecyclerViewGouwucheAdapter(Context context, List<GetCartsData.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gouwuche_rc_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getSellerName());
        holder.cb_shangjia.setChecked(false);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        holder.rv_gwc_shop.setLayoutManager(manager);
        MyGouwucheShopAdapter adapter = new MyGouwucheShopAdapter(context,list.get(position).getList());
        holder.rv_gwc_shop.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final CheckBox cb_shangjia;
        private final RecyclerView rv_gwc_shop;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_rv_gouwuche);
            cb_shangjia = itemView.findViewById(R.id.cb_shangjia);
            rv_gwc_shop = itemView.findViewById(R.id.rv_gwc_shop);
        }
    }
}
