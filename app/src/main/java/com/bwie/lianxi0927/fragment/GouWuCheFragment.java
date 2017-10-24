package com.bwie.lianxi0927.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.adapter.RecyclerViewGouwucheAdapter;
import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.presenter.CreateOrderPresenter;
import com.bwie.lianxi0927.presenter.GetCartsPresenter;
import com.bwie.lianxi0927.presenter.OnUpdateCartsPresenter;
import com.bwie.lianxi0927.view.CreateOrderView;
import com.bwie.lianxi0927.view.GetCartsView;
import com.bwie.lianxi0927.view.UpdateCartsView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GouWuCheFragment extends Fragment implements GetCartsView, UpdateCartsView, CreateOrderView {

    private View view;
    private Button pay;
    private TextView sumprice;
    private static CheckBox allselect;
    private TextView bianji;
    private RecyclerView rc_gouwuche;
    private RecyclerViewGouwucheAdapter adapter;
    private OnUpdateCartsPresenter presenter1;
    private int uid;
    private CreateOrderPresenter presenter2;
    private GetCartsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.gouwuche_item,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();
    }
    public static void setAllSelect(boolean b){
        allselect.setChecked(b);
    }
    private void initData() {
        presenter2 = new CreateOrderPresenter(getActivity(), this);
        presenter = new GetCartsPresenter(getContext(),this);
        uid = getActivity().getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
        presenter.requestCarts(uid);
        presenter1 = new OnUpdateCartsPresenter(getContext(), this);


    }
    private void initView() {
        pay = view.findViewById(R.id.pay);
        sumprice = view.findViewById(R.id.sumprice);
        allselect = view.findViewById(R.id.allselect);
        bianji = view.findViewById(R.id.bianji);
        rc_gouwuche = view.findViewById(R.id.rc_gouwuche);
    }

    @Override
    public void getCartsDataSussess(final List<GetCartsData.DataBean> data1) {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                private int selected;
                private List<GetCartsData.DataBean.ListBean> list = new ArrayList<GetCartsData.DataBean.ListBean>();
                @Override
                public void run() {
                    System.out.println("data1 ========== " + data1.size());
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    rc_gouwuche.setLayoutManager(manager);
                    adapter = new RecyclerViewGouwucheAdapter(getActivity(),data1);
                    rc_gouwuche.setAdapter(adapter);
                    pay.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            double sum = 0;
                            for (int i = 0; i < data1.size(); i++) {
                                for (int i1 = 0; i1 < data1.get(i).getList().size(); i1++) {
                                    GetCartsData.DataBean.ListBean listBean = data1.get(i).getList().get(i1);
                                    presenter1.requestUpdateCarts(getActivity().getSharedPreferences("con",Context.MODE_PRIVATE).getInt("uid",170),listBean.getSellerid()+"",listBean.getPid(),1,listBean.getNum());
                                    if(listBean.getSelected()==1){
                                        sum +=listBean.getBargainPrice()*listBean.getNum();
                                        System.out.println("sum = " + sum);
                                    }
                                }
                            }
                            presenter2.requestCreateOrder(uid,sum);
                            sumprice.setText("总价"+sum+"");


                        }
                    });
                    allselect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (allselect.isChecked()){
                                List<Boolean> allSelect = adapter.getAllSelect();
                                System.out.println("allSelect.size() = " + allSelect.size());
                                    Toast.makeText(getContext(), allSelect.size()+"", Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < allSelect.size(); i++) {
                                        allSelect.set(i,true);
                                        System.out.println("allSelect.get(i).booleanValue()+\"\" = " + allSelect.get(i).booleanValue()+"");
                                    }
                                    rc_gouwuche.setAdapter(adapter);
                                for (int i = 0; i < data1.size(); i++) {

                                    for (int i1 = 0; i1 < data1.get(i).getList().size(); i1++) {

                                        presenter1.requestUpdateCarts(uid,data1.get(i).getSellerid()+"",data1.get(i).getList().get(i1).getPid(),1,data1.get(i).getList().get(i1).getNum());
                                    }
                                }
                                }else {
                                List<Boolean> allSelect = adapter.getAllSelect();
                                System.out.println("allSelect.size() = " + allSelect.size());
                                Toast.makeText(getContext(), allSelect.size()+"", Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < allSelect.size(); i++) {
                                    allSelect.set(i,false);
                                    System.out.println("allSelect.get(i).booleanValue()+\"\" = " + allSelect.get(i).booleanValue()+"");
                                }
                                rc_gouwuche.setAdapter(adapter);
                                for (int i = 0; i < data1.size(); i++) {

                                    for (int i1 = 0; i1 < data1.get(i).getList().size(); i1++) {
                                        presenter1.requestUpdateCarts(uid,data1.get(i).getSellerid()+"",data1.get(i).getList().get(i1).getPid(),0,data1.get(i).getList().get(i1).getNum());
                                    }
                                }
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    public void getCartsDataFailure(String msg) {

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

    @Override
    public void onCreateOrderSuccess(final String msg) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOrderFailure(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFailure() {

    }
}
