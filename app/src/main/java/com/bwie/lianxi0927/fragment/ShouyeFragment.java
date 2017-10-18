package com.bwie.lianxi0927.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.CustomScanActivity;
import com.bwie.lianxi0927.MyAdapter;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.SouActivity;
import com.bwie.lianxi0927.XiangqingActivity;
import com.bwie.lianxi0927.ZhuActivity;
import com.bwie.lianxi0927.adapter.MyMiaoshaoAdapter;
import com.bwie.lianxi0927.adapter.ViewpagerAdapter;
import com.bwie.lianxi0927.bean.Miaosha;
import com.bwie.lianxi0927.bean.ShouYeGuanggao;
import com.bwie.lianxi0927.bean.Users;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.GUANGGAO_API;

/**
 * 作者： 张少丹
 * 时间：  2017/9/28.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ShouyeFragment extends Fragment {

    private View view;
    private ImageView sao;
    private XBanner binder;
    private ViewPager vp;
    private MyAdapter ma;
    private RecyclerView miaosha;
    private RecyclerView cainixihuan;
    private List<ShouYeGuanggao.DataBean> lunbo;
    private String[] split;
    private List<ShouYeGuanggao.MiaoshaBean.ListBeanX> list;
    private List<Miaosha> list1;
    private String[] split1;
    private long mHour = 02;
    private long mMin = 00;
    private long mSecond = 00;
    private boolean isRun = true;


    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvSecond;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tvHour.setText("0"+mHour+"");
                }else {
                    tvHour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tvMinute.setText("0"+mMin+"");
                }else {
                    tvMinute.setText(mMin+"");
                }
                if (mSecond<10){
                    tvSecond.setText("0"+mSecond+"");
                }else {
                    tvSecond.setText(mSecond+"");
                }
            }
        }
    };
    private EditText sousuo;

    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

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
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        Request request = new Request.Builder().url(GUANGGAO_API).post(build).build();
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
                        final ShouYeGuanggao shouYeGuanggao = gson.fromJson(result, ShouYeGuanggao.class);
                        lunbo = shouYeGuanggao.getData();
                        if(getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("result=======" + result);
                                    final List<String> images = new ArrayList<>();
                                    List<String> titles = new ArrayList<>();
                                    for (int i = 0; i < 4; i++) {
                                        images.add(lunbo.get(i).getIcon());
                                        System.out.println("lunbo = " + lunbo.get(i).getIcon());
                                        titles.add(lunbo.get(i).getTitle());
                                    }
                                    // XBanner适配数据
                                    binder.setData(images, titles);
// XBanner适配数据
                                    binder.setmAdapter(new XBanner.XBannerAdapter() {
                                        @Override
                                        public void loadBanner(XBanner banner, View view, int position) {
                                            System.out.println("banner = " + images.get(position));
                                            Glide.with(getContext()).load(images.get(position)).into((ImageView) view);
                                        }
                                    });
                                    binder.setPageTransformer(Transformer.Default);
// 设置XBanner页面切换的时间，即动画时长
                                    binder.setPageChangeDuration(3000);
                                    binder.setOnItemClickListener(new XBanner.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(XBanner banner, int position) {
                                            Toast.makeText(getContext(), "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    final ShouYeGuanggao.MiaoshaBean miaoshaBean = shouYeGuanggao.getMiaosha();
                                    list = miaoshaBean.getList();
                                    List<Miaosha> list1 = new ArrayList<>();
                                    for (int i = 0; i < list.size(); i++) {
                                        list1.add(new Miaosha(list.get(i).getImages().split("\\|")[0],miaoshaBean.getName(),""));
                                    }
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
                                    gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                                    miaosha.setLayoutManager(gridLayoutManager);
                                    ma = new MyAdapter(list1, getActivity());
                                    miaosha.setAdapter(ma);
                                    ma.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClickListener(int pos, View view) {
                                            Intent intent = new Intent(getContext(), XiangqingActivity.class);
                                            intent.putExtra("url",miaoshaBean.getList().get(pos).getDetailUrl());
                                            startActivity(intent);
                                        }
                                    });
                                    final ShouYeGuanggao.TuijianBean tuijian = shouYeGuanggao.getTuijian();
                                    final List<ShouYeGuanggao.TuijianBean.ListBean> list2 = tuijian.getList();
                                    List<Miaosha> listM = new ArrayList<Miaosha>();
                                    for (int i = 0; i < list2.size(); i++) {
                                        listM.add(new Miaosha(list2.get(i).getImages().split("\\|")[0],list2.get(i).getTitle(),list2.get(i).getPrice()+""));
                                    }
                                    GridLayoutManager g = new GridLayoutManager(getActivity(), 2);
                                    cainixihuan.setLayoutManager(g);
                                    MyAdapter aa = new MyAdapter(listM, getContext());
                                    cainixihuan.setAdapter(aa);
                                     aa.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                                         @Override
                                         public void onItemClickListener(int pos, View view) {
                                             Intent intent = new Intent(getContext(), XiangqingActivity.class);
                                             intent.putExtra("url",tuijian.getList().get(pos).getDetailUrl());
                                             startActivity(intent);
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
        binder = view.findViewById(R.id.banner);
        miaosha = view.findViewById(R.id.rece_gv);
        cainixihuan = view.findViewById(R.id.shouye_cainixihuan);
        sousuo = view.findViewById(R.id.et_sousuo);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SouActivity.class);
                startActivity(intent);
            }
        });
        vp = view.findViewById(R.id.vp);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        ViewpagerAdapter vpAdapter = new ViewpagerAdapter(getChildFragmentManager(),getActivity(),fragmentList);
        vp.setAdapter(vpAdapter);
        tvHour = view.findViewById(R.id.tv_hour);
        tvMinute = view.findViewById(R.id.tv_minute);
        tvSecond = view.findViewById(R.id.tv_second);
        startRun();
    }

    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        binder.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        binder.stopAutoPlay();
    }
}
