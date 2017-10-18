package com.bwie.lianxi0927.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.R;
import com.bwie.lianxi0927.bean.PruductXiangqing;
import com.bwie.lianxi0927.presenter.ProductXiangqingPresenter;
import com.bwie.lianxi0927.view.ProductXiangqingView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： 张少丹
 * 时间：  2017/10/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class ShopFragment extends android.support.v4.app.Fragment implements ProductXiangqingView {

    private View view;
    private XBanner banner;
    private List<String> imgesUrl;
    private TextView title;
    private TextView price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.shop_xiangqing_fragment,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        ProductXiangqingPresenter productXiangqingPresenter = new ProductXiangqingPresenter(this,getContext());
        Intent intent = getActivity().getIntent();
        int pid = intent.getIntExtra("pid", 0);
        productXiangqingPresenter.requestProductXiangqing(pid);

    }

    private void initView() {
        banner = view.findViewById(R.id.xb_banner);
        title = view.findViewById(R.id.tv_xiangqing_title);
        price = view.findViewById(R.id.tv_xiangqing_price);
    }

    @Override
    public void onProductXiangqingSuccess(final PruductXiangqing.DataBean data1) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imgesUrl = new ArrayList<>();
                String images = data1.getImages();
                final String[] split = images.split("\\|");
                for (int i = 0; i < split.length; i++) {
                    String s = split[i];
                    imgesUrl.add(s);
                }
                banner.setData(imgesUrl,null);
                banner.setPoinstPosition(XBanner.RIGHT);
                banner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getContext()).load(split[0]).into((ImageView) view);
                    }
                });
                System.out.println("data1.getTitle() = " + data1.getTitle());
                title.setText(data1.getTitle());
                price.setText(data1.getPrice()+"");
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
