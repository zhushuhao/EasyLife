package d.dao.easylife.bean.robot;

import java.util.ArrayList;

/**
 * Created by dao on 6/4/16.
 * 返回的网络数据
 */


public class RobotResponseMsg {

    public String reason;
    public BaseRobotResponseData result;
    public int error_code;

    @Override
    public String toString() {
        return "RobotResponseMsg{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
//        "reason":"成功的返回",
//        "result": /*根据code值的不同，返回的字段有所不同*/
//        {
//        "code":100000, /*返回的数据类型，请根据code的值去数据类型API查询*/
//        "text":"你好啊，希望你今天过的快乐"
//        },
//        "error_code":0