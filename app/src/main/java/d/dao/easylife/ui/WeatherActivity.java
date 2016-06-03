package d.dao.easylife.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import d.dao.easylife.R;
import d.dao.easylife.adapter.SimpleFragmentPagerAdapter;
import d.dao.easylife.bean.weather.BaseWeatherData;
import d.dao.easylife.manager.CacheManager;
import d.dao.easylife.popupwindow.PopChooseCity;
import d.dao.easylife.presenter.impl.WeatherPresenterImpl;
import d.dao.easylife.ui.view.IWeatherView;
import d.dao.easylife.utils.ReservoirUtils;
import d.dao.easylife.utils.ToastUtils;

/**
 * 天气
 */
public class WeatherActivity extends BaseToolbarActivity
        implements View.OnClickListener, IWeatherView {

    private WeatherPresenterImpl mPresenter;
    private Context mContext;
    private TextView tv_weather, tv_date, tv_temperature, tv_day, tv_night;
    private TextView tv_weather_tomorrow, tv_date_tomorrow, tv_temperature_tomorrow,
            tv_day_tomorrow, tv_night_tomorrow;

    private TextView tv_refresh;//重新加载
    private LinearLayout ll_error_root;//错误页面
    private PopChooseCity mPopChooseCity;//城市选择弹出框

    private static String CITY = "北京";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mContext = WeatherActivity.this;
        mPresenter = new WeatherPresenterImpl();
        mPresenter.attachView(this);

        super.setHomeTrue();
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_night = (TextView) findViewById(R.id.tv_night);

        tv_date_tomorrow = (TextView) findViewById(R.id.tv_date_tomorrow);
        tv_temperature_tomorrow = (TextView) findViewById(R.id.tv_temperature_tomorrow);
        tv_weather_tomorrow = (TextView) findViewById(R.id.tv_weather_tomorrow);
        tv_day_tomorrow = (TextView) findViewById(R.id.tv_day_tomorrow);
        tv_night_tomorrow = (TextView) findViewById(R.id.tv_night_tomorrow);

        tv_refresh = (TextView) findViewById(R.id.tv_load_once_more);//重新加载
        ll_error_root = (LinearLayout) findViewById(R.id.error_root);
    }

    @Override
    protected void initData() {
        Log.e("loadWeather", "start");
        // 获取默认的城市
        String temp = CacheManager.getInstance().getDefaultCity();
        if(temp!=null){
            this.CITY = temp;
        }
        this.setTitle(CITY);
        loadData(CITY);
    }

    private void loadData(String city){
        refresh(city);
    }
    @Override
    protected void initListeners() {
        tv_refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //重新加载
            case R.id.tv_load_once_more:
                if (ll_error_root.getVisibility() == View.VISIBLE) {
                    refresh(CITY);
                }
                break;
        }
    }

    //刷新
    private void refresh(String city) {
        //隐藏错误页面
        if (ll_error_root.getVisibility() == View.VISIBLE) {
            ll_error_root.setVisibility(View.GONE);
        }
        //显示进度框
        showProgressDialog();
        //请求数据
        mPresenter.loadWeather(city, 2);
        this.CITY =  city;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_weather, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //返回
            case android.R.id.home:
                finish();
                break;
            //刷新
            case R.id.action_refresh:
                this.refresh(CITY);
                break;
            //选择城市
            case R.id.action_choose_city:
                showChooseCityPop();
                break;
            //收藏为默认地点
            case R.id.action_add_default_city:
                CacheManager.getInstance().saveDefaultCity(CITY);
                ToastUtils.show(mContext,"成功添加到默认地址",0);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    //城市选择弹出框
    private void showChooseCityPop() {
        mPopChooseCity = new PopChooseCity(WeatherActivity.this, onCityChooseClickListener);
        mPopChooseCity.showAtLocation(findViewById(R.id.weather_root), Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener onCityChooseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //取消
                case R.id.tv_cancel:
                    break;
                //确定
                case R.id.tv_ok:
                    //得到选中的城市
                    String city = mPopChooseCity.getCity();
                    setTitle(city);
                    loadData(city);
                    break;
            }
            //点击后关闭弹出框体
            mPopChooseCity.dismiss();
        }
    };


    @Override
    public void OnLoadWeatherSuccess(List<BaseWeatherData> list) {
        hiddenProgressDialog();
        ToastUtils.show(mContext,"刷新成功",0);
        if (list.size() > 0) {
            //设置今日天气情况
            BaseWeatherData data = list.get(0);
            setTodayWeather(data);

            //设置明日天气情况
            BaseWeatherData data_tomorrow = list.get(1);
            setTomorrowWeather(data_tomorrow);

        } else {
            onLoadWeatherFailure(new Throwable("data is null"));
        }
    }


    //设置今天天气
    private void setTodayWeather(BaseWeatherData data) {
        tv_date.setText(data.getDate());
        String day_condition = data.getDay_condition();
        String night_condition = data.getNight_condition();
        if (day_condition.equals(night_condition)) {
            tv_weather.setText(day_condition);
        } else {
            tv_weather.setText(day_condition + "~" + night_condition);
        }
        tv_temperature.setText(data.getNight_tempature() + "~" + data.getDay_temperature());
        tv_day.setText(data.getDay_wind());
        tv_night.setText(data.getNight_wind());
    }

    //设置明天天气
    private void setTomorrowWeather(BaseWeatherData data_tomorrow) {
        tv_date_tomorrow.setText(data_tomorrow.getDate());
        String day_condition_tomorrow = data_tomorrow.getDay_condition();
        String night_condition_tomorrow = data_tomorrow.getNight_condition();
        if (day_condition_tomorrow.equals(night_condition_tomorrow)) {
            tv_weather_tomorrow.setText(day_condition_tomorrow);
        } else {
            tv_weather_tomorrow.setText(day_condition_tomorrow + "~" + night_condition_tomorrow);
        }
        tv_weather_tomorrow.setText(data_tomorrow.getDay_condition());
        tv_temperature_tomorrow.setText(data_tomorrow.getNight_tempature() + "~" + data_tomorrow.getDay_temperature());
        tv_day_tomorrow.setText(data_tomorrow.getDay_wind());
        tv_night_tomorrow.setText(data_tomorrow.getNight_wind());
    }

    private void showProgressDialog() {
        super.showProgressDialog("", "加载中...");
    }

    private void hideProgressDialog() {
        super.hiddenProgressDialog();
    }

    @Override
    public void onLoadWeatherFailure(Throwable e) {
        hideProgressDialog();
        ll_error_root.setVisibility(View.VISIBLE);
    }
}
