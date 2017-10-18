package com.bwie.lianxi0927.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.SouActivity;
import com.bwie.lianxi0927.bean.ProductCatagory;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/11.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class SmallRecyAdapter extends RecyclerView.Adapter<SmallRecyAdapter.ViewHolder>{

    private Context context;
    private List<ProductCatagory.DataBean.ListBean> data;
    public OnItemClickListener onItemClickListener;
    private View view;

    public SmallRecyAdapter(Context context, List<ProductCatagory.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(context, R.layout.smallrecy_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context).load(data.get(position).getIcon()).into(holder.iv);
        holder.tv.setText(data.get(position).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SouActivity.class);
                intent.putExtra("pid",data.get(position).getPscid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.smallrecy_tv);
            iv = itemView.findViewById(R.id.smallrecy_iv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(ImageView view,int postion);
    }
}
