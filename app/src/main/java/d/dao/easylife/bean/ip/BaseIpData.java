package d.dao.easylife.bean.ip;

/**
 * Created by dao on 6/4/16.
 *  ip数据
 */
public class BaseIpData {
    private String area;
    private String location;

    @Override
    public String toString() {
        return "BaseIpData{" +
                "area='" + area + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
//"area":"北京市",
//"location":"百度公司电信节点"