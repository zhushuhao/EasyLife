package d.dao.easylife.model;

import d.dao.easylife.bean.news.News;
import rx.Observable;

/**
 * Created by dao on 5/29/16.
 * 新闻model
 */
public interface INewsModel {
    /**
     * 获取新闻
     *
     * @param time     时间戳
     * @param pageSize 每一次请求的数目
     * @return
     */
    Observable<News> loadNews(long time, int pageSize);

}
