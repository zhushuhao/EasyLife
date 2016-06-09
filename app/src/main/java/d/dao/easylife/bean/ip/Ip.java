package d.dao.easylife.bean.ip;

/**
 * Created by dao on 6/4/16.
 * 查询IP返回的数据
 */
public class Ip {
    public String resultcode;
    public String reason;
    public BaseIpData result;
    public String error_code;

    @Override
    public String toString() {
        return "Ip{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code='" + error_code + '\'' +
                '}';
    }
}
//        "resultcode":"200",
//        "reason":"Return Successd!",
//        "result":{
//        "area":"北京市",
//        "location":"百度公司电信节点"
//        },
//        "error_code":0