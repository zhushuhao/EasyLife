package d.dao.easylife.api;

import d.dao.easylife.bean.news.News;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by dao on 5/29/16.
 * 管理API
 */
public interface ApiService {

    //新闻接口:
    //http://api.1-blog.com/biz/bizserver/news/list.do?max_behot_time=1464583244994&size=10
    @GET("/biz/bizserver/news/list.do")
    Observable<News> loadNews(@Query("max_behot_time") long max_behot_time, @Query("size") int size);
}
