package d.dao.easylife.model;

import rx.Observable;

/**
 * Created by dao on 5/31/16.
 * 第一个选项卡的笑话
 */
public interface IJokeFirstModel {
    Observable<String> loadJoke(long time, int pageSize);
}
