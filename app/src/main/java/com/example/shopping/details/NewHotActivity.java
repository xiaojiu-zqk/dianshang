package com.example.shopping.details;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.details.NewHotAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shangcheng.ShouyeContract;
import com.example.shopping.models.bean.BannerInfoTopBean;
import com.example.shopping.models.bean.NewDataBean;
import com.example.shopping.persenter.details.NewHotPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class NewHotActivity extends BaseActivity<ShouyeContract.NewHotPersenter> implements ShouyeContract.NewHotView, View.OnClickListener {

    @BindView(R.id.img_new_hot)
    ImageView imgNewHot;
    @BindView(R.id.name_new_hot)
    TextView nameNewHot;
    @BindView(R.id.zonghe_new_hot)
    Button zongheNewHot;
    @BindView(R.id.jiage_new_hot)
    Button jiageNewHot;
    @BindView(R.id.fenlei_new_hot)
    Button fenleiNewHot;
    @BindView(R.id.rv_new_hot)
    RecyclerView rvNewHot;
    private String ORDER = "asc";
    private String SORT = "default";
    private int tag;
    private ArrayList<NewDataBean.DataBeanX.DataBean> newList;
    private ArrayList<NewDataBean.DataBeanX.DataBean> hotList;
    private NewHotAdapter newAdapter;
    private NewHotAdapter hotAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_hot;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", 0);
        rvNewHot.setLayoutManager(new GridLayoutManager(this, 2));
        if (tag == 1) {
            newList = new ArrayList<>();
            newAdapter = new NewHotAdapter(newList, this);
            newAdapter.tag = 1;
            rvNewHot.setAdapter(newAdapter);
        } else {
            hotList = new ArrayList<>();
            hotAdapter = new NewHotAdapter(hotList, this);
            hotAdapter.tag = 2;
            rvNewHot.setAdapter(hotAdapter);
        }
        zongheNewHot.setOnClickListener(this);
        jiageNewHot.setOnClickListener(this);
        fenleiNewHot.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        persenter.getBannerInfoTop();

        if (tag == 1) {
            persenter.getNewData(1, 1, 100, ORDER, SORT, 0);
        } else {
            persenter.getHotData(1, 1, 100, ORDER, SORT, 0);
        }
    }

    @Override
    protected ShouyeContract.NewHotPersenter createPersenter() {
        return new NewHotPersenter();
    }

    @Override
    public void getBannerInfoTopReturn(BannerInfoTopBean result) {
        Glide.with(this).load(result.getData().getBannerInfo().getImg_url()).into(imgNewHot);
        nameNewHot.setText(result.getData().getBannerInfo().getName());
    }

    @Override
    public void getNewDataReturn(NewDataBean result) {
            newAdapter.updata(result.getData().getData());
    }

    @Override
    public void getHotDataReturn(NewDataBean result) {
            hotAdapter.updata(result.getData().getData());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zonghe_new_hot:
                if (tag == 1) {
                    SORT = "default";
                    persenter.getNewData(1, 1, 100, ORDER, SORT, 0);
                } else {
                    SORT = "default";
                    persenter.getHotData(1, 1, 100, ORDER, SORT, 0);
                }
                break;
            case R.id.jiage_new_hot:
                SORT = "price";
                if (tag == 1) {
                    if (ORDER.equals("asc")) {
                        ORDER = "desc";
                        persenter.getNewData(1, 1, 100, ORDER, SORT, 0);
                    } else {
                        ORDER = "asc";
                        persenter.getNewData(1, 1, 100, ORDER, SORT, 0);
                    }
                } else {
                    if (ORDER.equals("asc")) {
                        ORDER = "desc";
                        persenter.getHotData(1, 1, 100, ORDER, SORT, 0);
                    } else {
                        ORDER = "asc";
                        persenter.getHotData(1, 1, 100, ORDER, SORT, 0);
                    }
                }
                break;
            case R.id.fenlei_new_hot:
                pop();
                break;
        }
    }

    private void pop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        if(!popupWindow.isShowing()){
            popupWindow.showAsDropDown(fenleiNewHot,0,2);
        }
        TextView all = inflate.findViewById(R.id.all_pop);
        TextView jujia = inflate.findViewById(R.id.jujia_pop);
        TextView yingtong = inflate.findViewById(R.id.yingtong_pop);
        TextView yinshi = inflate.findViewById(R.id.yinshi_pop);
        TextView canchu = inflate.findViewById(R.id.canchu_pop);
        TextView zahuo = inflate.findViewById(R.id.zahuo_pop);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        jujia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        yingtong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        yinshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        canchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        zahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
