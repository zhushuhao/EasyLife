package d.dao.easylife.presenter.impl;

import d.dao.easylife.presenter.INewsPresenter;
import d.dao.easylife.ui.view.IMainView;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dao on 5/29/16.
 * 新闻
 */
public class NewsPresenterImpl implements INewsPresenter{
    private IMainView mView;
    public CompositeSubscription mCompositeSubscription;
//    public DataManager mDataManager;


    @Override
    public void attachView(IMainView view) {
        this.mView = view;
        this.mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void loadNews() {

    }

    @Override
    public void detachView(IMainView view) {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }
}
