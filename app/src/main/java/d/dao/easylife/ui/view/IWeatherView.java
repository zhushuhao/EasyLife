package d.dao.easylife.ui.view;

import java.util.List;

import d.dao.easylife.bean.weather.BaseWeatherData;

/**
 * Created by dao on 6/3/16.
 * 天气
 */
public interface IWeatherView {

    /**
     * 得到的天气数据
     * @param list
     */
    void OnLoadWeatherSuccess(List<BaseWeatherData> list);

    /**
     * 异常
     * @param e
     */
    void onLoadWeatherFailure(Throwable e);
}
