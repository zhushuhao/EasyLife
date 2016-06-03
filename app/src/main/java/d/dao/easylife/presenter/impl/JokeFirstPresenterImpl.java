package d.dao.easylife.presenter.impl;

import android.util.Log;

import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import d.dao.easylife.api.DataManager;
import d.dao.easylife.bean.joke.BaseJokeFirstData;
import d.dao.easylife.presenter.IJokeFirstPresenter;
import d.dao.easylife.ui.view.IJokeFirstView;
import d.dao.easylife.utils.ReservoirUtils;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dao on 5/31/16.
 * 第一个选项卡的笑话接口实现
 */
public class JokeFirstPresenterImpl implements IJokeFirstPresenter {

    private IJokeFirstView mView;
    private CompositeSubscription mCompositeSubscription;
    private DataManager mDataManager;
    private ReservoirUtils reservoirUtils;


    @Override
    public void attachView(IJokeFirstView view) {
        this.mView = view;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mDataManager = DataManager.getInstance();
        this.reservoirUtils = ReservoirUtils.getInstance();
    }

    /**
     * 刷新笑话1,如果maxId=0,minId=0,说明是第一次进入时刷新
     *
     * @param maxId 最大ID
     * @param minId 最小ID
     * @param size  条数
     */
    @Override
    public void loadFirstJoke(int maxId, int minId, int size) {
        this.mCompositeSubscription.add(this.mDataManager.loadFirstJoke(maxId, minId, size)
                .subscribe(new Subscriber<List<BaseJokeFirstData>>() {
                    @Override
                    public void onCompleted() {
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BaseJokeFirstData> baseJokeFirstDatas) {
                        Log.e("result", baseJokeFirstDatas.toString());
                        mView.onGetJokeSuccess(baseJokeFirstDatas);
                        // 刷新缓存
                        reservoirUtils.refresh("jokefirst", baseJokeFirstDatas);
                    }
                }));
    }

    /**
     * 加载笑话1,如果maxId=0,minId=0,说明是第一次进入时刷新
     *
     * @param maxId 最大ID
     * @param minId 最小ID
     * @param size  条数
     */
    @Override
    public void loadFirstJokeMore(final int maxId, int minId, int size) {
        this.mCompositeSubscription.add(this.mDataManager.loadFirstJoke(maxId, minId, size)
                .subscribe(new Subscriber<List<BaseJokeFirstData>>() {
                    @Override
                    public void onCompleted() {
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        if (maxId == 0) {//如果是第一次进入刷新
                            //获取缓存信息
                            Type resultType = new TypeToken<List<BaseJokeFirstData>>() {
                            }.getType();
                            reservoirUtils.get("jokefirst", resultType, new ReservoirGetCallback<List<BaseJokeFirstData>>() {
                                @Override
                                public void onSuccess(List<BaseJokeFirstData> object) {
                                    //获取到本地数据
                                    if (object.size() > 0) {
                                        //如果得到缓存的信息
                                        mView.onLoadJokeSuccess(object);
                                    } else {
                                        mView.onGetJokeFailure(e);
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    //获取本地缓存信息也失败,显示提示加载信息
                                    mView.onGetJokeFailure(e);
                                }
                            });

                        }else{
                            mView.onGetJokeFailure(e);
                        }

                    }

                    @Override
                    public void onNext(List<BaseJokeFirstData> baseJokeFirstDatas) {
                        Log.e("result", baseJokeFirstDatas.toString());
                        mView.onLoadJokeSuccess(baseJokeFirstDatas);
                        // 刷新缓存
                        reservoirUtils.refresh("jokefirst", baseJokeFirstDatas);
                    }
                }));
    }

    @Override
    public void detachView(IJokeFirstView view) {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }
}
