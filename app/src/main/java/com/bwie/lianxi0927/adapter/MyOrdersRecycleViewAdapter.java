package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.lianxi0927.zhifubao.PayDemoActivity;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.GetOrders;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/22.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyOrdersRecycleViewAdapter extends RecyclerView.Adapter<MyOrdersRecycleViewAdapter.ViewHolder>{
    private Context context;
    private List<GetOrders.DataBean> data1;

    public MyOrdersRecycleViewAdapter(Context context, List<GetOrders.DataBean> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.order_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.createTime.setText(data1.get(position).getCreatetime());
        holder.orderid.setText(data1.get(position).getOrderid()+"");
        holder.price.setText(data1.get(position).getPrice()+"");
        holder.status.setText(data1.get(position).getStatus()+"");
        holder.uid.setText(data1.get(position).getUid()+"");

        if(holder.status.equals("1")){
            holder.pay.setText("已支付");
        }
        if(holder.status.equals("2")){
            holder.pay.setText("订单已取消");
        }
        if (holder.status.equals("0")){
            holder.pay.setText("待支付");
        }
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PayDemoActivity.class);
                intent.putExtra("orderid",data1.get(position).getOrderid()+"");
                context.startActivity(intent);
                holder.pay.setText("已支付");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView orderid;
        private final TextView price;
        private final TextView status;
        private final TextView uid;
        private final TextView createTime;
        private final Button pay;

        public ViewHolder(View itemView) {
            super(itemView);
            createTime = itemView.findViewById(R.id.order_tv_createtime);
            orderid = itemView.findViewById(R.id.order_tv_orderid);
            price = itemView.findViewById(R.id.order_tv_price);
            status = itemView.findViewById(R.id.order_tv_status);
            uid = itemView.findViewById(R.id.order_tv_uid);
            pay = itemView.findViewById(R.id.btn_pay);
        }
    }
}
