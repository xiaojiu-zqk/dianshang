package com.example.shopping.interfaces.shangcheng.details;

import com.example.shopping.interfaces.IBasePersenter;
import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.models.bean.CateGroyDetailRvBean;
import com.example.shopping.models.bean.CategroyDetailTabBean;

public interface CategroyDetailContract {
    interface View extends IBaseView{
        void getCategroyDetailTabReturn(CategroyDetailTabBean result);

        void getCategroyDetailRvReturn(CateGroyDetailRvBean result);
    }
    interface Persenter extends IBasePersenter<View>{
        void getCategroyDetailTab(int id);

        void getCategroyDetailRv(int id,int page,int size);
    }
}
