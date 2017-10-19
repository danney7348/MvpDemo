package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.AmountView;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.presenter.OnUpdateCartsPresenter;
import com.bwie.lianxi0927.view.UpdateCartsView;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyGouwucheShopAdapter extends RecyclerView.Adapter<MyGouwucheShopAdapter.ViewHolder> implements UpdateCartsView {
    private Context context;
    private List<GetCartsData.DataBean.ListBean> list;

    public MyGouwucheShopAdapter(Context context, List<GetCartsData.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rv_gwc_shop_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OnUpdateCartsPresenter presenter = new OnUpdateCartsPresenter(context,this);
        //holder.cb_shop.setChecked(false);
        holder.tv_shop_price.setText("￥"+list.get(position).getBargainPrice()+"");
        holder.tv_shop_subhead.setText(list.get(position).getSubhead());
        holder.tv_shop_title.setText(list.get(position).getTitle());
        System.out.println("holder = " + list.get(position).getNum()+"");
        holder.jiajianqi.setGoods_storage(50);
        holder.jiajianqi.setnum(list.get(position).getNum());
        if (list.get(position).getSelected()==0){
            holder.cb_shop.setChecked(true);
            System.out.println("list = " + list.get(position).getSelected());
        }else if(list.get(position).getSelected()==1){
            holder.cb_shop.setChecked(false);
            System.out.println("list = " + list.get(position).getSelected());
        }
        holder.cb_shop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private int selected;

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    selected = 0;
                    System.out.println("list = " + list.get(position).getSelected());
                }else {
                    selected = 1;
                    System.out.println("list = " + list.get(position).getSelected());
                }
                int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                presenter.requestUpdateCarts(uid,list.get(position).getSellerid(),list.get(position).getPid(),selected,list.get(position).getNum());

            }
        });
        holder.jiajianqi.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                presenter.requestUpdateCarts(uid,list.get(position).getSellerid(),list.get(position).getPid(),list.get(position).getSelected(),amount);
            }
        });
        Glide.with(context).load(list.get(position).getImages().split("\\|")[0]).into(holder.iv_gwc_shop_img);

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

        private final CheckBox cb_shop;
        private final ImageView iv_gwc_shop_img;
        private final TextView tv_shop_price;
        private final TextView tv_shop_subhead;
        private final TextView tv_shop_title;
        private final AmountView jiajianqi;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_shop = itemView.findViewById(R.id.cb_shop);
            iv_gwc_shop_img = itemView.findViewById(R.id.iv_gwc_shop_img);
            tv_shop_price = itemView.findViewById(R.id.tv_shop_price);
            tv_shop_subhead = itemView.findViewById(R.id.tv_shop_subhead);
            tv_shop_title = itemView.findViewById(R.id.tv_shop_title);
            jiajianqi = itemView.findViewById(R.id.jiajianqi);
        }
    }
}
