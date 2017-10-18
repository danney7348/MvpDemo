package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.Products;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/16.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyProductRecycleView1 extends RecyclerView.Adapter<MyProductRecycleView1.ViewHolder>{

    private Context context;
    private List<Products.DataBean> data;
    private View view;

    public MyProductRecycleView1(Context context, List<Products.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.product_item2,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.price.setText(data.get(position).getPrice()+"");
        holder.xiao.setText(data.get(position).getSalenum()+"");
        Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holder.iv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView title;
        private final TextView price;
        private final TextView xiao;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_pro_img2);
            title = itemView.findViewById(R.id.tv_pro_title2);
            price = itemView.findViewById(R.id.tv_pro_price2);
            xiao = itemView.findViewById(R.id.tv_xiaol2);
        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(View v,int position);
    }
}
