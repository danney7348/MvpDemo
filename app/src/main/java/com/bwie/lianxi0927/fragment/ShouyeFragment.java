package com.bwie.lianxi0927.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.CustomScanActivity;
import com.bwie.lianxi0927.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * 作者： 张少丹
 * 时间：  2017/9/28.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ShouyeFragment extends Fragment {

    private View view;
    private ImageView sao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.shouye_item, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity())
                        .setOrientationLocked(false)
                        .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                        .initiateScan(); // 初始化扫描
            }
        });
    }

    private void initView() {
        sao = view.findViewById(R.id.iv_saoasao);
    }

}
