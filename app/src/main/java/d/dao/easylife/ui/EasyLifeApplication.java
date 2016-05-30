package d.dao.easylife.ui;

import android.app.Application;
import android.content.Context;

import com.anupcowkur.reservoir.Reservoir;
import com.zhy.autolayout.config.AutoLayoutConifg;

import d.dao.easylife.utils.ACache;

/**
 * Created by dao on 5/29/16.
 */
public class EasyLifeApplication extends Application {

    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;
    private static EasyLifeApplication mInstance = new EasyLifeApplication();
    private static ACache mACache;
    private static Context mContext;

    public static EasyLifeApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = EasyLifeApplication.this;
        mACache = ACache.get(mContext);
        AutoLayoutConifg.getInstance().useDeviceSize();
        this.initReservoir();



    }

    public synchronized ACache getACache() {
        if (mACache == null) {
            mACache = ACache.get(mContext);
        }
        return mACache;
    }


    private void initReservoir() {
        try {
            Reservoir.init(this, CACHE_DATA_MAX_SIZE);
        } catch (Exception e) {
            //failure
            e.printStackTrace();
        }
    }

}
