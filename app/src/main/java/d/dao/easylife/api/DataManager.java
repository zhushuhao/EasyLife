package d.dao.easylife.api;


import android.util.Log;

import java.util.List;

import javax.crypto.spec.RC2ParameterSpec;

import d.dao.easylife.bean.ip.BaseIpData;
import d.dao.easylife.bean.ip.Ip;
import d.dao.easylife.bean.joke.BaseJokeFirstData;
import d.dao.easylife.bean.joke.JokeFirst;
import d.dao.easylife.bean.news.BaseNewsData;
import d.dao.easylife.bean.news.News;
import d.dao.easylife.bean.robot.BaseRobotResponseData;
import d.dao.easylife.bean.robot.RobotResponseMsg;
import d.dao.easylife.bean.weather.BaseWeatherData;
import d.dao.easylife.bean.weather.Weather;
import d.dao.easylife.model.impl.FirstJokeModel;
import d.dao.easylife.model.impl.NewsModel;
import d.dao.easylife.model.impl.QueryIpModel;
import d.dao.easylife.model.impl.RobotModel;
import d.dao.easylife.model.impl.WeatherModel;
import d.dao.easylife.utils.RxUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * 管理API数据获取
 */
public class DataManager {

    private static DataManager mInstance;

    private NewsModel mNewsModel;//新闻
    private FirstJokeModel mFirstJokeModel;//笑话1
    private WeatherModel mWeatherModel;//天气
    private RobotModel mRobotModel;//机器人
    private QueryIpModel mQueryIpModel;//查询天气


    private DataManager() {
        this.mNewsModel = NewsModel.getInstance();
        this.mFirstJokeModel = FirstJokeModel.getInstance();
        this.mWeatherModel = WeatherModel.getInstance();
        this.mRobotModel = RobotModel.getInstance();
        this.mQueryIpModel = QueryIpModel.getInstance();
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


    //获取笑话1
    public Observable<List<BaseJokeFirstData>> loadFirstJoke(int maxId, int minId, int size) {
        Log.e("datamanager", "start");
        return this.mFirstJokeModel.loadFirstJoke(maxId, minId, size).map(new Func1<JokeFirst, List<BaseJokeFirstData>>() {
            @Override
            public List<BaseJokeFirstData> call(JokeFirst jokeFirst) {
                return jokeFirst.detail;
            }
        }).compose(RxUtils.<List<BaseJokeFirstData>>applyIOToMainThreadSchedulers());
    }


    //获取天气

    public Observable<List<BaseWeatherData>> loadWeather(String city, int more) {
        Log.e("datamanager", "load");

        return this.mWeatherModel.loadWeather(city, more).map(new Func1<Weather, List<BaseWeatherData>>() {
            @Override
            public List<BaseWeatherData> call(Weather weather) {
                Log.e("datamanager", "load2");
                Log.e("weather", weather.toString());
                return weather.detail;
            }
        })
                .compose(RxUtils.<List<BaseWeatherData>>applyIOToMainThreadSchedulers());
    }

    // 机器人
    public Observable<BaseRobotResponseData> loadRobot(String info, String key) {
        return this.mRobotModel.loadRobot(info, key).map(new Func1<RobotResponseMsg, BaseRobotResponseData>() {
            @Override
            public BaseRobotResponseData call(RobotResponseMsg robotResponseMsg) {
                return robotResponseMsg.result;
            }
        }).compose(RxUtils.<BaseRobotResponseData>applyIOToMainThreadSchedulers());
    }

    //查询ip
    public Observable<BaseIpData> queryIp(String ip, String key) {
        return this.mQueryIpModel.queryIp(ip, key)
                .map(new Func1<Ip, BaseIpData>() {
                    @Override
                    public BaseIpData call(Ip ip) {
                        return ip.result;
                    }
                })
                .compose(RxUtils.<BaseIpData>applyIOToMainThreadSchedulers());
    }

}
