package com.example.shopping.details;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.adapter.shouye.BrandListAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shangcheng.BrandListContract;
import com.example.shopping.models.bean.BrandListBean;
import com.example.shopping.persenter.home.BrandListPersenter;

import java.util.ArrayList;

import butterknife.BindView;

public class BrandListActivity extends BaseActivity<BrandListContract.Persenter> implements BrandListContract.View {


    @BindView(R.id.rv_barand_list)
    RecyclerView rvBarandList;
    private BrandListAdapter listAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_list;
    }
    @Override
    protected void initView() {
        rvBarandList.setLayoutManager(new LinearLayoutManager(this));
        rvBarandList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ArrayList<BrandListBean.DataBeanX.DataBean> dataBeans = new ArrayList<>();
        listAdapter = new BrandListAdapter(dataBeans, this);
        rvBarandList.setAdapter(listAdapter);
    }

    @Override
    protected void initData() {
        persenter.getBrandList(1,100);
    }

    @Override
    protected BrandListContract.Persenter createPersenter() {
        return new BrandListPersenter();
    }


    @Override
    public void getBrandListReturn(BrandListBean result) {
        listAdapter.updata(result.getData().getData());
    }

}
