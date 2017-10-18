package com.bwie.lianxi0927;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bwie.lianxi0927.adapter.MyAdapterT;
import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.fragment.PinjiaFragment;
import com.bwie.lianxi0927.fragment.ShopFragment;
import com.bwie.lianxi0927.fragment.XiangqingFragment;
import com.bwie.lianxi0927.presenter.ProductXiangqingPresenter;
import com.bwie.lianxi0927.view.ProductXiangqingView;

import java.util.ArrayList;
import java.util.List;

public class ProdutctsXiangqingActivity extends AppCompatActivity implements ProductXiangqingView {

    private ViewPager vp;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtcts_xiangqing);
        initView();
        initData();
    }
    private void initData() {
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        ProductXiangqingPresenter presenter = new ProductXiangqingPresenter(this,this);
        presenter.requestProductXiangqing(pid);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new XiangqingFragment());
        fragments.add(new PinjiaFragment());
//将tablayout和viewpager绑定
        MyAdapterT adapter = new MyAdapterT(getSupportFragmentManager(), this, fragments);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
//给viewpager设置适配器

//自定义适配器类
    }
    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
    }

    @Override
    public void onProductXiangqingSuccess(final PruductXiangqing.DataBean data1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

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
