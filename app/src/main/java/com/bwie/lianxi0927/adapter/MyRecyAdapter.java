package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.ViewHolder>{

    private Context context;
    private List<ProductCatagory.DataBean> data;

    public MyRecyAdapter(Context context, List<ProductCatagory.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recybig_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getName());
        SmallRecyAdapter adapter = new SmallRecyAdapter(context,data.get(position).getList());
        GridLayoutManager am = new GridLayoutManager(context,3);
        holder.rv.setLayoutManager(am);
        holder.rv.setAdapter(adapter);
       /* adapter.setOnItemClickListener(new SmallRecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageView view, int postion) {
                Intent intent = new Intent(context, SouActivity.class);
                intent.putExtra("pid",data.get(position).getList().get(position).getPscid());
                context.startActivity(intent);
            }
        });*/
       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv.setLayoutManager(linearLayoutManager);*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.recybig_tv);
            rv = itemView.findViewById(R.id.recybig_rv);
        }
    }
}
