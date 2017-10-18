package com.bwie.lianxi0927;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.lianxi0927.adapter.MyAdapterT;
import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.fragment.GouWuCheFragment;
import com.bwie.lianxi0927.fragment.PinjiaFragment;
import com.bwie.lianxi0927.fragment.ShopFragment;
import com.bwie.lianxi0927.fragment.XiangqingFragment;
import com.bwie.lianxi0927.presenter.AddCarPresenter;
import com.bwie.lianxi0927.presenter.ProductXiangqingPresenter;
import com.bwie.lianxi0927.view.AddCarView;
import com.bwie.lianxi0927.view.ProductXiangqingView;

import java.util.ArrayList;
import java.util.List;

public class ProdutctsXiangqingActivity extends AppCompatActivity implements ProductXiangqingView, AddCarView {

    private ViewPager vp;
    private TabLayout tl;
    private Button joinshapcar;
    private LinearLayout shapcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtcts_xiangqing);
        initView();
        initData();
    }
    private void initData() {
        final Intent intent = getIntent();
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
        joinshapcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int anInt = getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 170);
                int pid1 = intent.getIntExtra("pid", 0);
                System.out.println("anInt =++++++++++++++++++++ " + anInt+"======="+pid1);
                AddCarPresenter presenter1 = new AddCarPresenter(ProdutctsXiangqingActivity.this,ProdutctsXiangqingActivity.this);
                presenter1.requestAddCar(anInt,pid1);
               // Toast.makeText(ProdutctsXiangqingActivity.this, getSharedPreferences("con",MODE_PRIVATE).getInt("uid",170)+"========="+intent.getIntExtra("pid", 0)+"", Toast.LENGTH_SHORT).show();
            }
        });
        shapcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProdutctsXiangqingActivity.this, GouWuCheFragment.class));
            }
        });
    }
    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
        joinshapcar = (Button) findViewById(R.id.joinshapcar);
        shapcar = (LinearLayout) findViewById(R.id.shapcar);
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

    @Override
    public void onAddCarDataSuccess(String msg) {
        /*AlertDialog.Builder ad = new AlertDialog.Builder(this)
                .setTitle(msg);
        ad.show();*/
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddCarDataFilure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure() {

    }
}
