package com.example.shopping.details;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.adapter.details.BuyListRvAdapter;
import com.example.shopping.adapter.details.BuyWentiAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shangcheng.details.BuyDetailContract;
import com.example.shopping.models.bean.BuyBean;
import com.example.shopping.models.bean.GoodsRelatedbean;
import com.example.shopping.models.bean.NewHotCardListBean;
import com.example.shopping.persenter.details.BuyPersenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewHotDetailActivity extends BaseActivity<BuyDetailContract.Persenter> implements BuyDetailContract.View {


    @BindView(R.id.name_new_hot_detail)
    TextView nameNewHotDetail;
    @BindView(R.id.brief_new_hot_detail)
    TextView briefNewHotDetail;
    @BindView(R.id.price_new_hot_detail)
    TextView priceNewHotDetail;
    @BindView(R.id.shu)
    TextView shu;
    @BindView(R.id.jiantou_new_top_detail)
    TextView jiantouNewTopDetail;
    @BindView(R.id.chicun_new_hot_detail)
    TextView chicunNewHotDetail;
    @BindView(R.id.chicunValue_new_hot_detail)
    TextView chicunValueNewHotDetail;
    @BindView(R.id.color_new_hot_detail)
    TextView colorNewHotDetail;
    @BindView(R.id.colorValue_new_hot_detail)
    TextView colorValueNewHotDetail;
    @BindView(R.id.caizhi_new_hot_detail)
    TextView caizhiNewHotDetail;
    @BindView(R.id.caizhiValue_new_hot_detail)
    TextView caizhiValueNewHotDetail;
    @BindView(R.id.chandi_new_hot_detail)
    TextView chandiNewHotDetail;
    @BindView(R.id.chandiValue_new_hot_detail)
    TextView chandiValueNewHotDetail;
    @BindView(R.id.webView_new_hot_detail)
    WebView webViewNewHotDetail;
    @BindView(R.id.shoucang_new_top_detail)
    TextView shoucangNewTopDetail;
    @BindView(R.id.wentiRv_new_top_detail)
    RecyclerView wentiRvNewTopDetail;
    @BindView(R.id.listRv_new_top_detail)
    RecyclerView listRvNewTopDetail;
    @BindView(R.id.banner_new_hot_detail)
    Banner bannerNewHotDetail;
    @BindView(R.id.shop_new_top_detail)
    ImageView shopNewTopDetail;
    @BindView(R.id.lijisjop_new_top_detail)
    TextView lijisjopNewTopDetail;
    @BindView(R.id.add_new_hot_detail)
    TextView addNewHotDetail;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    private String aNew;
    private int id;
    private int num;
    private ArrayList<BuyBean.DataBeanX.IssueBean> issueBeans;
    private BuyWentiAdapter wentiAdapter;
    private BuyListRvAdapter buyListRvAdapter;
    private ArrayList<GoodsRelatedbean.DataBean.GoodsListBean> goodsListBeans;
    private int productId;
    private String retail_price;
    private String list_pic_url;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_hot_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        aNew = intent.getStringExtra("new");
        wentiRvNewTopDetail.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        listRvNewTopDetail.setLayoutManager(new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        issueBeans = new ArrayList<>();
        wentiAdapter = new BuyWentiAdapter(issueBeans, this);
        wentiRvNewTopDetail.setAdapter(wentiAdapter);
        goodsListBeans = new ArrayList<>();
        buyListRvAdapter = new BuyListRvAdapter(goodsListBeans, this);
        listRvNewTopDetail.setAdapter(buyListRvAdapter);
        shu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
        addNewHotDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            persenter.getCardList(id,num,productId);
            }
        });
    }

    private void pop() {
        num = 1;
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_newhotdetail, null);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        if (!popupWindow.isShowing()) {
            popupWindow.showAtLocation(addNewHotDetail, Gravity.BOTTOM, 0, 80);
        }
        Button add = inflate.findViewById(R.id.add_newhot_detail_pop);
        Button jian = inflate.findViewById(R.id.jian_new_hot_detail_pop);
        TextView number = inflate.findViewById(R.id.number_newhot_detail_pop);
        ImageView img = inflate.findViewById(R.id.img_new_hot_detail_pop);
        TextView price = inflate.findViewById(R.id.price_newhot_detail_pop);
        Glide.with(this).load(list_pic_url).into(img);
        price.setText(retail_price);
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num>1){
                    num--;
                    number.setText(num+"");
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                number.setText(num+"");
            }
        });
    }

    @Override
    protected void initData() {
        persenter.getBuy(id);
        persenter.getGoodsRelaterd(id);
    }

    @Override
    protected BuyPersenter createPersenter() {
        return new BuyPersenter();
    }


    @Override
    public void getBuyReturn(BuyBean result) {
        List<?> specificationList = result.getData().getSpecificationList();
        wentiAdapter.updata(result.getData().getIssue());
        List<BuyBean.DataBeanX.GalleryBean> gallery = result.getData().getGallery();
        ArrayList<String> strings = new ArrayList<>();
        for (BuyBean.DataBeanX.GalleryBean galleryBean : gallery) {
            String img_url = galleryBean.getImg_url();
            strings.add(img_url);
        }
        setbanner(bannerNewHotDetail, strings);
        BuyBean.DataBeanX.InfoBean info = result.getData().getInfo();
        list_pic_url = info.getList_pic_url();
        retail_price = info.getRetail_price();
        setTextViewValues(info.getName(), info.getGoods_brief(), info.getRetail_price() + "");
        List<BuyBean.DataBeanX.ProductListBean> productList = result.getData().getProductList();
        productId = productList.get(0).getId();
    }

    private void setTextViewValues(String name, String goods_brief, String retail_price) {
        nameNewHotDetail.setText(name);
        briefNewHotDetail.setText(goods_brief);
        priceNewHotDetail.setText("￥" + retail_price);
    }

    @Override
    public void getGoodsRelatedReturn(GoodsRelatedbean result) {
        buyListRvAdapter.updata(result.getData().getGoodsList());
    }

    @Override
    public void getCardListReturn(NewHotCardListBean resilt) {

    }

    private static void setbanner(Banner bannerShouye, ArrayList<String> strings) {
        bannerShouye.setImages(strings)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
    }
}
