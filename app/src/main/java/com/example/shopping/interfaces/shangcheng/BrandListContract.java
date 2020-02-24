package com.example.shopping.interfaces.shangcheng;

import com.example.shopping.interfaces.IBasePersenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.models.bean.BrandListBean;

public interface BrandListContract {
    interface View extends IBaseView{
        void getBrandListReturn(BrandListBean result);
    }
    interface Persenter extends IBasePersenter<View>{
        void getBrandList(int page, int size);
    }
}
