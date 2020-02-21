package com.example.shopping.persenter.fenlei;

import com.example.shopping.base.BasePersenter;
import com.example.shopping.common.CommonSubscriber;
import com.example.shopping.interfaces.shangcheng.details.CategroyDetailContract;
import com.example.shopping.models.HttpManager;
import com.example.shopping.models.bean.CateGroyDetailRvBean;
import com.example.shopping.models.bean.CategroyDetailTabBean;
import com.example.shopping.models.bean.CategroyListBean;
import com.example.shopping.utils.RxUtils;

public class CategroyDetailPerSenter extends BasePersenter<CategroyDetailContract.View> implements CategroyDetailContract.Persenter {
    @Override
    public void getCategroyDetailTab(int id) {
        addSubscribe(HttpManager.getCategroyDetailApi().getCategroyDetailTab(id)
                .compose(RxUtils.<CategroyDetailTabBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<CategroyDetailTabBean>(mView){
                    @Override
                    public void onNext(CategroyDetailTabBean result) {
                        mView.getCategroyDetailTabReturn(result);
                    }
                }));
    }

    @Override
    public void getCategroyDetailRv(int id, int page, int size) {
        addSubscribe(HttpManager.getCategroyDetailApi().getCategroyDetailRv(id,page,size)
                .compose(RxUtils.<CateGroyDetailRvBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<CateGroyDetailRvBean>(mView){
                    @Override
                    public void onNext(CateGroyDetailRvBean result) {
                        mView.getCategroyDetailRvReturn(result);
                    }
                }));
    }


}
