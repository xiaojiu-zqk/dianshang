package com.example.shopping.models.api;

import com.example.shopping.models.bean.CateGroyDetailRvBean;
import com.example.shopping.models.bean.CategroyDetailTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategroyDetailApi {
    @GET("goods/category")
    Flowable<CategroyDetailTabBean> getCategroyDetailTab(@Query("id") int id);

    @GET("api/goods/list")
    Flowable<CateGroyDetailRvBean> getCategroyDetailRv(@Query("categoryId") int id, @Query("page") int page, @Query("size") int size);

}
