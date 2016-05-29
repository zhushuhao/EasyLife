package d.dao.easylife.ui.view;

import java.util.List;

import d.dao.easylife.bean.News;

/**
 * Created by dao on 5/29/16.
 * MainActivity
 */
public interface IMainView {

    /**
     * 获取新闻成功
     * @param list
     */
    void onGetNewsSuccess(List<News> list);

    /**
     * 获取新闻失败
     */
    void onGetNewsFailure();
}
