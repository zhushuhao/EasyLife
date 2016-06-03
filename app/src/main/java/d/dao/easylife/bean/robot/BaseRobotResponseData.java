package d.dao.easylife.bean.robot;

/**
 * Created by dao on 6/4/16.
 * 每一条返回的消息
 */
public class BaseRobotResponseData {

    private long code;
    private String text;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "BaseRobotResponseData{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
//"code":100000, /*返回的数据类型，请根据code的值去数据类型API查询*/
//"text":"你好啊，希望你今天过的快乐"