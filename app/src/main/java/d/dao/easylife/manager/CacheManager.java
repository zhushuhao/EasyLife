package d.dao.easylife.manager;

import java.util.List;

import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.ui.EasyLifeApplication;
import d.dao.easylife.utils.ACache;

/**
 * Created by dao on 5/23/16.
 * 管理缓存
 */
public class CacheManager {

    private static ACache mACache;
    private static CacheManager mInstance;

    static {
        mInstance = new CacheManager();
        mACache = EasyLifeApplication.getInstance().getACache();
    }

    public static synchronized CacheManager getInstance() {
        return mInstance;
    }


    /**
     * 储存新闻数据
     *
     * @param data
     * @param remove false:和原有的数据一起保存下来
     *               true:先把原有的数据清除,再添加
     */
    public void setNewsData(List<BaseNewsData> data, boolean remove) {

    }

    /**
     * 储存新闻数据,和原来的数据一起储存
     *
     * @param data
     */
    public void setNewsData(List<BaseNewsData> data) {
        setNewsData(data, false);
    }

}
