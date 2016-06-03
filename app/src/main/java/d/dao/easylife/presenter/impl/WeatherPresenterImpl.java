package d.dao.easylife.presenter.impl;

import android.util.Log;

import java.util.List;

import d.dao.easylife.api.DataManager;
import d.dao.easylife.bean.weather.BaseWeatherData;
import d.dao.easylife.presenter.IWeatherPresenter;
import d.dao.easylife.ui.view.IWeatherView;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dao on 6/3/16.
 * 天气
 */
public class WeatherPresenterImpl implements IWeatherPresenter {

    private IWeatherView mView;
    public CompositeSubscription mCompositeSubscription;
    public DataManager mDataManager;

    @Override
    public void attachView(IWeatherView view) {
        this.mView = view;
        this.mCompositeSubscription = new CompositeSubscription();
        this.mDataManager = DataManager.getInstance();
    }

    @Override
    public void loadWeather(String city, int more) {
        if (more < 1) {
            more = 1;
        } else if (more > 2) {
            more = 2;
        }
        Log.e("load","load");
        this.mCompositeSubscription.add(this.mDataManager.loadWeather(city, more)
                .subscribe(new Subscriber<List<BaseWeatherData>>() {
            @Override
            public void onCompleted() {
                if (mCompositeSubscription != null) {
                    mCompositeSubscription.remove(this);
                }
            }
            @Override
            public void onError(Throwable e) {
                Log.e("failure",e.toString());
                mView.onLoadWeatherFailure(e);
            }
            @Override
            public void onNext(List<BaseWeatherData> baseWeatherDatas) {
                Log.e("get success",baseWeatherDatas.toString());
                mView.OnLoadWeatherSuccess(baseWeatherDatas);
            }
        }));
    }

    @Override
    public void detachView(IWeatherView view) {
        this.mView = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }
}
