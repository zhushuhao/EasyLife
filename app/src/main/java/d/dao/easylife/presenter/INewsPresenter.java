package d.dao.easylife.presenter;

import d.dao.easylife.ui.view.IMainView;

/**
 * Created by dao on 5/29/16.
 * 新闻
 */
public interface INewsPresenter {

    void attachView(IMainView view);

    /**
     * 刷新时加载
     */
    void loadNews();

    /**
     * 下拉加载更多
     *
     * @param time 时间戳
     */
    void loadMore(long time);
    void detachView(IMainView view);
}
