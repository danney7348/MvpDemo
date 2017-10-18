package com.bwie.lianxi0927;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.adapter.MyProductRecycleView;
import com.bwie.lianxi0927.adapter.MyProductRecycleView1;
import com.bwie.lianxi0927.adapter.MySouListAdapter;
import com.bwie.lianxi0927.bean.ProductData;
import com.bwie.lianxi0927.bean.Products;
import com.bwie.lianxi0927.bean.Sousuo;
import com.bwie.lianxi0927.common.Api;
import com.bwie.lianxi0927.fragment.XiangqingFragment;
import com.bwie.lianxi0927.presenter.ProductPresenter;
import com.bwie.lianxi0927.presenter.ProductXiangqingPresenter;
import com.bwie.lianxi0927.utils.HttpUtils;
import com.bwie.lianxi0927.view.ProductView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;

public class SouActivity extends AppCompatActivity implements ProductView {

    private EditText et_sousuo;
    private TextView tv_sousuo;
    private RecyclerView rv;
    private ImageView huan;
    private SharedPreferences con;
    private TextView paixu;
    private MyProductRecycleView adapter;
    private MyProductRecycleView1 adapter1;
    private TextView xiaoliang;
    private ProductPresenter presenter;
    private int i;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou);
        initView();
        initData();
    }

    private void initData() {
        final String s = et_sousuo.getText().toString();
        System.out.println("s sssssssssssssssssssssssssssss= " + s);
        tv_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Api.SOUSUO_API+et_sousuo.getText().toString() = " + Api.SOUSUO_API+s);
                HttpUtils utils = new HttpUtils();
                utils.getJson(Api.SOUSUO_API+et_sousuo.getText().toString(), new HttpUtils.HttpCallBack() {
                    @Override
                    public void onSusscess(String data) {
                        Gson gson = new Gson();
                        Sousuo sousuo = gson.fromJson(data, Sousuo.class);
                        final List<Sousuo.DataBean> data1 = sousuo.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LinearLayoutManager manager = new LinearLayoutManager(SouActivity.this);
                                rv.setLayoutManager(manager);
                                MySouListAdapter adapter = new MySouListAdapter(SouActivity.this,data1);
                                rv.setAdapter(adapter);
                            }
                        });
                    }
                });
            }
        });
        presenter = new ProductPresenter(this,this);
        Intent intent = getIntent();
        i = intent.getIntExtra("pid", 0);
        presenter.requestProduct(i,0);
    }

    private void initView() {
        et_sousuo = (EditText) findViewById(R.id.et_sssssss);
        tv_sousuo = (TextView) findViewById(R.id.tv_sousuo1);
        rv = (RecyclerView) findViewById(R.id.sou_rv);
        huan = (ImageView) findViewById(R.id.iv_huan);
        paixu = (TextView) findViewById(R.id.tv_paixu);
        xiaoliang = (TextView) findViewById(R.id.tv_xiaoliang);
    }

    @Override
    public void getProductSuccess(final List<Products.DataBean> data1) {

        con = getSharedPreferences("con", MODE_PRIVATE);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < data1.size(); i++) {
                    int sellerid = data1.get(i).getSellerid();
                }
                System.out.println("data1sssssssssssssdddddddddddddddddd = " + data1);
                LinearLayoutManager manager = new LinearLayoutManager(SouActivity.this);
                rv.setLayoutManager(manager);
                adapter = new MyProductRecycleView(SouActivity.this,data1);
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(View v, int position) {
                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                        intent.putExtra("pid",data1.get(position).getPid());
                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                        startActivity(intent);
                    }
                });
                rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        //当前RecyclerView显示出来的最后一个的item的position
                        int lastPosition = -1;
                        //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                        if(newState == RecyclerView.SCROLL_STATE_IDLE){
                            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                            if(layoutManager instanceof GridLayoutManager){
                                //通过LayoutManager找到当前显示的最后的item的position
                                lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                            }else if(layoutManager instanceof LinearLayoutManager){
                                lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                            }else if(layoutManager instanceof StaggeredGridLayoutManager){
                                //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                                //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                                int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                                lastPosition = findMax(lastPositions);
                            }
                            //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                            //如果相等则说明已经滑动到最后了
                            if(lastPosition == recyclerView.getLayoutManager().getItemCount()-1){
                                System.out.println("i = " + i);
                                page++;
                                Toast.makeText(SouActivity.this, page+"", Toast.LENGTH_SHORT).show();
                                System.out.println("page = " + page);
                                presenter.requestProduct(i,page);
                            }
                        }
                    }
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        //得到当前显示的最后一个item的view
                        View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
                        //得到lastChildView的bottom坐标值
                        int lastChildBottom = lastChildView.getBottom();
                        //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                        int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
                        //通过这个lastChildView得到这个view当前的position值
                        int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);

                        //判断lastChildView的bottom值跟recyclerBottom
                        //判断lastPosition是不是最后一个position
                        //如果两个条件都满足则说明是真正的滑动到了底部
                        if(lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){

                            Toast.makeText(SouActivity.this, "滑动到底了", Toast.LENGTH_SHORT).show();
                            
                            /*HttpUtils utils = new HttpUtils();
                            for (int i = 0; i < data1.size(); i++) {
                                final int finalI = i;
                                utils.getJson(Api.PRODUCTSL_API + data1.get(i).getPscid() + "&&page=2", new HttpUtils.HttpCallBack() {
                                    @Override
                                    public void onSusscess(String data) {
                                        System.out.println("Api.PRODUCTSL_API + data1.get(i).getPscid() + \"&&page=2\" = " + Api.PRODUCTSL_API + data1.get(finalI).getPscid() + "&&page=2");
                                        System.out.println("data = " + data);
                                        Toast.makeText(SouActivity.this, data, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }*/

                        }
                    }
                });

                huan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isTrue = con.getBoolean("isTrue", true);
                        if(isTrue){
                            huan.setImageResource(R.mipmap.a_a);
                            System.out.println("data1sssssssssssssdddddddddddddddddd = " + data1);
                            LinearLayoutManager manager = new LinearLayoutManager(SouActivity.this);
                            rv.setLayoutManager(manager);
                            adapter = new MyProductRecycleView(SouActivity.this,data1);
                            rv.setAdapter(adapter);
                            adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                                @Override
                                public void onItemClickListener(View v, int position) {
                                    Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                    intent.putExtra("pid",data1.get(position).getPid());
                                    System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                    startActivity(intent);
                                    Toast.makeText(SouActivity.this, "seaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                                }
                            });
                            con.edit().putBoolean("isTrue",false).commit();
                        }else{
                            huan.setImageResource(R.mipmap.a9n);
                            GridLayoutManager manager = new GridLayoutManager(SouActivity.this,2);
                            rv.setLayoutManager(manager);
                            adapter1 = new MyProductRecycleView1(SouActivity.this,data1);
                            rv.setAdapter(adapter1);
                            adapter1.setOnItemClickListener(new MyProductRecycleView1.OnItemClickListener() {
                                @Override
                                public void onItemClickListener(View v, int position) {
                                    Toast.makeText(SouActivity.this, "sssssssss", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                    intent.putExtra("pid",data1.get(position).getPid());
                                    System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                    startActivity(intent);
                                }
                            });
                            con.edit().putBoolean("isTrue",true).commit();
                        }
                    }
                });
                paixu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        paixu.setTextColor(Color.RED);
                        xiaoliang.setTextColor(Color.BLACK);
                        boolean isFirst = con.getBoolean("isFirst", true);
                        if(isFirst){
                            Toast.makeText(SouActivity.this, "降序排列", Toast.LENGTH_SHORT).show();
                            Collections.sort(data1, new Comparator<Products.DataBean>() {
                                @Override
                                public int compare(Products.DataBean db, Products.DataBean t1) {
                                    int i = (int) (db.getPrice() - t1.getPrice());
                                    if(i>0){
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                            if(con.getBoolean("isTrue", true)){
                                adapter1 = new MyProductRecycleView1(SouActivity.this,data1);
                                adapter1.notifyDataSetChanged();
                                rv.setAdapter(adapter1);
                                adapter1.setOnItemClickListener(new MyProductRecycleView1.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });

                            }else {
                                adapter = new MyProductRecycleView(SouActivity.this,data1);
                                adapter.notifyDataSetChanged();
                                rv.setAdapter(adapter);
                                adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }
                            con.edit().putBoolean("isFirst",false).commit();
                        }else {
                            Toast.makeText(SouActivity.this, "升序排列", Toast.LENGTH_SHORT).show();
                            Collections.sort(data1, new Comparator<Products.DataBean>() {
                                @Override
                                public int compare(Products.DataBean db, Products.DataBean t1) {
                                  int i = (int) (db.getPrice() - t1.getPrice());
                                    if(i<0){
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                            if(con.getBoolean("isTrue", true)){
                                adapter1 = new MyProductRecycleView1(SouActivity.this,data1);
                                adapter1.notifyDataSetChanged();
                                rv.setAdapter(adapter1);
                                adapter1.setOnItemClickListener(new MyProductRecycleView1.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                adapter = new MyProductRecycleView(SouActivity.this,data1);
                                adapter.notifyDataSetChanged();
                                rv.setAdapter(adapter);
                                adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }
                            con.edit().putBoolean("isFirst",true).commit();
                        }

                    }
                });

                xiaoliang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        xiaoliang.setTextColor(Color.RED);
                        paixu.setTextColor(Color.BLACK);
                        boolean isFirst = con.getBoolean("isFirst", true);
                        if(isFirst){
                            Toast.makeText(SouActivity.this, "降序排列", Toast.LENGTH_SHORT).show();
                            Collections.sort(data1, new Comparator<Products.DataBean>() {
                                @Override
                                public int compare(Products.DataBean db, Products.DataBean t1) {
                                    int i = (int) (db.getSalenum() - t1.getSalenum());
                                    if(i>0){
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                            if(con.getBoolean("isTrue", true)){
                                adapter1 = new MyProductRecycleView1(SouActivity.this,data1);
                                adapter1.notifyDataSetChanged();
                                rv.setAdapter(adapter1);
                                adapter1.setOnItemClickListener(new MyProductRecycleView1.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });

                            }else {
                                adapter = new MyProductRecycleView(SouActivity.this,data1);
                                adapter.notifyDataSetChanged();
                                rv.setAdapter(adapter);
                                adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }
                            con.edit().putBoolean("isFirst",false).commit();
                        }else {
                            Toast.makeText(SouActivity.this, "升序排列", Toast.LENGTH_SHORT).show();
                            Collections.sort(data1, new Comparator<Products.DataBean>() {
                                @Override
                                public int compare(Products.DataBean db, Products.DataBean t1) {
                                    int i = (int) (db.getSalenum() - t1.getSalenum());
                                    if(i<0){
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                            if(con.getBoolean("isTrue", true)){
                                adapter1 = new MyProductRecycleView1(SouActivity.this,data1);
                                adapter1.notifyDataSetChanged();
                                rv.setAdapter(adapter1);
                                adapter1.setOnItemClickListener(new MyProductRecycleView1.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                adapter = new MyProductRecycleView(SouActivity.this,data1);
                                adapter.notifyDataSetChanged();
                                rv.setAdapter(adapter);
                                adapter.setOnItemClickListener(new MyProductRecycleView.OnItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View v, int position) {
                                        Intent intent = new Intent(SouActivity.this,ProdutctsXiangqingActivity.class);
                                        intent.putExtra("pid",data1.get(position).getPid());
                                        System.out.println("data1.get(position).getPid() = " + data1.get(position).getPid());
                                        startActivity(intent);
                                    }
                                });
                            }
                            con.edit().putBoolean("isFirst",true).commit();
                        }

                    }
                });



            }
        });


    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void getProductFail(String msg) {

    }

    @Override
    public void Failure(Call call, IOException e) {

    }
}
