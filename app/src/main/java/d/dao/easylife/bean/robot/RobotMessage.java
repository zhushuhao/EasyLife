package d.dao.easylife.bean.robot;

/**
 * Created by dao on 6/3/16.
 * 用于用户输入数据
 */
public class RobotMessage {
    private int type;//0代表自己,1代表机器
    private String msg;//消息内容


    public RobotMessage(String msg,int type) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "RobotMessage{" +
                "type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
