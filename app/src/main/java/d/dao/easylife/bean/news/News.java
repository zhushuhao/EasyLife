package d.dao.easylife.bean.news;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dao on 5/30/16.
 * 新闻
 */
public class News implements Serializable {

    public String status;
    public String desc;
    public ArrayList<BaseNewsData> detail;//每次只有两天的数据

    @Override
    public String toString() {
        return "News{" +
                "status='" + status + '\'' +
                ", desc='" + desc + '\'' +
                ", detail=" + detail +
                '}';
    }
}
