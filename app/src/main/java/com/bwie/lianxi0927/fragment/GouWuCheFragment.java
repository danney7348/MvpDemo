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

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.adapter.RecyclerViewGouwucheAdapter;
import com.bwie.lianxi0927.bean.GetCartsData;
import com.bwie.lianxi0927.presenter.GetCartsPresenter;
import com.bwie.lianxi0927.view.GetCartsView;

import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/18.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class GouWuCheFragment extends Fragment implements GetCartsView {

    private View view;
    private Button pay;
    private TextView sumprice;
    private CheckBox allselect;
    private TextView bianji;
    private RecyclerView rc_gouwuche;

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
    private void initData() {
        GetCartsPresenter presenter = new GetCartsPresenter(getContext(),this);
        int uid = getActivity().getSharedPreferences("con", Context.MODE_PRIVATE).getInt("uid", 170);
        presenter.requestCarts(uid);
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

                private List<GetCartsData.DataBean.ListBean> list;

                @Override
                public void run() {
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    rc_gouwuche.setLayoutManager(manager);
                    RecyclerViewGouwucheAdapter adapter = new RecyclerViewGouwucheAdapter(getActivity(),data1);
                    rc_gouwuche.setAdapter(adapter);
                }
            });
        }

    }

    @Override
    public void getCartsDataFailure(String msg) {

    }

    @Override
    public void failure() {

    }
}
