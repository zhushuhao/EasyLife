package d.dao.easylife.api;


import android.util.Log;

import java.util.List;

import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.bean.news.News;
import d.dao.easylife.model.impl.NewsModel;
import d.dao.easylife.utils.RxUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * 管理API数据获取
 */
public class DataManager {

    private static DataManager mInstance;

    private NewsModel mNewsModel;

    private DataManager() {
        this.mNewsModel = NewsModel.getInstance();
    }

    //单例
    public synchronized static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
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
    public Observable<List<BaseNewsData>> loadNews(long time, int pageSize) {
        Log.e("datamanager", "start");
        return this.mNewsModel.loadNews(time, pageSize)
                .map(new Func1<News, List<BaseNewsData>>() {
                    @Override
                    public List<BaseNewsData> call(News news) {
                        Log.e("datamanager", "回到manager");

                        Log.e("news", news.toString());
                        return news.detail;
                    }
                })
                .compose(RxUtils.<List<BaseNewsData>>applyIOToMainThreadSchedulers());
    }


}
