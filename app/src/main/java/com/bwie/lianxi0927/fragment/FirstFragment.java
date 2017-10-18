package com.bwie.lianxi0927.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.MyAdapter;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.adapter.GvAdapter;
import com.bwie.lianxi0927.bean.Miaosha;
import com.bwie.lianxi0927.bean.MiddleBean;
import com.bwie.lianxi0927.bean.Product;
import com.bwie.lianxi0927.bean.ShouYeGuanggao;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.GUANGGAO_API;
import static com.bwie.lianxi0927.common.Api.PRODUCT_API;

/**
 * 作者： 张少丹
 * 时间：  2017/9/29.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class FirstFragment extends Fragment {

    private View view;
    private GridView gv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.first_item, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gv = view.findViewById(R.id.gv);
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(PRODUCT_API).post(build).build();
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

                        System.out.println("result======="+result);


                        Gson gson = new Gson();
                        Product product = gson.fromJson(result, Product.class);
                        final List<Product.DataBean> data = product.getData();
                        if(getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    List<MiddleBean> list = new ArrayList<>();
                                    for (int i = 0; i < 10; i++) {
                                        list.add(new MiddleBean(data.get(i).getIcon().split("\\|")[0],data.get(i).getName()));
                                    }
                                    GvAdapter gvAdapter = new GvAdapter(getActivity(),list);
                                    gv.setAdapter(gvAdapter);
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
}
