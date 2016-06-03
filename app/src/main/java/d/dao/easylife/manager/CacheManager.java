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
     * 保存WeatherActivity默认的城市
     * @param city
     */
    public void saveDefaultCity(String city){
        mACache.put("city",city);
    }
    /**
     * 获取WeatherActivity默认的城市
     */
    public String getDefaultCity(){
        return mACache.getAsString("city");
    }


}
