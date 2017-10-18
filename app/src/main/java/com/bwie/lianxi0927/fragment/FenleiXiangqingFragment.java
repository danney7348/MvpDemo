package com.bwie.lianxi0927.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.adapter.MyListViewAdapter;
import com.bwie.lianxi0927.adapter.MyRecyAdapter;
import com.bwie.lianxi0927.bean.Fenlei;
import com.bwie.lianxi0927.bean.ProductCatagory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.CATAGRAY_API;
import static com.bwie.lianxi0927.common.Api.FENLEI_API;

/**
 * 作者： 张少丹
 * 时间：  2017/10/11.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class FenleiXiangqingFragment extends Fragment {
    private int cid;
    private View view;
    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fenleixiangqing_item,null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        rv = view.findViewById(R.id.rv_xiang);
        initData();
    }
    private void initData() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(CATAGRAY_API+"?cid="+cid).post(build).build();
        System.out.println("CATAGRAY_API+\"?cid=\"+cid = " + CATAGRAY_API+"?cid="+cid);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    final String result;
                    try {
                        result = response.body().string();
                        System.out.println("result=======" + result);
                        Gson gson = new Gson();
                        final ProductCatagory productCatagory = gson.fromJson(result, ProductCatagory.class);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<ProductCatagory.DataBean> data = productCatagory.getData();
                                    MyRecyAdapter adapter = new MyRecyAdapter(getContext(),data);
                                    rv.setAdapter(adapter);
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    rv.setLayoutManager(linearLayoutManager);
                                    rv.setItemAnimator(new RecyclerView.ItemAnimator() {
                                        @Override
                                        public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
                                            return false;
                                        }

                                        @Override
                                        public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                                            return false;
                                        }

                                        @Override
                                        public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                                            return false;
                                        }

                                        @Override
                                        public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                                            return false;
                                        }

                                        @Override
                                        public void runPendingAnimations() {

                                        }

                                        @Override
                                        public void endAnimation(RecyclerView.ViewHolder item) {


                                        }

                                        @Override
                                        public void endAnimations() {

                                        }

                                        @Override
                                        public boolean isRunning() {
                                            return false;
                                        }
                                    });
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
            public void getCid(int position) {
                cid = position;
            }
    }

