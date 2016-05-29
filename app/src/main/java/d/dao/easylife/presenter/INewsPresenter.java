package d.dao.easylife.presenter;

import d.dao.easylife.ui.view.IMainView;

/**
 * Created by dao on 5/29/16.
 * 新闻
 */
public interface INewsPresenter {

    void attachView(IMainView view);
    void loadNews();
    void detachView(IMainView view);
}
