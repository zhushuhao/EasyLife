package d.dao.easylife.presenter;

import d.dao.easylife.ui.view.IWeatherView;

/**
 * Created by dao on 6/3/16.
 *  天气
 */
public interface IWeatherPresenter {

    void attachView(IWeatherView view);

    /**
     * 加载天气情况
     * @param city 城市
     * @param more 几天
     */
    void loadWeather(String city,int more);
    void detachView(IWeatherView view);

}
