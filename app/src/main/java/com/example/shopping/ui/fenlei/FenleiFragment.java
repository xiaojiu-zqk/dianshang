package com.example.shopping.ui.fenlei;


import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IBasePersenter;

import butterknife.BindView;

public class FenleiFragment extends BaseFragment {
    @BindView(R.id.text_notifications)
    TextView textNotifications;

    @Override
    protected int getLayout() {
        return R.layout.fragment_fenlei;
    }

    @Override
    protected void initView() {
    textNotifications.setText("这是分类页面");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IBasePersenter createPersenter() {
        return null;
    }
}