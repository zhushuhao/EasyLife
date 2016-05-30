package d.dao.easylife.presenter.impl;

import android.util.Log;

import java.util.List;

import d.dao.easylife.api.DataManager;
import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.presenter.INewsPresenter;
import d.dao.easylife.ui.view.IMainView;
import d.dao.easylife.utils.ReservoirUtils;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dao on 5/29/16.
 * 新闻
 */
public class NewsPresenterImpl implements INewsPresenter {
    private static int pageSize = 10;
    public CompositeSubscription mCompositeSubscription;
    public DataManager mDataManager;
    private IMainView mView;
    private ReservoirUtils reservoirUtils;


    @Override
    public void attachView(IMainView view) {
        this.mView = view;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mDataManager = DataManager.getInstance();
        this.reservoirUtils = ReservoirUtils.getInstance();
    }


    //获取新闻
    //刷新
    @Override
    public void loadNews() {
        long tempTime = System.currentTimeMillis();
        Log.e("time", "" + tempTime);
        this.mCompositeSubscription.add(this.mDataManager.loadNews(tempTime, pageSize)
                .subscribe(new Subscriber<List<BaseNewsData>>() {
                    @Override
                    public void onCompleted() {
                        //移除
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onGetNewsFailure(e);
                    }

                    @Override
                    public void onNext(List<BaseNewsData> newses) {
                        Log.e("news", newses.toString());
                        //获取数据成功
                        mView.onGetNewsSuccess(newses);
                        // 刷新缓存
                        reservoirUtils.refresh("news", newses);
                    }
                }));
    }

    //加载更多
    //time 时间戳
    @Override
    public void loadMore(long time) {
        this.mCompositeSubscription.add(this.mDataManager.loadNews(time, pageSize)
                .subscribe(new Subscriber<List<BaseNewsData>>() {
                    @Override
                    public void onCompleted() {
                        //移除
                        if (mCompositeSubscription != null) {
                            mCompositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", e.toString());
                        mView.onLoadNewsFailure(e);
                    }

                    @Override
                    public void onNext(List<BaseNewsData> newses) {
                        Log.e("news", newses.toString());
                        //获取数据成功
                        mView.onLoadNewsSuccess(newses);
                        // 刷新缓存
                        reservoirUtils.refresh("news", newses);
                    }
                }));
    }

    @Override
    public void detachView(IMainView view) {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }
}
