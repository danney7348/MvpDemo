package com.bwie.lianxi0927.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.presenter.ProductPresenter;
import com.bwie.lianxi0927.presenter.ProductXiangqingPresenter;
import com.bwie.lianxi0927.view.ProductXiangqingView;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class XiangqingFragment extends android.support.v4.app.Fragment implements ProductXiangqingView {

    private View view;
    private WebView wv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.xiangqing_fragment_item,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wv = view.findViewById(R.id.wv);
        Intent intent = getActivity().getIntent();
        int pid = intent.getIntExtra("pid", 0);
        System.out.println("piafeasrfasefaergfaszfased = " + pid);
        wv.getSettings().setJavaScriptEnabled(true);
        ProductXiangqingPresenter productXiangqingPresenter = new ProductXiangqingPresenter(this,getActivity());
        productXiangqingPresenter.requestProductXiangqing(pid);

    }

    @Override
    public void onProductXiangqingSuccess(final PruductXiangqing.DataBean data1) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("data1 = " + data1);
                wv.loadUrl(data1.getDetailUrl());
            }
        });
    }

    @Override
    public void onProductXiangqingFailure(String msg) {

    }

    @Override
    public void Failure() {

    }
}
