package com.bwie.lianxi0927.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.fragment.GouWuCheFragment;
import com.bwie.lianxi0927.presenter.CreateOrderPresenter;
import com.bwie.lianxi0927.presenter.DeleteCartPresenter;
import com.bwie.lianxi0927.presenter.OnUpdateCartsPresenter;
import com.bwie.lianxi0927.view.CreateOrderView;
import com.bwie.lianxi0927.view.DeleteCartView;
import com.bwie.lianxi0927.view.UpdateCartsView;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class RecyclerViewGouwucheAdapter extends RecyclerView.Adapter<RecyclerViewGouwucheAdapter.ViewHolder> implements UpdateCartsView, DeleteCartView, CreateOrderView {

    private Context context;
    private List<GetCartsData.DataBean> list;
    private List<Boolean> listSelect;
    private int uid;

    public RecyclerViewGouwucheAdapter(Context context, List<GetCartsData.DataBean> list) {
        this.context = context;
        this.list = list;
        listSelect = new ArrayList<>();;
        System.out.println("list = RecyclerViewGouwucheAdapter" + list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println("list = " + list.size());
                    listSelect.add(i,false);
                    System.out.println("listSelect = " + listSelect.size());
                    System.out.println("listSelect = " + listSelect.get(i).booleanValue());
                }
    }

    public  List<Boolean> getAllSelect(){
        return listSelect;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gouwuche_rc_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cb_shangjia.setChecked(listSelect.get(position));
        holder.title.setText(list.get(position).getSellerName());
        holder.shangjiaSelect = false;
        System.out.println("position = " + position);
        final OnUpdateCartsPresenter presenter = new OnUpdateCartsPresenter(context,this);
        uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        holder.rv_gwc_shop.setLayoutManager(manager);
        final MyGouwucheShopAdapter adapter = new MyGouwucheShopAdapter(context,list.get(position).getList(),holder.cb_shangjia);
        holder.rv_gwc_shop.setAdapter(adapter);

        bianli();
        if(holder.cb_shangjia.isChecked()){
            for (int i = 0; i < list.get(position).getList().size(); i++) {
                list.get(position).getList().get(i).setSelected(1);
                update(context.getSharedPreferences("con",Context.MODE_PRIVATE).getInt("uid",170),list.get(position).getList().get(i).getSellerid()+"",list.get(position).getList().get(i).getPid(),1,list.get(position).getList().get(i).getNum(),list.get(position).getList().get(i).getBargainPrice());
            }
            holder.rv_gwc_shop.setAdapter(adapter);
        }else {
            for (int i = 0; i < list.get(position).getList().size(); i++) {
                list.get(position).getList().get(i).setSelected(0);
                update(context.getSharedPreferences("con",Context.MODE_PRIVATE).getInt("uid",170),list.get(position).getList().get(i).getSellerid()+"",list.get(position).getList().get(i).getPid(),0,list.get(position).getList().get(i).getNum(),list.get(position).getList().get(i).getBargainPrice());
            }
            holder.rv_gwc_shop.setAdapter(adapter);
        }
        adapter.setUpDateAdapter(new MyGouwucheShopAdapter.UpDateAdapter() {
            @Override
            public void onUpdateAdapterTrue() {
                holder.shangjiaSelect = true;
                listSelect.set(position,true);
                if(holder.shangjiaSelect ==true){
                    holder.cb_shangjia.setChecked(true);
                }else {
                    holder.cb_shangjia.setChecked(false);
                }
                holder.rv_gwc_shop.setAdapter(adapter);
            }
            @Override
            public void onUpdateAdapterFalse() {
                holder.shangjiaSelect = false;
                listSelect.set(position,false);
                bianli();
                if(holder.shangjiaSelect ==true){
                    holder.cb_shangjia.setChecked(true);
                }else {
                    holder.cb_shangjia.setChecked(false);
                }
                holder.rv_gwc_shop.setAdapter(adapter);
            }

        });
        holder.cb_shangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.cb_shangjia.isChecked()){
                    for (int i = 0; i <list.get(position).getList().size() ; i++) {
                        list.get(position).getList().get(i).setSelected(1);
                    }
                    holder.rv_gwc_shop.setAdapter(adapter);
                    for (int i = 0; i < list.get(position).getList().size(); i++) {
                        update(uid,list.get(position).getSellerid()+"",list.get(position).getList().get(i).getPid(),1,list.get(position).getList().get(i).getNum(),list.get(position).getList().get(i).getBargainPrice());
                    }
                    listSelect.set(position,true);
                    bianli();
                }else {
                    for (int i = 0; i <list.get(position).getList().size() ; i++) {
                        list.get(position).getList().get(i).setSelected(0);
                    }
                    holder.rv_gwc_shop.setAdapter(adapter);
                    for (int i = 0; i < list.get(position).getList().size(); i++) {
                        update(uid,list.get(position).getSellerid()+"",list.get(position).getList().get(i).getPid(),0,list.get(position).getList().get(i).getNum(),list.get(position).getList().get(i).getBargainPrice());
                    }
                    listSelect.set(position,false);
                    bianli();
                }
            }
        });
        if(holder.cb_shangjia.isChecked()){
            bianli();
            for (int i = 0; i <list.get(position).getList().size() ; i++) {
                list.get(position).getList().get(i).setSelected(1);
            }
            holder.rv_gwc_shop.setAdapter(adapter);
            bianli();
        }else {
            bianli();
            for (int i = 0; i <list.get(position).getList().size() ; i++) {
                list.get(position).getList().get(i).setSelected(0);
            }
            holder.rv_gwc_shop.setAdapter(adapter);
            bianli();
        }
        final DeleteCartPresenter presenter1 = new DeleteCartPresenter(context, this);
        adapter.setOnItemClickListener(new MyGouwucheShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {
                Toast.makeText(context, "ssssssssss", Toast.LENGTH_SHORT).show();
                int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                presenter1.requestDeleteCart(uid,list.get(position).getList().get(pos).getPid());
                adapter.notifyDataSetChanged();
                holder.rv_gwc_shop.setAdapter(adapter);
            }
        });
        adapter.setOnItemClickListener(new MyGouwucheShopAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(final int pos, View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setMessage("是否删除该商品？").setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "ssssssssss", Toast.LENGTH_SHORT).show();
                        int uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
                        list.get(position).getList().remove(pos);
                        if(list.get(position).getList().size()==0){
                            list.remove(position);
                        }
                        notifyDataSetChanged();
                        holder.rv_gwc_shop.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("留下",null);
                ad.show();
                presenter1.requestDeleteCart(uid,list.get(position).getList().get(pos).getPid());
            }
        });
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

    private void update(int uid,String sellerid,int pid,int selected,int num,double price){
        OnUpdateCartsPresenter presenter = new OnUpdateCartsPresenter(context,this);
        uid = context.getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
        presenter.requestUpdateCarts(uid,sellerid,pid,selected,num);
        int sum = 0;
        if(selected==1){
            sum+=price*num;
        }
        CreateOrderPresenter presenter1 = new CreateOrderPresenter(context,this);
        presenter1.requestCreateOrder(uid,sum);
    }
    @Override
    public void onDeleteCartDataSuccess(String msg) {

    }

    @Override
    public void onDeleteCartDataFilure(String msg) {

    }

    @Override
    public void failure() {

    }

    @Override
    public void onCreateOrderSuccess(String msg) {

    }

    @Override
    public void onCreateOrderFailure(String msg) {

    }

    @Override
    public void onFailure() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final RecyclerView rv_gwc_shop;
        private final CheckBox cb_shangjia;
        private boolean shangjiaSelect;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_rv_gouwuche);
            cb_shangjia = itemView.findViewById(R.id.cb_shangjia);
            rv_gwc_shop = itemView.findViewById(R.id.rv_gwc_shop);
        }
    }
    private void bianli() {
        List<Boolean> getlistflag = getAllSelect();
        int z=0;
        for (int i = 0; i <getlistflag.size() ; i++) {
            if(getlistflag.get(i)==true){
                z++;
            }else {
                z--;
            }
        }
        System.out.println("z = " + z);
        if(z==getlistflag.size()){
            GouWuCheFragment.setAllSelect(true);
        }else {
            GouWuCheFragment.setAllSelect(false);;
        }
    }
}
