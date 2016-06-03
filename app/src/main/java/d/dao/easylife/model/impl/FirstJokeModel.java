package d.dao.easylife.model.impl;

import d.dao.easylife.api.EasyLife;
import d.dao.easylife.bean.joke.JokeFirst;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.model.IFirstJokeModel;
import rx.Observable;

/**
 * Created by dao on 5/31/16.
 * 笑话1
 */
public class FirstJokeModel implements IFirstJokeModel {

    private static FirstJokeModel mInstance;

    public synchronized static FirstJokeModel getInstance() {
        if (mInstance == null) {
            mInstance = new FirstJokeModel();
        }
        return mInstance;
    }
    @Override
    public Observable<JokeFirst> loadFirstJoke(int maxId,int minId,int size) {
        return EasyLife.getInstance().getApiService(BaseUrl.NEWS).loadFirstJoke(maxId,minId,size);
    }
}
