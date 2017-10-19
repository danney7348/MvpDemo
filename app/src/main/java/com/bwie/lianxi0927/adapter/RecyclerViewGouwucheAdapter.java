package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.presenter.OnUpdateCartsPresenter;
import com.bwie.lianxi0927.view.UpdateCartsView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class RecyclerViewGouwucheAdapter extends RecyclerView.Adapter<RecyclerViewGouwucheAdapter.ViewHolder> implements UpdateCartsView {

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getSellerName());
        final OnUpdateCartsPresenter presenter = new OnUpdateCartsPresenter(context,this);
        /*holder.cb_shangjia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                    int selected = list.get(position).getList().get(position).getSelected();
                    System.out.println("selected = " + selected);
                    selected=0;
                    if(selected==0){
                        holder.cb_shangjia.setChecked(true);
                    }
                    System.out.println("selected = " + selected);
                    int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                    presenter.requestUpdateCarts(uid,list.get(position).getList().get(position).getSellerid(),list.get(position).getList().get(position).getPid(),selected,list.get(position).getList().get(position).getNum());
                }else {
                    holder.cb_shangjia.setChecked(false);
                    int selected = list.get(position).getList().get(position).getSelected();
                    System.out.println("selected = " + selected);
                    selected=1;
                    System.out.println("selected = " + selected);
                    int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                    presenter.requestUpdateCarts(uid,list.get(position).getList().get(position).getSellerid(),list.get(position).getList().get(position).getPid(),selected,list.get(position).getList().get(position).getNum());

                }
            }
        });*/
        LinearLayoutManager manager = new LinearLayoutManager(context);
        holder.rv_gwc_shop.setLayoutManager(manager);
        MyGouwucheShopAdapter adapter = new MyGouwucheShopAdapter(context,list.get(position).getList());
        holder.rv_gwc_shop.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onUpdateCartsDataSuccess(String msg) {

    }

    @Override
    public void onUpdateCartsDataFilure(String msg) {

    }

    @Override
    public void failure() {

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
