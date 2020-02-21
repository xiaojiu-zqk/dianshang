package com.example.shopping.adapter.details;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.models.bean.CateGroyDetailRvBean;

import java.util.List;

public class CategroyDetailAdaper extends BaseAdapter {
    public CategroyDetailAdaper(List data, Context context) {
        super(data, context);
    }

    @Override
    public int getLayout() {
        return R.layout.item_categroy_detail;
    }

    @Override
    public void bindData(BaseViewHolder holder, Object o) {
        CateGroyDetailRvBean.DataBeanX.DataBean dataBean = (CateGroyDetailRvBean.DataBeanX.DataBean) o;
        ImageView img = (ImageView) holder.getView(R.id.img_categroy_detail_item);
        TextView name = (TextView) holder.getView(R.id.name_categroy_detail_item);
        TextView price = (TextView) holder.getView(R.id.price_categroy_detail_item);
        Glide.with(mContext).load(dataBean.getList_pic_url()).into(img);
        name.setText(dataBean.getName());
        price.setText("￥"+dataBean.getRetail_price());
    }
}
