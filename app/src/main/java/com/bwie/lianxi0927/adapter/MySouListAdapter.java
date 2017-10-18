package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.Sousuo;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */
public class MySouListAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Sousuo.DataBean> data;
    private static final int atype = 0;
    private static final int btype = 1;

    public MySouListAdapter(Context context, List<Sousuo.DataBean> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case atype:
                view = View.inflate(context,R.layout.sou_item,null);
                holder = new ViewHolderOne(view);
                break;
            case btype:
                view = View.inflate(context,R.layout.sou_item2,null);
                holder = new ViewHoldeTwo(view);
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return atype;
        }else {
            return btype;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case 0:
                final ViewHolderOne holderOne = (ViewHolderOne) holder;
                holderOne.title1.setText(data.get(position).getTitle());
                Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holderOne.img1);
                break;
            case 1:
                final ViewHoldeTwo holderTwo = (ViewHoldeTwo) holder;
                holderTwo.title2.setText(data.get(position).getTitle());
                Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holderTwo.img2);
                break;
        }
    }
    public class ViewHolderOne extends RecyclerView.ViewHolder{

        private final ImageView img1;
        private final TextView title1;

        public ViewHolderOne(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.sou_iv_img1);
            title1 = itemView.findViewById(R.id.sou_tv_title1);
        }
    }
    public class ViewHoldeTwo extends RecyclerView.ViewHolder{

        private final ImageView img2;
        private final TextView title2;

        public ViewHoldeTwo(View itemView) {
            super(itemView);
            img2 = itemView.findViewById(R.id.sou_iv_img2);
            title2 = itemView.findViewById(R.id.sou_tv_title2);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
