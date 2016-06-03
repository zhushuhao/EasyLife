package d.dao.easylife.ui.view;

import java.util.List;

import d.dao.easylife.bean.joke.BaseJokeFirstData;
import d.dao.easylife.bean.news.BaseNewsData;

/**
 * Created by dao on 5/31/16.
 * 第一个选项卡joke
 */
public interface IJokeFirstView {
    /**
     *  刷新时获取笑话成功
     * @param list 新闻数据列表
     */
    void onGetJokeSuccess(List<BaseJokeFirstData> list);

    /**
     * 刷新时获取笑话失败
     */
    void onGetJokeFailure(Throwable e);

    /**
     * 向下加载更多时获取笑话成功
     *
     * @param list 新闻数据列表
     */
    void onLoadJokeSuccess(List<BaseJokeFirstData> list);

    /**
     * 向下加载更多时获取笑话失败
     */
    void onLoadJokeFailure(Throwable e);
}
