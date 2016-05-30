package d.dao.easylife.ui.view;

import java.util.List;

import d.dao.easylife.bean.news.BaseNewsData;

/**
 * Created by dao on 5/29/16.
 * MainActivity
 */
public interface IMainView {

    /**
     *  刷新时获取新闻成功
     * @param list 新闻数据列表
     */
    void onGetNewsSuccess(List<BaseNewsData> list);

    /**
     * 刷新时获取新闻失败
     */
    void onGetNewsFailure(Throwable e);

    /**
     * 向下加载更多时获取新闻成功
     *
     * @param list 新闻数据列表
     */
    void onLoadNewsSuccess(List<BaseNewsData> list);

    /**
     * 向下加载更多时获取新闻失败
     */
    void onLoadNewsFailure(Throwable e);

}
