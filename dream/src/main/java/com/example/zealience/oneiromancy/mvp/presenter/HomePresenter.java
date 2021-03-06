package com.example.zealience.oneiromancy.mvp.presenter;

import com.example.zealience.oneiromancy.R;
import com.example.zealience.oneiromancy.api.ApiRequest;
import com.steven.base.app.SharePConstant;
import com.example.zealience.oneiromancy.constant.UrlConstant;
import com.example.zealience.oneiromancy.entity.DreamTypeEntity;
import com.example.zealience.oneiromancy.entity.HomeRecommendEntity;
import com.example.zealience.oneiromancy.mvp.contract.HomeContract;
import com.example.zealience.oneiromancy.util.ParseJsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.steven.base.rx.BaseObserver;
import com.steven.base.util.AssetsUtil;
import com.steven.base.util.GsonUtil;
import com.steven.base.util.SPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @user steven
 * @createDate 2019/2/20 11:14
 * @description 自定义
 */
public class HomePresenter extends HomeContract.Presenter {
    private Integer[] images = {R.mipmap.bg_banner_one, R.mipmap.bg_banner_two, R.mipmap.bg_banner_three};

    @Override
    public void getHomeDreamTypeData() {
        mRxManager.add(mModel.getDreamType(ApiRequest.getHomeDreamType()).subscribeWith(new BaseObserver<List<DreamTypeEntity>>(mContext, false) {
            @Override
            protected void onSuccess(List<DreamTypeEntity> dreamTypeEntities) {
                mView.setDreamTypeData(dreamTypeEntities);
                SPUtils.setSharedStringData(mContext, SharePConstant.KEY_DREAM_DATA, GsonUtil.getGson().toJson(dreamTypeEntities));
            }

            @Override
            protected void onError(String message) {
                mView.onError(message);
            }
        }));
    }

    @Override
    public void getHomeBannerData() {
        mView.setBannerDdata(Arrays.asList(images));
    }

    @Override
    public void getHotSearchData() {
        String[] hots = {"被人打", "捡到钱", "梦见回到小学", "梦到吃好吃的", "被人追杀"};
        mView.setHotSearchData(hots);
    }

    @Override
    public void getHomeRecommendData() {
        List<HomeRecommendEntity> recommendEntities = new ArrayList<>();
        String jsonResult = AssetsUtil.getStrFromAssets("homeData", mContext);
        JsonArray jsonElements = new JsonParser().parse(jsonResult).getAsJsonArray();
//        recommendEntities = GsonUtil.jsonArrayToList(jsonElements,HomeRecommendEntity.class);
        for (int i = 0; i < jsonElements.size(); i++) {
            HomeRecommendEntity entity = ParseJsonUtil.parseRecomendEntity(jsonElements.get(i).getAsJsonObject());
            recommendEntities.add(entity);
        }
        mView.setRecommendData(recommendEntities);
    }

    @Override
    public void getAppActivityData() {
        mView.showAppAdv(UrlConstant.url_app_adv_photo, UrlConstant.url_app_adv_function);
    }
}
