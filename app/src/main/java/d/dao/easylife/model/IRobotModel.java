package d.dao.easylife.model;

import d.dao.easylife.bean.robot.RobotResponseMsg;
import rx.Observable;

/**
 * Created by dao on 6/4/16.
 * 机器人
 */
public interface IRobotModel {


    /**
     * 获取机器人答案
     * @param info 用户说的话
     * @param key  聚合数据的key
     * @return
     */
    Observable<RobotResponseMsg> loadRobot(String info,String key);
}
