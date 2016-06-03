package d.dao.easylife.bean.weather;

/**
 * Created by dao on 6/3/16.
 * 每一条天气
 * "id": 4584,                    ----数据库自增id，没有意义
 * "city": "合肥",                ----查询到的城市
 * "county": "合肥",              ----查询到的县（区）
 * "date": "2015-03-01",          ----该条记录所属日期
 * "day_condition": "多云",       ----白天的天气状况
 * "day_wind": "南风 ≤3级",      ----白天的风况
 * "day_temperature": "10℃",     ----白天最高气温
 * "night_condition": "多云",     ----晚上的天气状况
 * "night_wind": "东南风 ≤3级",  ----晚上的风况
 * "night_temperature": "2℃",    ----晚上的最低气温
 * "update_time": 1425168300000   ----更新时间，以毫秒表示
 */
public class BaseWeatherData {
    private int id;
    private String city;
    private String country;
    private String date;
    private String day_condition;
    private String day_wind;
    private String day_temperature;
    private String night_condition;
    private String night_wind;
    private String night_temperature;
    private long update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay_condition() {
        return day_condition;
    }

    public void setDay_condition(String day_condition) {
        this.day_condition = day_condition;
    }

    public String getDay_wind() {
        return day_wind;
    }

    public void setDay_wind(String day_wind) {
        this.day_wind = day_wind;
    }

    public String getDay_temperature() {
        return day_temperature;
    }

    public void setDay_temperature(String day_temperature) {
        this.day_temperature = day_temperature;
    }

    public String getNight_condition() {
        return night_condition;
    }

    public void setNight_condition(String night_condition) {
        this.night_condition = night_condition;
    }

    public String getNight_wind() {
        return night_wind;
    }

    public void setNight_wind(String night_wind) {
        this.night_wind = night_wind;
    }

    public String getNight_tempature() {
        return night_temperature;
    }

    public void setNight_tempature(String night_tempature) {
        this.night_temperature = night_tempature;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "BaseWeatherData{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", date='" + date + '\'' +
                ", day_condition='" + day_condition + '\'' +
                ", day_wind='" + day_wind + '\'' +
                ", day_temperature='" + day_temperature + '\'' +
                ", night_condition='" + night_condition + '\'' +
                ", night_wind='" + night_wind + '\'' +
                ", night_tempature='" + night_temperature + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
