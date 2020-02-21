package com.example.shopping.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.adapter.details.CategroyDetailAdaper;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shangcheng.details.CategroyDetailContract;
import com.example.shopping.models.bean.CateGroyDetailRvBean;
import com.example.shopping.models.bean.CategroyDetailTabBean;
import com.example.shopping.persenter.fenlei.CategroyDetailPerSenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategroyDetailActivity extends BaseActivity<CategroyDetailContract.Persenter> implements CategroyDetailContract.View,TabLayout.OnTabSelectedListener {
    @BindView(R.id.tab_categroy_detail)
    TabLayout tabCategroyDetail;
    @BindView(R.id.name_categroy_detail)
    TextView nameCategroyDetail;
    @BindView(R.id.froatname_categroy_detail)
    TextView froatnameCategroyDetail;
    @BindView(R.id.rv_cetegroy_detail)
    RecyclerView rvCetegroyDetail;
    private ArrayList<CateGroyDetailRvBean.DataBeanX.DataBean> dataBeans;
    private CategroyDetailAdaper categroyDetailAdaper;
    private List<CategroyDetailTabBean.DataBean.BrotherCategoryBean> beans;

    @Override
    protected int getLayout() {
        return R.layout.activity_categroy_detail;
    }

    @Override
    protected void initView() {
        tabCategroyDetail.addOnTabSelectedListener(this);

        dataBeans = new ArrayList<>();
        rvCetegroyDetail.setLayoutManager(new GridLayoutManager(this,2));
        categroyDetailAdaper = new CategroyDetailAdaper(dataBeans, this);
        rvCetegroyDetail.setAdapter(categroyDetailAdaper);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        persenter.getCategroyDetailTab(id);
    }

    @Override
    protected CategroyDetailContract.Persenter createPersenter() {
        return new CategroyDetailPerSenter();
    }

    @Override
    public void getCategroyDetailTabReturn(CategroyDetailTabBean result) {
        int posi = 0;
        beans = result.getData().getBrotherCategory();

        nameCategroyDetail.setText(result.getData().getCurrentCategory().getName());
        froatnameCategroyDetail.setText(result.getData().getCurrentCategory().getFront_name());
        List<CategroyDetailTabBean.DataBean.BrotherCategoryBean> brotherCategory = result.getData().getBrotherCategory();
        for (int i = 0; i <brotherCategory.size() ; i++) {
            TabLayout.Tab tab = tabCategroyDetail.newTab();
            tab.setText(brotherCategory.get(i).getName());
            //设置标识  获取请求商品列表数据是的id
            tab.setTag(brotherCategory.get(i).getId());
            tabCategroyDetail.addTab(tab);

            //判断id是否一样  一样获取当前tabitem的下标
            if (result.getData().getCurrentCategory().getId() == result.getData().getBrotherCategory().get(i).getId()){
                posi = i;
            }
        }

        if (posi>=0){
            tabCategroyDetail.getTabAt(posi).select();
        }
    }


    @Override
    public void getCategroyDetailRvReturn(CateGroyDetailRvBean result) {
        categroyDetailAdaper.updata(result.getData().getData());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int id  = (int) tab.getTag();
        CategroyDetailTabBean.DataBean.BrotherCategoryBean brotherCategoryBean = beans.get(tab.getPosition());
        nameCategroyDetail.setText(brotherCategoryBean.getName());
        froatnameCategroyDetail.setText(brotherCategoryBean.getFront_name());
        //请求列表数据
        persenter.getCategroyDetailRv(id,1,100);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
