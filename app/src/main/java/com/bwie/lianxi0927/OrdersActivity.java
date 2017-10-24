package com.bwie.lianxi0927;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.adapter.MyOrdersRecycleViewAdapter;
import com.bwie.lianxi0927.bean.GetAddrs;
import com.bwie.lianxi0927.bean.GetOrders;
import com.bwie.lianxi0927.presenter.GetAddrsPresenter;
import com.bwie.lianxi0927.presenter.GetOrdersPresenter;
import com.bwie.lianxi0927.view.GetAddrsView;
import com.bwie.lianxi0927.view.GetOrdersView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class OrdersActivity extends AppCompatActivity implements GetOrdersView {

    private XRecyclerView rv;
    private MyOrdersRecycleViewAdapter adapter;
    private GetOrdersPresenter presenter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        initView();
        initData();
    }

    private void initData() {
        presenter = new GetOrdersPresenter(this,this);
        presenter.requestGetOrders(getSharedPreferences("con",MODE_PRIVATE).getInt("uid",170),1);
    }

    private void initView() {
        rv = (XRecyclerView) findViewById(R.id.order_rv);
    }

    @Override
    public void onGetOrdersSuccess(final List<GetOrders.DataBean> data1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LinearLayoutManager manager = new LinearLayoutManager(OrdersActivity.this);
                rv.setLayoutManager(manager);
                if(adapter == null){
                    adapter = new MyOrdersRecycleViewAdapter(OrdersActivity.this,data1);
                    rv.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
                rv.setRefreshProgressStyle(13);//刷新样式
                rv.setLaodingMoreProgressStyle(13);//加载样式
                rv.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        Toast.makeText(OrdersActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                        presenter.requestGetOrders(getSharedPreferences("con",MODE_PRIVATE).getInt("uid",170),1);
                        System.out.println("page = " + page);
                        rv.refreshComplete();
                    }
                    @Override
                    public void onLoadMore() {
                        Toast.makeText(OrdersActivity.this, "下拉加载", Toast.LENGTH_SHORT).show();
                        page++;
                        presenter.requestGetOrders(getSharedPreferences("con",MODE_PRIVATE).getInt("uid",170),page);
                        System.out.println("page = " + page);
                        rv.loadMoreComplete();
                    }
                });
            }
        });
    }

    @Override
    public void onGetOrdersFailure(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailre() {

    }
    public class ProgressStyle {
        public static final int SysProgress=-1;
        public static final int BallPulse=0;
        public static final int BallGridPulse=1;
        public static final int BallClipRotate=2;
        public static final int BallClipRotatePulse=3;
        public static final int SquareSpin=4;
        public static final int BallClipRotateMultiple=5;
        public static final int BallPulseRise=6;
        public static final int BallRotate=7;
        public static final int CubeTransition=8;
        public static final int BallZigZag=9;
        public static final int BallZigZagDeflect=10;
        public static final int BallTrianglePath=11;
        public static final int BallScale=12;
        public static final int LineScale=13;
        public static final int LineScaleParty=14;
        public static final int BallScaleMultiple=15;
        public static final int BallPulseSync=16;
        public static final int BallBeat=17;
        public static final int LineScalePulseOut=18;
        public static final int LineScalePulseOutRapid=19;
        public static final int BallScaleRipple=20;
        public static final int BallScaleRippleMultiple=21;
        public static final int BallSpinFadeLoader=22;
        public static final int LineSpinFadeLoader=23;
        public static final int TriangleSkewSpin=24;
        public static final int Pacman=25;
        public static final int BallGridBeat=26;
        public static final int SemiCircleSpin=27;
    }
}
