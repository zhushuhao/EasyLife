package d.dao.easylife.model;

import d.dao.easylife.bean.joke.JokeFirst;
import d.dao.easylife.bean.news.News;
import rx.Observable;

/**
 * Created by dao on 5/31/16.
 *笑话1
 */
public interface IFirstJokeModel {

    /**
     * 请求笑话1
     * @param maxId  笑话最大ID
     * @param minId  笑话最小ID
     * @param size   请求条数
     * @return
     */
    Observable<JokeFirst> loadFirstJoke(int maxId,int minId,int size);
}
