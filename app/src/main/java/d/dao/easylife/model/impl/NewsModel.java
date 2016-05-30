package d.dao.easylife.model.impl;

import d.dao.easylife.api.EasyLife;
import d.dao.easylife.bean.news.News;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.model.INewsModel;
import rx.Observable;

/**
 * Created by dao on 5/29/16.
 * 新闻IModel实现
 */
public class NewsModel implements INewsModel {

    private static NewsModel mInstance;

    public static NewsModel getInstance() {
        if (mInstance == null) {
            mInstance = new NewsModel();
        }
        return mInstance;
    }


    /**
     * 获取新闻
     *
     * @param time
     * @param pageSize
     * @return
     */
    @Override
    public Observable<News> loadNews(long time, int pageSize) {
        return EasyLife.getInstance().getApiService(BaseUrl.NEWS).loadNews(time, pageSize);
    }
}
