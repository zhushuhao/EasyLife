package d.dao.easylife.model.impl;

import d.dao.easylife.api.EasyLife;
import d.dao.easylife.bean.robot.RobotResponseMsg;
import d.dao.easylife.constants.BaseUrl;
import d.dao.easylife.model.IRobotModel;
import rx.Observable;

/**
 * Created by dao on 6/4/16.
 * 机器人
 */
public class RobotModel implements IRobotModel {
    private static RobotModel mInstance = new RobotModel();

    public static synchronized RobotModel getInstance() {
        return mInstance;
    }

    @Override
    public Observable<RobotResponseMsg> loadRobot(String info, String key) {
        return EasyLife.getInstance().getApiService(BaseUrl.ROBOT).loadRobot(info, key);
    }
}
