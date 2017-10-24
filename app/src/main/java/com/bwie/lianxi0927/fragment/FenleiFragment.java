package com.bwie.lianxi0927.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.MyAdapter;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.SouActivity;
import com.bwie.lianxi0927.XiangqingActivity;
import com.bwie.lianxi0927.ZhuActivity;
import com.bwie.lianxi0927.adapter.MyListViewAdapter;
import com.bwie.lianxi0927.bean.Fenlei;
import com.bwie.lianxi0927.bean.Miaosha;
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

import static com.bwie.lianxi0927.common.Api.FENLEI_API;
import static com.bwie.lianxi0927.common.Api.GUANGGAO_API;

/**
 * 作者： 张少丹
 * 时间：  2017/10/7.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class FenleiFragment extends Fragment {

    public static int currPos = 0;
    private View view;
    private ListView listView;
    private FrameLayout fenlei_fl;
    private List<Fenlei.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fenlei_item,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = view.findViewById(R.id.left_fragment_listview);
        fenlei_fl = view.findViewById(R.id.fenlei_fl);
        EditText ss = view.findViewById(R.id.et_ss);
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SouActivity.class);
                startActivity(intent);
            }
        });
        initDatas();
    }

    private void initDatas() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(FENLEI_API).post(build).build();
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
                        final Fenlei fenlei = gson.fromJson(result, Fenlei.class);
                        data = fenlei.getData();
                        if(getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    FenleiXiangqingFragment fsf = new FenleiXiangqingFragment();
                                    fsf.getCid(data.get(0).getCid());
                                    getChildFragmentManager().beginTransaction().replace(R.id.fenlei_fl,fsf).commit();
                                    final List<Fenlei.DataBean> data = fenlei.getData();
                                    final List<Fragment> fragmentList = new ArrayList<>();
                                    for (int i = 0; i < data.size(); i++) {
                                        fragmentList.add(new FenleiXiangqingFragment());
                                    }

                                    final MyListViewAdapter adapter = new MyListViewAdapter(getActivity(),data);
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            currPos = i;
                                            adapter.notifyDataSetChanged();
                                            FenleiXiangqingFragment fsf = new FenleiXiangqingFragment();
                                            fsf.getCid(data.get(i).getCid());
                                            getChildFragmentManager().beginTransaction().replace(R.id.fenlei_fl,fsf).commit();
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
}