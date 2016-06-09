package d.dao.easylife.presenter.impl;

import d.dao.easylife.api.DataManager;
import d.dao.easylife.bean.ip.BaseIpData;
import d.dao.easylife.presenter.IQueryIpPresenter;
import d.dao.easylife.ui.view.IQueryIpView;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dao on 6/4/16.
 * ip查询
 */
public class QueryIpPresenterImpl implements IQueryIpPresenter {

    private IQueryIpView mView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(IQueryIpView view) {
        this.mView = view;
        this.mDataManager = DataManager.getInstance();
        this.mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void queryIp(String ip, String key) {
        this.mCompositeSubscription.add(this.mDataManager.queryIp(ip, key).subscribe(new Subscriber<BaseIpData>() {
            @Override
            public void onCompleted() {
                if (mCompositeSubscription != null) {
                    mCompositeSubscription.remove(this);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.onQueryIpFailure(e);
            }

            @Override
            public void onNext(BaseIpData baseIpData) {
                mView.onQueryIpSuccess(baseIpData);
            }
        }));
    }

    @Override
    public void detachView(IQueryIpView view) {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }
}
